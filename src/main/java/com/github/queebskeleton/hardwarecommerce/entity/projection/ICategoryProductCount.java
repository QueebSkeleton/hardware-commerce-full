package com.github.queebskeleton.hardwarecommerce.entity.projection;

import com.github.queebskeleton.hardwarecommerce.entity.Category;

public interface ICategoryProductCount {
	
	Category getCategory();
	Long getTotalProductCount();

}
