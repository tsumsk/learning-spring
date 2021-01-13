package spring.boot.data.jpa.example.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "singer")
@NamedQueries({
	@NamedQuery(name = "Singer.findAll",
		query = "select s from Singer s"),
	@NamedQuery(name = "Singer.findAllWithAlbum",
		query = "select distinct s from Singer s " +
			"left join fetch s.albums a " +
			"left join fetch s.instruments i"),
	@NamedQuery(name = "Singer.findById",
		query = "select distinct s from Singer s " +
			"left join fetch s.albums a " +
			"left join fetch s.instruments i " +
			"where s.id = :id")
})
public class Singer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;

	@Version
	@Column(name = "version")
	private Integer version;

	@OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Album> albums = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "singer_instrument",
		joinColumns = @JoinColumn(name = "singer_id"),
		inverseJoinColumns = @JoinColumn(name = "instrument_id"))
	private Set<Instrument> instruments = new HashSet<>();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public boolean addAlbum(Album album) {
		album.setSinger(this);
		return getAlbums().add(album);
	}

	public boolean removeAlbum(Album album) {
		return getAlbums().remove(album);
	}

	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
	}

	public Set<Instrument> getInstruments() {
		return instruments;
	}

	@Override
	public String toString() {
		return "Singer[" +
			"id" + "[" + id + "]" + "," +
			"firstName" + "[" + firstName + "]" + ", " +
			"lastName" + "[" + lastName + "]" + "," +
			"birthDate" + "[" + birthDate + "]" +
			"]";
	}
}
