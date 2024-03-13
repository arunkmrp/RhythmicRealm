package com.kodnest.rhythmicrealm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.rhythmicrealm.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {

}
