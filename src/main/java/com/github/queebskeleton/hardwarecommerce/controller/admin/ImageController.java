package com.github.queebskeleton.hardwarecommerce.controller.admin;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.queebskeleton.hardwarecommerce.entity.Product;
import com.github.queebskeleton.hardwarecommerce.service.ImageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/images")
public class ImageController {
	
	private final ImageService imageService;
	
	private static MediaType getImageMediaTypeFromExtension(String extension) {
		if(extension.contentEquals("jpg") || extension.contentEquals("jpeg"))
			return MediaType.IMAGE_JPEG;
		
		else if(extension.contentEquals("png"))
			return MediaType.IMAGE_PNG;
		
		else if(extension.contentEquals("gif"))
			return MediaType.IMAGE_GIF;
		
		throw new IllegalArgumentException("Invalid file extension.");
	}

	@GetMapping("/product")
	public @ResponseBody ResponseEntity<byte[]> productImage(@RequestParam Long id) {
		Product.Image image = imageService.getProductImage(id);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(getImageMediaTypeFromExtension(image.getExtension()));
		
		return new ResponseEntity<byte[]>(image.getData(), headers, HttpStatus.OK);
	}
	
}
