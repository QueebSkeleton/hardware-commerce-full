package com.github.queebskeleton.hardwarecommerce.controller.admin;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.queebskeleton.hardwarecommerce.dto.EntityImage;
import com.github.queebskeleton.hardwarecommerce.service.ImageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/images")
public class ImageController {
	
	private final ImageService imageService;
	
	private static MediaType fromImageFileExtension(String extension) {
		String extensionUpper = extension.toUpperCase();
		
		if(extensionUpper.contentEquals("JPEG") || extensionUpper.contentEquals("JPG"))
			return MediaType.IMAGE_JPEG;
		
		else if(extensionUpper.contentEquals("PNG"))
			return MediaType.IMAGE_PNG;
		
		throw new IllegalArgumentException("Given file extension format is not recognized.");
	}

	@GetMapping("/product")
	public void productImage(@RequestParam UUID id, HttpServletResponse response) throws IOException {
		EntityImage imageWrapper = imageService.getProductImage(id);
		
		// TODO: Better handling of IO streams, restructure code as well
		response.setContentType(fromImageFileExtension(imageWrapper.getFileExtension()).toString());
		imageWrapper.getInputStream().transferTo(response.getOutputStream());
		imageWrapper.getInputStream().close();
	}
	
}
