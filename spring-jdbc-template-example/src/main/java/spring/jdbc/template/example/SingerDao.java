package spring.jdbc.template.example;

import java.util.List;

public interface SingerDao {
	String findNameById(Long id);

	String findNameByIdV2(Long id);

	List<Singer> findAll();

	List<Singer> findAllWithAlbums();
}
