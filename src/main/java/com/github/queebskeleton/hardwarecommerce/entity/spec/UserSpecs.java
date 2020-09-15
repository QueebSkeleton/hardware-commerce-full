package com.github.queebskeleton.hardwarecommerce.entity.spec;

import org.springframework.data.jpa.domain.Specification;

import com.github.queebskeleton.hardwarecommerce.entity.User;

public class UserSpecs {
	
	public static Specification<User> isAdministrator() {
		return (root, query, builder) -> {
			return builder.equal(root.get("type"), User.Type.ADMINISTRATOR);
		};
	}
	
	public static Specification<User> isCustomer() {
		return (root, query, builder) -> {
			return builder.equal(root.get("type"), User.Type.CUSTOMER);
		};
	}
	
	public static Specification<User> firstNameContainsIgnoreCase(String firstName) {
		return (root, query, builder) -> {
			return builder.like(builder.upper(root.get("firstName")), "%" + firstName.toUpperCase() + "%");
		};
	}
	
	public static Specification<User> lastNameContainsIgnoreCase(String lastName) {
		return (root, query, builder) -> {
			return builder.like(builder.upper(root.get("lastName")), "%" + lastName.toUpperCase() + "%");
		};
	}
	
	public static Specification<User> nameContainsIgnoreCase(String name) {
		return (root, query, builder) -> {
			return builder.like(
					builder.upper(builder.concat(
							builder.concat(root.get("firstName"), " "),
							root.get("lastName"))), "%" + name.toUpperCase() + "%");
		};
	}
	
	public static Specification<User> emailAddressContainsIgnoreCase(String emailAddress) {
		return (root, query, builder) -> {
			return builder.like(builder.upper(root.get("emailAddress")), "%" + emailAddress.toUpperCase() + "%");
		};
	}
	
	public static Specification<User> contactNumberContainsIgnoreCase(String contactNumber) {
		return (root, query, builder) -> {
			return builder.like(builder.upper(root.get("contactNumber")), "%" + contactNumber.toUpperCase() + "%");
		};
	}
	
}
