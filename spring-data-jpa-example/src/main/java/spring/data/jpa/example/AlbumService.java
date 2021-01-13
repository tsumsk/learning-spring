package spring.data.jpa.example;

import spring.data.jpa.example.entities.Album;
import spring.data.jpa.example.entities.Singer;

import java.util.List;

public interface AlbumService {
	List<Album> findBySinger(Singer singer);
	List<Album> findByTitle(String title);
}
