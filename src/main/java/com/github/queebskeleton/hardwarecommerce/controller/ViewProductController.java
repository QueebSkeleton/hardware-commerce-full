package com.github.queebskeleton.hardwarecommerce.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.queebskeleton.hardwarecommerce.dto.FrontStoreRatingForm;
import com.github.queebskeleton.hardwarecommerce.entity.Rating;
import com.github.queebskeleton.hardwarecommerce.service.ProductService;
import com.github.queebskeleton.hardwarecommerce.service.RatingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ViewProductController {

	private final RatingService ratingService;
	private final ProductService productService;
	
	@GetMapping("/product")
	public String viewProduct(Long id, Model model) {
		model.addAttribute("productDisplay", productService.getProductDisplayByProductId(id));
		
		List<Rating> ratingList = ratingService.getAllRatingsByProductId(id);
		Map<Integer, Long> ratingSummaryMap =
				Arrays.asList(1, 2, 3, 4, 5)
					  .stream()
					  .collect(
						Collectors.toMap(value -> value, value -> Long.valueOf(0)));
		ratingSummaryMap.putAll(ratingList.parallelStream()
							  .collect(
										Collectors.groupingBy(
											rating -> rating.getValue(),
											Collectors.counting())));
		
		model.addAttribute("ratingList", ratingList);
		model.addAttribute("ratingSummaryMap", ratingSummaryMap);
		model.addAttribute("totalRatingCount", ratingList.size());
		
		return "front-store/pages/view-product";
	}
	
	@PostMapping("/rate")
	public String rateProduct(FrontStoreRatingForm ratingForm) {
		ratingService.rateProduct(ratingForm);
		
		return "redirect:/product?id=" + ratingForm.getProductId();
	}
	
}
