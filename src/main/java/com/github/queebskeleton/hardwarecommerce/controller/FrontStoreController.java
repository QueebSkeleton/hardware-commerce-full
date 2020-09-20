package com.github.queebskeleton.hardwarecommerce.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.github.queebskeleton.hardwarecommerce.dto.BillingAddress;
import com.github.queebskeleton.hardwarecommerce.dto.FrontStorePagination;
import com.github.queebskeleton.hardwarecommerce.dto.FrontStoreRatingForm;
import com.github.queebskeleton.hardwarecommerce.entity.Rating;
import com.github.queebskeleton.hardwarecommerce.model.ShoppingCart;
import com.github.queebskeleton.hardwarecommerce.service.CategoryService;
import com.github.queebskeleton.hardwarecommerce.service.OrderService;
import com.github.queebskeleton.hardwarecommerce.service.ProductService;
import com.github.queebskeleton.hardwarecommerce.service.RatingService;
import com.github.queebskeleton.hardwarecommerce.service.VendorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FrontStoreController {
	
	private final ShoppingCart shoppingCart;

	private final CategoryService categoryService;
	private final VendorService vendorService;
	private final ProductService productService;
	private final RatingService ratingService;
	private final OrderService orderService;
	
	@ModelAttribute(name = "shoppingCart")
	public ShoppingCart shoppingCart() {
		return shoppingCart;
	}
	
	@GetMapping("/")
	public String landingPage(Model model) {
		model.addAttribute("topProductList", productService.getTopSellingProducts());
		model.addAttribute("newProductList", productService.getNewProducts());
		
		return "index";
	}
	
	@GetMapping("/store")
	public String store(Model model) {
		model.addAttribute("categoriesWithProductCount", categoryService.getAllCategoriesWithProductCount());
		model.addAttribute("vendorsWithProductCount", vendorService.getAllVendorsWithProductCount());
		
		return "front-store/pages/store";
	}
	
	@GetMapping("/products")
	public String products(FrontStorePagination paginationData, Pageable pageable, Model model) {
		model.addAttribute("productPage", productService.getProductPage(paginationData, pageable));
		
		return "front-store/pages/products-view";
	}
	
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
	
	@GetMapping("/checkout")
	public String checkoutPage() {
		return "front-store/pages/checkout";
	}
	
	@PostMapping("/checkout")
	public String checkout(
			BillingAddress billingAddress,
			@SessionAttribute ShoppingCart shoppingCart) {
		orderService.placeOrder(billingAddress, shoppingCart);
		
		return "redirect:/";
	}
	
}
