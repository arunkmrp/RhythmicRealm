package com.kodnest.rhythmicrealm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kodnest.rhythmicrealm.model.User;
import com.kodnest.rhythmicrealm.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	UserService userService;

	@GetMapping("/register")
	public String showRegistrationForm() {
		return "register";
	}

	@PostMapping("/register")
	public String addUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		String username = user.getUsername();
		String email = user.getEmail();

		User existingUserByUsername = userService.findByUsername(username);
		User existingUserByEmail = userService.findByEmail(email);

		if (existingUserByUsername != null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Username already exists. Please choose another.");
			return "redirect:/register";
		} else if (existingUserByEmail != null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Email already exists. Please choose another.");
			return "redirect:/register";
		} else {
			userService.saveUser(user);
			return "redirect:/login";
		}
	}

}
