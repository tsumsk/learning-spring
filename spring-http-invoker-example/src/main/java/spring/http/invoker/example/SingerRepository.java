package spring.http.invoker.example;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import spring.http.invoker.example.entities.Singer;

public interface SingerRepository extends CrudRepository<Singer, Long> {
	List<Singer> findByFirstName(String firstName);
}
