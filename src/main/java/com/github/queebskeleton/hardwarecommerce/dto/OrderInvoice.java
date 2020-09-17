package com.github.queebskeleton.hardwarecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.github.queebskeleton.hardwarecommerce.entity.Order;
import com.github.queebskeleton.hardwarecommerce.entity.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInvoice {
	
	public static OrderInvoice fromOrderEntity(Order order) {
		OrderInvoice invoice = new OrderInvoice(
				order.getId(),
				order.getPlacedBy().getFirstName() + " " + order.getPlacedBy().getLastName(),
				order.getPlacedBy().getContactNumber(),
				order.getPlacedBy().getEmailAddress(),
				order.getPlacedOn(),
				order.getStatus().toString(),
				order.getSalesTaxRate(),
				order.getOrderItems().parallelStream()
						.map(Item::fromOrderItemEntity)
						.collect(Collectors.toList()));
		invoice.getItems().forEach(
				item -> item.setOrder(invoice));
		return invoice;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Item {
		
		public static Item fromOrderItemEntity(OrderItem orderItem) {
			return new Item(
					null,
					orderItem.getProduct().getName(),
					orderItem.getProduct().getBarcode(),
					orderItem.isTaxed(),
					orderItem.getUnitPrice(),
					orderItem.getQuantity());
		}
		
		private OrderInvoice order;
		private String productName;
		private String productBarcode;
		private boolean isTaxed;
		private double productUnitPrice;
		private int quantity;
		
		public double getSubtotal() {
			return productUnitPrice * quantity;
		}
		
		public double getSalesTax() {
			return isTaxed ?
					getSubtotal() * order.getSalesTaxRate() :
						0.0;
		}
		
		public double getSubtotalTaxed() {
			return getSubtotal() + getSalesTax();
		}
		
	}
	
	private Long orderId;
	private String placedBy;
	private String placedByContactNumber;
	private String placedByEmailAddress;
	private LocalDateTime placedOn;
	private String status;
	private double salesTaxRate;
	private List<Item> items;
	
	public double getTotal() {
		return items.parallelStream()
					.mapToDouble(Item::getSubtotalTaxed)
					.sum();
	}

}
