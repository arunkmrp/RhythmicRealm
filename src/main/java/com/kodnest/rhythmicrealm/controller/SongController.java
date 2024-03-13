package com.kodnest.rhythmicrealm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.rhythmicrealm.model.Song;
import com.kodnest.rhythmicrealm.model.User;
import com.kodnest.rhythmicrealm.service.SongService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SongController {
	@Autowired
	SongService songService;

	@PostMapping("/admin/addsong")
	public String addSong(@ModelAttribute Song song) {
		songService.addSong(song);
		return "redirect:/admin/add-song";
	}

	@GetMapping("/admin/view-songs")
	public String viewSongs(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("role", user.getRole());
		System.out.println(user.getRole());
		List<Song> songs = songService.getAllSongs();
		model.addAttribute("songs", songs);
		return "viewsongs";
	}

}
