package com.github.queebskeleton.hardwarecommerce.entity.projection;

import com.github.queebskeleton.hardwarecommerce.entity.Vendor;

public interface IVendorProductCount {

	Vendor getVendor();
	Long getTotalProductCount();
	
}
