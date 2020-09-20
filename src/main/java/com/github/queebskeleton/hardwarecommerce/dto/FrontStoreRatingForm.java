package com.github.queebskeleton.hardwarecommerce.dto;

import lombok.Data;

@Data
public class FrontStoreRatingForm {
	
	private Long productId;
	private String raterName;
	private String raterEmailAddress;
	private String review;
	private byte value;

}
