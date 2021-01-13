package spring.data.jpa.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.data.jpa.example.entities.Album;
import spring.data.jpa.example.entities.Singer;

import java.util.List;

@Service("jpaAlbumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {
	@Autowired
	private AlbumRepository albumRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Album> findBySinger(Singer singer) {
		return albumRepository.findBySinger(singer);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Album> findByTitle(String title) {
		return albumRepository.findByTitle(title);
	}
}
