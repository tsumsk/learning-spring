package jpa.example.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "album")
public class Album implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Temporal(TemporalType.DATE)
	@Column(name = "release_date")
	private Date releaseDate;

	@Version
	@Column(name = "version")
	private Integer version;

	@ManyToOne
	@JoinColumn(name = "singer_id")
	private Singer singer;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public Singer getSinger() {
		return singer;
	}

	@Override
	public String toString() {
		return "Album[" +
			"id[" + id + "]," +
			"title[" + title + "]," +
			"releaseDate[" + releaseDate + "]" +
			"]";
	}
}
