package com.github.queebskeleton.hardwarecommerce.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.queebskeleton.hardwarecommerce.entity.Vendor;
import com.github.queebskeleton.hardwarecommerce.entity.spec.VendorSpecs;
import com.github.queebskeleton.hardwarecommerce.repository.VendorJpaRepository;
import com.github.queebskeleton.hardwarecommerce.service.VendorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VendorServiceImpl implements VendorService {
	
	private final VendorJpaRepository vendorJpaRepository;
	
	@Override
	public Page<Vendor> getVendorPage(Pageable pageable, String search) {
		
		if(search == null)
			return vendorJpaRepository.findAll(pageable);
		
		return vendorJpaRepository.findAll(
				Specification.where(VendorSpecs.nameContainsIgnoreCase(search))
				.or(VendorSpecs.addressContainsIgnoreCase(search))
				.or(VendorSpecs.contactContainsIgnoreCase(search))
				.or(VendorSpecs.emailAddressContainsIgnoreCase(search))
				.or(VendorSpecs.websiteUrlContainsIgnoreCase(search)), pageable);
		
	}

	@Override
	public Vendor getVendorById(Long id) {
		return vendorJpaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id."));
	}

	@Override
	public void saveVendor(Vendor vendor) {
		vendorJpaRepository.save(vendor);
	}

	@Override
	public void deleteVendorById(Long id) {
		vendorJpaRepository.deleteById(id);
	}

}
