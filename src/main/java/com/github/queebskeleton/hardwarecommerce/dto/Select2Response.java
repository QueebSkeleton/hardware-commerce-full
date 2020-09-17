package com.github.queebskeleton.hardwarecommerce.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Select2Response {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Content {

		private String id;
		private String text;
		
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Pagination {
		
		private boolean more;
		
	}
	
	private List<Content> results;
	private Pagination pagination;
	
}
