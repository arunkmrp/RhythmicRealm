package com.kodnest.rhythmicrealm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.rhythmicrealm.model.Playlist;
import com.kodnest.rhythmicrealm.model.Song;
import com.kodnest.rhythmicrealm.model.User;
import com.kodnest.rhythmicrealm.service.PlaylistService;
import com.kodnest.rhythmicrealm.service.SongService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PlaylistController {

	@Autowired
	PlaylistService playlistService;
	@Autowired
	private SongService songService;

	@GetMapping("/admin/create-playlist")
	public String showCreatePlaylistForm(Model model, HttpSession session) {
		List<Song> songs = songService.getAllSongs();
		model.addAttribute("songs", songs);
		User user = (User) session.getAttribute("user");

		model.addAttribute("role", user.getRole());
		return "createplaylist";
	}

	@GetMapping("/admin/view-playlists")
	public String viewPlaylists(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");

		model.addAttribute("role", user.getRole());
		List<Playlist> playlists = playlistService.getAllPlaylists();
		model.addAttribute("playlists", playlists);
		return "viewplaylist";
	}

	@PostMapping("/create")
	public String createPlaylist(@ModelAttribute("playlist") Playlist playlist) {

		return "redirect:/admin/create-playlist";

	}

	@GetMapping("/viewsongs")
	public String viewSongs(@RequestParam("playlistId") Long playlistId, Model model) {
		Playlist playlist = playlistService.findPlaylistById(playlistId);
		if (playlist != null) {
			model.addAttribute("songs", playlist.getSongs());
			return "viewsongs";
		} else {
			return "error";
		}
	}

}
