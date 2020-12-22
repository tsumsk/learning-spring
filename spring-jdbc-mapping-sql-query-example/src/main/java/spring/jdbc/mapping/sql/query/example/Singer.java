package spring.jdbc.mapping.sql.query.example;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
public class Singer implements Serializable {

	private Long id;

	private String firstName;

	private String lastName;

	private Date birthDate;

	private List<Album> albums;

	public void addAlbum(Album album) {
		if (albums == null) {
			return;
		}

		albums.add(album);
	}
}
