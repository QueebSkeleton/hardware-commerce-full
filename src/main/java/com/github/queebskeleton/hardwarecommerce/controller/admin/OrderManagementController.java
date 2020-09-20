package com.github.queebskeleton.hardwarecommerce.controller.admin;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.queebskeleton.hardwarecommerce.entity.Order;
import com.github.queebskeleton.hardwarecommerce.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/orders")
public class OrderManagementController {

	private final OrderService orderService;
	
	@GetMapping("/table")
	public String table(Pageable pageable, String search, Model model) {
		Page<Order> orderPage = orderService.getOrderPage(pageable, search);
		
		model.addAttribute("orderPage", orderPage);
		model.addAttribute("idOrder", orderPage.getSort().getOrderFor("id"));
		model.addAttribute("placedByFirstNameOrder", orderPage.getSort().getOrderFor("placedBy.firstName"));
		model.addAttribute("placedOnOrder", orderPage.getSort().getOrderFor("placedOn"));
		model.addAttribute("statusOrder", orderPage.getSort().getOrderFor("status"));
		
		return "admin/pages/order-mgmt/table";
	}
	
	@GetMapping("/invoice")
	public String invoice(Long id, Model model) {
		model.addAttribute("invoice", orderService.getOrderInvoiceByOrderId(id));
		
		return "admin/pages/order-mgmt/invoice";
	}
	
	@DeleteMapping("/delete")
	public @ResponseBody ResponseEntity<Void> deleteById(@RequestParam Long id) {
		orderService.deleteOrderById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// Return Http Status BAD_REQUEST without message for Illegal Argument Exceptions
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid parameters given.")
	public void illegalArgumentException() {
	}
	
	// Return Http Status 500 without message for Data Access Exceptions
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Data Access error has occured.")
	public void dataAccessException() {
	}
	
}
