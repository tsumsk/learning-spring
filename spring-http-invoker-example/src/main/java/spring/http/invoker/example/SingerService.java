package spring.http.invoker.example;

import java.util.List;
import spring.http.invoker.example.entities.Singer;

public interface SingerService {
	List<Singer> findAll();

	List<Singer> findByFirstName(String firstName);

	Singer findById(Long id);

	Singer save(Singer singer);

	void delete(Singer singer);
}
