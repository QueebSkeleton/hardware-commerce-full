package com.github.queebskeleton.hardwarecommerce.service;

import java.util.List;
import java.util.UUID;

import com.github.queebskeleton.hardwarecommerce.dto.EntityImage;

public interface ImageService {
	
	EntityImage getProductImage(UUID id);
	void saveAll(List<EntityImage> images);
	
}
