package com.kodnest.rhythmicrealm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kodnest.rhythmicrealm.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/adminhome")
	public String adminHome(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("role", user.getRole());
		return "adminhome";
	}

	@GetMapping("/customerhome")
	public String customerHome(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("role", user.getRole());
		System.out.println(user.getRole());
		model.addAttribute("ispremium", user.isIspremium());
		return "customerhome";
	}

	@GetMapping("/admin/add-song")
	public String addsong() {
		return "addsong";
	}

}
