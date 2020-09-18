package com.github.queebskeleton.hardwarecommerce.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.github.queebskeleton.hardwarecommerce.entity.User;
import com.github.queebskeleton.hardwarecommerce.entity.specs.UserSpecs;
import com.github.queebskeleton.hardwarecommerce.repository.AddressJpaRepository;
import com.github.queebskeleton.hardwarecommerce.repository.UserJpaRepository;
import com.github.queebskeleton.hardwarecommerce.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final AddressJpaRepository addressJpaRepository;
	private final UserJpaRepository userJpaRepository;

	@Override
	public Page<User> getAdministratorPage(Pageable pageable, String search) {
		
		if(search == null)
			return userJpaRepository.findAll(
					Specification.where(UserSpecs.isAdministrator()), pageable);
		
		return userJpaRepository.findAll(
				Specification.where(UserSpecs.isAdministrator()
				.and(
					UserSpecs.firstNameContainsIgnoreCase(search)
					.or(UserSpecs.lastNameContainsIgnoreCase(search))
					.or(UserSpecs.emailAddressContainsIgnoreCase(search))
					.or(UserSpecs.contactNumberContainsIgnoreCase(search)))), pageable);
		
	}
	
	@Override
	public Page<User> getCustomerPage(Pageable pageable, String search) {
		
		if(search == null)
			return userJpaRepository.findAll(
					Specification.where(UserSpecs.isCustomer()),
					pageable);
		
		return userJpaRepository.findAll(
				Specification.where(UserSpecs.isCustomer()
				.and(
					UserSpecs.firstNameContainsIgnoreCase(search)
					.or(UserSpecs.lastNameContainsIgnoreCase(search))
					.or(UserSpecs.emailAddressContainsIgnoreCase(search))
					.or(UserSpecs.contactNumberContainsIgnoreCase(search)))), pageable);
		
	}

	@Override
	public User getUserById(Long id) {
		return userJpaRepository.findById(id, EntityGraphUtils.fromAttributePaths("address"))
				.orElseThrow(() -> new IllegalArgumentException("Invalid User ID."));
	}
	
	@Override
	public void saveAdministrator(User administrator) {
		addressJpaRepository.save(administrator.getAddress());
		
		administrator.setType(User.Type.ADMINISTRATOR);
		userJpaRepository.save(administrator);
	}

	@Override
	public void saveCustomer(User customer) {
		addressJpaRepository.save(customer.getAddress());
		
		customer.setType(User.Type.CUSTOMER);
		userJpaRepository.save(customer);
	}

	@Override
	public void deleteUserById(Long id) {
		userJpaRepository.deleteById(id);
	}

}
