package com.github.queebskeleton.hardwarecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.github.queebskeleton.hardwarecommerce.entity.Order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminCustomOrderForm {
	
	@Data
	@NoArgsConstructor
	public static class OrderItemForm {
		
		private Long productId;
		private int quantity;
		
	}
	
	private Long placedById;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime placedOn;
	
	private Order.Status status;
	private List<OrderItemForm> orderItems;

}
