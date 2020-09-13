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

import com.github.queebskeleton.hardwarecommerce.entity.User;
import com.github.queebskeleton.hardwarecommerce.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/administrators")
public class AdministratorManagementController {
	
	private final UserService userService;

	@GetMapping("/table")
	public String table(Pageable pageable, String search, Model model) {
		Page<User> administratorPage = userService.getAdministratorPage(pageable, search);
		
		model.addAttribute("administratorPage", administratorPage);
		model.addAttribute("firstNameOrder", administratorPage.getSort().getOrderFor("firstName"));
		model.addAttribute("lastNameOrder", administratorPage.getSort().getOrderFor("lastName"));
		model.addAttribute("emailAddressOrder", administratorPage.getSort().getOrderFor("emailAddress"));
		model.addAttribute("contactNumberOrder", administratorPage.getSort().getOrderFor("contactNumber"));
		
		return "admin/pages/administrator-mgmt/table";
	}
	
	@GetMapping("/add-modal-form")
	public String addModalForm(Model model) {
		model.addAttribute("administrator", new User());
		model.addAttribute("modalHeaderText", "Add Administrator");
		
		return "admin/pages/administrator-mgmt/add-update-modal";
	}
	
	@GetMapping("/update-modal-form")
	public String updateModalForm(@RequestParam Long id, Model model) {
		if(id == null)
			throw new IllegalArgumentException("Invalid parameters given.");
			
		model.addAttribute("administrator", userService.getUserById(id));
		model.addAttribute("modalHeaderText", "Update Administrator");
		
		return "admin/pages/administrator-mgmt/add-update-modal";
	}
	
	@PostMapping("/save")
	public @ResponseBody ResponseEntity<Void> save(User administrator) {
		userService.saveAdministrator(administrator);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public @ResponseBody ResponseEntity<Void> deleteById(@RequestParam Long id) {
		userService.deleteUserById(id);
		
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
