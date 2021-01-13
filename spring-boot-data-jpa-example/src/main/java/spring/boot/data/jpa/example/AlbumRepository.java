package spring.boot.data.jpa.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.boot.data.jpa.example.entities.Album;
import spring.boot.data.jpa.example.entities.Singer;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
	List<Album> findBySinger(Singer singer);

	@Query("SELECT a FROM Album a where a.title like %:title%")
	List<Album> findByTitle(String title);
}
