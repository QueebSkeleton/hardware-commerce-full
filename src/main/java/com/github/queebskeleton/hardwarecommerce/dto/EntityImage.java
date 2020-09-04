package com.github.queebskeleton.hardwarecommerce.dto;

import java.io.InputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityImage {
	
	private String fileName;
	
	private String fileExtension;
	
	private InputStream inputStream;

}
