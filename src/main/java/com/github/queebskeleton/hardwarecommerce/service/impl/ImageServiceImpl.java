package com.github.queebskeleton.hardwarecommerce.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.queebskeleton.hardwarecommerce.dto.EntityImage;
import com.github.queebskeleton.hardwarecommerce.entity.ProductImage;
import com.github.queebskeleton.hardwarecommerce.repository.ProductImageJpaRepository;
import com.github.queebskeleton.hardwarecommerce.service.ImageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
	
	private static final String IMAGE_STORAGE_PATH = "images" + File.separator;
	
	private final ProductImageJpaRepository productImageJpaRepository;

	private static void createImageDirectoryIfNotExists() {
		// Check if images base path (folder) exists
		// Create directory if it doesn't exist
		File imagePath = new File(IMAGE_STORAGE_PATH);
		if(!imagePath.exists())
			imagePath.mkdir();
	}

	@Override
	public EntityImage getProductImage(UUID id) {
		ProductImage imageMeta =
				productImageJpaRepository.findById(id)
					.orElseThrow(() -> new IllegalArgumentException());
		
		EntityImage imageWrapper = new EntityImage();
		imageWrapper.setFileName(imageMeta.getFileName().toString());
		imageWrapper.setFileExtension(imageMeta.getFileExtension());
		
		try {
			FileInputStream imageFile =
				new FileInputStream(
						IMAGE_STORAGE_PATH +
						imageMeta.getFileName() + "." +
						imageMeta.getFileExtension());
			InputStream imageInputStream = new BufferedInputStream(imageFile);
			imageWrapper.setInputStream(imageInputStream);
		} catch(IOException e) {
			e.printStackTrace(System.out);
			throw new RuntimeException("An error occured while retrieving images from file system.");
		}
		
		return imageWrapper;
	}

	@Override
	public void saveAll(List<EntityImage> images) {
		createImageDirectoryIfNotExists();
		
		images.forEach(image -> {
			try(FileOutputStream imageFileOutputStream
					= new FileOutputStream(
							IMAGE_STORAGE_PATH +
							image.getFileName() + "." +
							image.getFileExtension());
				OutputStream imageOutputStream = new BufferedOutputStream(imageFileOutputStream);) {
				image.getInputStream().transferTo(imageOutputStream);
				image.getInputStream().close();
			} catch(IOException e) {
				e.printStackTrace();
				// TODO: Change exception
				throw new RuntimeException("An error occured while saving image to file system.");
			}
			
		});
		
		
	}

}
