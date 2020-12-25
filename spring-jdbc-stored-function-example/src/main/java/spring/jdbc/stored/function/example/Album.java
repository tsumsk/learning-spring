package spring.jdbc.stored.function.example;

import lombok.Data;

import java.sql.Date;

@Data
public class Album {
	private Long id;

	private Long singerId;

	private String title;

	private Date releaseDate;
}
