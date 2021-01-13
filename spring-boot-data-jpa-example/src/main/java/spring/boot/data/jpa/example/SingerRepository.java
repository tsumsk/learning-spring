package spring.boot.data.jpa.example;

import org.springframework.data.repository.CrudRepository;
import spring.boot.data.jpa.example.entities.Singer;

import java.util.List;

public interface SingerRepository extends CrudRepository<Singer, Long> {
	List<Singer> findByFirstName(String firstName);

	List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}
