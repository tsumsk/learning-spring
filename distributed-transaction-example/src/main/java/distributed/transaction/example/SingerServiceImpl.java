package distributed.transaction.example;

import distributed.transaction.example.entities.Singer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("singerService")
@Transactional
public class SingerServiceImpl implements SingerService {

	@PersistenceContext(unitName = "entityManagerFactory1")
	private EntityManager entityManager1;

	@PersistenceContext(unitName = "entityManagerFactory2")
	private EntityManager entityManager2;

	@Override
	public Singer save(Singer singer) {
	  Singer singer2 = new Singer();
	  singer2.setFirstName(singer.getFirstName());
	  singer2.setLastName(singer.getLastName());

	  if (singer.getId() == null) {
	  	entityManager1.persist(singer);
	  	entityManager2.persist(singer2);
		} else {
	  	entityManager1.merge(singer);
	  	entityManager2.merge(singer2);
		}

	  return singer;
	}
}
