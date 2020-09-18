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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.queebskeleton.hardwarecommerce.entity.Vendor;
import com.github.queebskeleton.hardwarecommerce.service.VendorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/vendors")
public class VendorManagementController {
	
	private final VendorService vendorService;
	
	@GetMapping("/table")
	public String table(Pageable pageable, String search, Model model) {
		Page<Vendor> vendorPage = vendorService.getVendorPage(pageable, search);
		
		model.addAttribute("vendorPage", vendorPage);
		model.addAttribute("nameOrder", vendorPage.getSort().getOrderFor("name"));
		model.addAttribute("addressOrder", vendorPage.getSort().getOrderFor("address"));
		model.addAttribute("contactOrder", vendorPage.getSort().getOrderFor("contact"));
		model.addAttribute("emailAddressOrder", vendorPage.getSort().getOrderFor("emailAddress"));
		model.addAttribute("websiteUrlOrder", vendorPage.getSort().getOrderFor("websiteUrl"));
		
		return "admin/pages/vendor-mgmt/table";
	}
	
	@GetMapping("/add-modal-form")
	public String addModalForm(Model model) {
		model.addAttribute("vendor", new Vendor());
		model.addAttribute("modalHeaderText", "Add Vendor");
		
		return "admin/pages/vendor-mgmt/add-update-modal";
	}
	
	@GetMapping("/update-modal-form")
	public String updateModalForm(@RequestParam Long id, Model model) {
		if(id == null)
			throw new IllegalArgumentException("Invalid parameters given.");
			
		model.addAttribute("vendor", vendorService.getVendorById(id));
		model.addAttribute("modalHeaderText", "Update Vendor");
		
		return "admin/pages/vendor-mgmt/add-update-modal";
	}
	
	@PostMapping("/save")
	public @ResponseBody ResponseEntity<Void> save(Vendor vendor) {
		vendorService.saveVendor(vendor);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public @ResponseBody ResponseEntity<Void> deleteById(@RequestParam Long id) {
		vendorService.deleteVendorById(id);
		
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
