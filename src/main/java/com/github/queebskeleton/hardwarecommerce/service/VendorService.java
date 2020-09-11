package com.github.queebskeleton.hardwarecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.queebskeleton.hardwarecommerce.entity.Vendor;
import com.github.queebskeleton.hardwarecommerce.entity.projection.IVendorProductCount;

public interface VendorService {
	
	Page<Vendor> getVendorPage(Pageable pageable, String search);
	List<Vendor> getAllVendors();
	Vendor getVendorById(Long id);
	void saveVendor(Vendor vendor);
	void deleteVendorById(Long id);
	
	List<IVendorProductCount> getAllVendorsWithProductCount();

}
