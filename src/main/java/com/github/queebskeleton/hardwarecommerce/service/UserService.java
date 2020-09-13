package com.github.queebskeleton.hardwarecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.queebskeleton.hardwarecommerce.entity.User;

public interface UserService {
	
	Page<User> getAdministratorPage(Pageable pageable, String search);
	Page<User> getCustomerPage(Pageable pageable, String search);
	User getUserById(Long id);
	void saveAdministrator(User administrator);
	void saveCustomer(User customer);
	void deleteUserById(Long id);

}
