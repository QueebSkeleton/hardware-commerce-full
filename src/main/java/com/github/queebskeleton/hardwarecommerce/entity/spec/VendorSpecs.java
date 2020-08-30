package com.github.queebskeleton.hardwarecommerce.entity.spec;

import org.springframework.data.jpa.domain.Specification;

import com.github.queebskeleton.hardwarecommerce.entity.Vendor;

public class VendorSpecs {
	
	public static Specification<Vendor> nameContainsIgnoreCase(String name) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%");
		};
	}
	
	public static Specification<Vendor> addressContainsIgnoreCase(String address) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(criteriaBuilder.upper(root.get("address")), "%" + address.toUpperCase() + "%");
		};
	}
	
	public static Specification<Vendor> contactContainsIgnoreCase(String contact) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(criteriaBuilder.upper(root.get("contact")), "%" + contact.toUpperCase() + "%");
		};
	}
	
	public static Specification<Vendor> emailAddressContainsIgnoreCase(String emailAddress) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(criteriaBuilder.upper(root.get("emailAddress")), "%" + emailAddress.toUpperCase() + "%");
		};
	}
	
	public static Specification<Vendor> websiteUrlContainsIgnoreCase(String websiteUrl) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(criteriaBuilder.upper(root.get("websiteUrl")), "%" + websiteUrl.toUpperCase() + "%");
		};
	}

}
