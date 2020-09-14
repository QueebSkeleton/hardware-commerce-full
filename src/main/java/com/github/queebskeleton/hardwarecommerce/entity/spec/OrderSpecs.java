package com.github.queebskeleton.hardwarecommerce.entity.spec;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import com.github.queebskeleton.hardwarecommerce.entity.Order;
import com.github.queebskeleton.hardwarecommerce.entity.User;

public class OrderSpecs {
	
	public static Specification<Order> userNameContainsIgnoreCase(String name) {
		return (root, query, builder) -> {
			Join<Order, User> orderUserJoin = root.join("placedBy");
			return builder.like(
					builder.concat(
						builder.concat(builder.upper(orderUserJoin.get("firstName")), " "),
						orderUserJoin.get("lastName")),
					"%" + name.toUpperCase() + "%");
		};
	}
	
	public static Specification<Order> placedOnContainsIgnoreCase(String placedOn) {
		return (root, query, builder) -> {
			return builder.like(root.get("placedOn"), "%" + placedOn + "%");
		};
	}
	
	public static Specification<Order> statusContainsIgnoreCase(String status) {
		return (root, query, builder) -> {
			return builder.like(builder.upper(root.get("status")), "%" + status.toUpperCase() + "%");
		};
	}

}
