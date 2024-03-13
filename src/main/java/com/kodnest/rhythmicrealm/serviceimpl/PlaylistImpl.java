package com.kodnest.rhythmicrealm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.rhythmicrealm.model.Playlist;
import com.kodnest.rhythmicrealm.repository.PlaylistRepository;
import com.kodnest.rhythmicrealm.service.PlaylistService;

@Service
public class PlaylistImpl implements PlaylistService {

	@Autowired
	PlaylistRepository playlistRepository;

	@Override
	public List<Playlist> getAllPlaylists() {
		return playlistRepository.findAll();
	}

	@Override
	public void createPlaylist(Playlist playlist) {
		playlistRepository.save(playlist);
	}

	@Override
	public Playlist findPlaylistById(Long playlistId) {
		return playlistRepository.findById(playlistId).orElse(null);
	}

}
