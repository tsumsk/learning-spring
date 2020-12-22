package spring.jdbc.mapping.sql.query.example;

import java.util.List;

public interface SingerDao {
	String findNameById(Long id);

	List<Singer> findAll();

	List<Singer> findByFirstName(String firstName);

	List<Singer> findAllWithAlbums();
}
