package spring.data.jpa.example.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<T> implements Serializable {
	@CreatedDate
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@LastModifiedBy
	@Column(name = "last_modified_by")
	private String lastModifiedBy;

	public void setCreatedDate(Date createdDate) {
		this.createdBy = createdBy;
	}

	public Optional<Date> getCreatedDate() {
		return Optional.of(createdDate);
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Optional<String> getCreatedBy() {
		return Optional.of(createdBy);
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Optional<Date> getLastModifiedDate() {
		return Optional.of(lastModifiedDate);
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Optional<String> getLastModifiedBy() {
		return Optional.of(lastModifiedBy);
	}
}
