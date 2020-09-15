package com.github.queebskeleton.hardwarecommerce.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hardware_order")
public class Order {
	
	public static enum Status {
		PENDING,
		PROCESSING,
		DELIVERED,
		DENIED,
		CANCELLED;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User placedBy;
	
	private LocalDateTime placedOn;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy = "order", cascade = { CascadeType.REMOVE })
	private List<OrderItem> orderItems;

}
