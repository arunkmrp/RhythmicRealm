package com.kodnest.rhythmicrealm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kodnest.rhythmicrealm.model.User;
import com.kodnest.rhythmicrealm.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam("usernameOrEmail") String usernameOrEmail,
			@RequestParam("password") String password, RedirectAttributes redirectAttributes, HttpSession session,
			Model model) {

		boolean exists = userService.usernameOrEmailExists(usernameOrEmail);

		if (exists) {
			boolean authenticated = userService.authenticateUser(usernameOrEmail, password);
			if (authenticated) {
				User user = userService.findByUsernameOrEmail(usernameOrEmail);
				String role = user.getRole();
				session.setAttribute("user", user);
				if ("admin".equalsIgnoreCase(role)) {
					return "redirect:/adminhome";
				} else if ("customer".equalsIgnoreCase(role)) {
					return "redirect:/customerhome";
				} else {
					return "redirect:/customerhome";
				}
			} else {
				redirectAttributes.addFlashAttribute("errorMessage", "Incorrect password. Please try again.");
				return "redirect:/login";
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "User does not exist. Please register first.");
			return "redirect:/login";
		}
	}

	@GetMapping("/admin/users")
	public String getUsers(Model model) {
		List<User> userList = userService.getAllUsers();
		model.addAttribute("users", userList);
		return "Users";
	}

}
