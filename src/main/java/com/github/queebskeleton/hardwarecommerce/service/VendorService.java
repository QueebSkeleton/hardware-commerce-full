package com.github.queebskeleton.hardwarecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.queebskeleton.hardwarecommerce.entity.Vendor;

public interface VendorService {
	
	Page<Vendor> getVendorPage(Pageable pageable, String search);
	Vendor getVendorById(Long id);
	void saveVendor(Vendor vendor);
	void deleteVendorById(Long id);

}
