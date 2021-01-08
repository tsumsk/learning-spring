package jpa.example;

import jpa.example.entities.Singer;
import jpa.example.entities.Singer_;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<Singer> findAll() {
		return entityManager.createNamedQuery("Singer.findAll", Singer.class)
			.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Singer> findAllByNativeQuery() {
		return entityManager.createNativeQuery("SELECT id, first_name, last_name, birth_date, version " +
			"FROM singer", Singer.class)
			.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Singer> findByCriteriaQuery(String firstName, String lastName) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Singer> criteriaQuery = criteriaBuilder.createQuery(Singer.class);

		Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
		singerRoot.fetch(Singer_.albums, JoinType.LEFT);
		singerRoot.fetch(Singer_.instruments, JoinType.LEFT);

		criteriaQuery.select(singerRoot).distinct(true);

		Predicate predicate = criteriaBuilder.conjunction();

		if (firstName != null) {
			Predicate p = criteriaBuilder.equal(singerRoot.get(Singer_.firstName), firstName);
			predicate = criteriaBuilder.and(predicate, p);
		}

		if (lastName != null) {
			Predicate p = criteriaBuilder.equal(singerRoot.get(Singer_.lastName), lastName);
			predicate = criteriaBuilder.and(predicate, p);
		}

		criteriaQuery.where(predicate);

		return entityManager.createQuery(criteriaQuery)
			.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Singer> findAllWithAlbum() {
		return entityManager.createNamedQuery("Singer.findAllWithAlbum", Singer.class)
			.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Singer findById(Long id) {
		return entityManager.createNamedQuery("Singer.findById", Singer.class)
			.setParameter("id", id)
			.getSingleResult();
	}

	@Override
	public Singer save(Singer singer) {
		if (singer.getId() == null) {
			entityManager.persist(singer);
		} else {
			entityManager.merge(singer);
		}

		return singer;
	}

	@Override
	public void delete(Singer singer) {
		Singer merged = entityManager.merge(singer);
		entityManager.remove(merged);
	}
}
