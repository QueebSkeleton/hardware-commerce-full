package com.github.queebskeleton.hardwarecommerce.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.queebskeleton.hardwarecommerce.entity.User;
import com.github.queebskeleton.hardwarecommerce.entity.specs.UserSpecs;
import com.github.queebskeleton.hardwarecommerce.repository.UserJpaRepository;
import com.github.queebskeleton.hardwarecommerce.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserJpaRepository userJpaRepository;
	
	@Override
	public Page<User> getCustomerPage(Pageable pageable, String search) {
		
		if(search == null)
			return userJpaRepository.findAll(
					Specification.where(UserSpecs.isCustomer()), pageable);
		
		return userJpaRepository.findAll(
				Specification.where(UserSpecs.isCustomer()
				.and(UserSpecs.firstNameContainsIgnoreCase(search))
				.and(UserSpecs.lastNameContainsIgnoreCase(search))
				.and(UserSpecs.emailAddressContainsIgnoreCase(search))
				.and(UserSpecs.contactNumberContainsIgnoreCase(search))), pageable);
		
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
