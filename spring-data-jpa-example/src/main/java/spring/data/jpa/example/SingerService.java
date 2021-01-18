package spring.data.jpa.example;

import spring.data.jpa.example.entities.Singer;

import java.util.List;

public interface SingerService {
	List<Singer> findAll();

	List<Singer> findByFirstName(String firstName);

	List<Singer> findByFirstNameAndLastName(String firstName, String lastName);

	Singer save(Singer singer);

	long countAll();
}
