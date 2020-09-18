package com.github.queebskeleton.hardwarecommerce.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.github.queebskeleton.hardwarecommerce.entity.Product;
import com.github.queebskeleton.hardwarecommerce.repository.ProductJpaRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO: Probably abstract away this class, since this implementation is Spring-reliant
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class ShoppingCart {
	
	@Value("${general.settings.sales-tax-rate}")
	private double salesTaxRate;
	
	private final ProductJpaRepository productJpaRepository;
	
	@Getter
	@AllArgsConstructor
	public static class Item {
		
		private Long productId;
		private String productName;
		private String productImagePath;
		private int quantity;
		private double taxedUnitPrice;
		
		private void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		public double getSubtotal() {
			return quantity * taxedUnitPrice;
		}
		
	}
	
	@Getter
	private List<Item> items;
	
	public ShoppingCart(ProductJpaRepository productJpaRepository) {
		this.productJpaRepository = productJpaRepository;
		items = new ArrayList<>();
	}
	
	public void addItem(Long productId, int quantity) {
		if(itemExists(productId)) {
			getItemByProductId(productId).setQuantity(quantity);
			return;
		}
		
		Product product =
				productJpaRepository.findById(productId, EntityGraphUtils.fromAttributePaths("images"))
					.orElseThrow(() -> new IllegalArgumentException("Invalid Product ID."));
		
		items.add(new Item(
				product.getId(),
				product.getName(),
				!product.getImages().isEmpty() ?
					product.getImages().get(0).getFileName().toString() :
						null,
				quantity,
				product.isTaxable() ?
					product.getUnitPrice() + (product.getUnitPrice() * salesTaxRate) :
						product.getUnitPrice()));
	}
	
	public void removeItem(Long productId) {
		items.removeIf(item -> item.getProductId() == productId);
	}
	
	public boolean itemExists(Long productId) {
		return !items.parallelStream()
				.noneMatch(item -> item.getProductId() == productId);
	}
	
	public Item getItemByProductId(Long productId) {
		return items.parallelStream()
					.filter(item -> item.getProductId() == productId)
					.findFirst()
					.orElse(null);
	}
	
	public int getTotalQuantity() {
		return items != null ?
				items.parallelStream()
				.mapToInt(item -> item.getQuantity())
				.sum() : 0;
	}
	
	public double getSubtotal() {
		return items != null ?
				items.parallelStream()
					.mapToDouble(Item::getSubtotal)
					.sum() : 0;
	}

}
