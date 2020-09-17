package com.github.queebskeleton.hardwarecommerce.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class ShoppingCart {
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Item {
		
		private Long productId;
		private String productName;
		private String productImagePath;
		private int quantity;
		private double taxedUnitPrice;
		
		public double getSubtotal() {
			return quantity * taxedUnitPrice;
		}
		
	}
	
	private List<Item> items;
	
	public void addItem(Item item) {
		if(items == null)
			items = new ArrayList<>();
		
		items.add(item);
	}
	
	public void removeItem(Long productId) {
		if(items != null)
			items.removeIf(item -> item.getProductId() == productId);
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
