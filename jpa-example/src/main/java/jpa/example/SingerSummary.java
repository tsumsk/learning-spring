package jpa.example;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SingerSummary implements Serializable {
	private String firstName;

	private String lastName;

	private String latestAlbum;

	public SingerSummary(String firstName, String lastName, String latestAlbum) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.latestAlbum = latestAlbum;
	}
}
