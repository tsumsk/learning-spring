package restful.ws.example;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import restful.ws.example.entities.Singer;

public interface SingerRepository extends CrudRepository<Singer, Long> {
	List<Singer> findByFirstName(String firstName);
}
