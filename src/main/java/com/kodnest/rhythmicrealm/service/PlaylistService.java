package com.kodnest.rhythmicrealm.service;

import java.util.List;

import com.kodnest.rhythmicrealm.model.Playlist;

public interface PlaylistService {

	List<Playlist> getAllPlaylists();

	void createPlaylist(Playlist playlist);

	Playlist findPlaylistById(Long playlistId);

}
