package jpa.example;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Service("singerSummaryService")
@Repository
@Transactional
public class SingerSummaryService {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public void displaySingerSummary() {
		List result = entityManager.createQuery(
			"select s.firstName, s.lastName, a.title from Singer s " +
				"left join s.albums a " +
				"where a.releaseDate = (select max(a2.releaseDate) from Album a2 where a2.singer = s.id)")
			.getResultList();

		int count = 0;
		for (Iterator i = result.iterator(); i.hasNext(); ) {
			Object[] values = (Object[]) i.next();
			System.out.println(++count + ": " + values[0] + ", " + values[1] + ", " + values[2]);
		}
	}

	@Transactional(readOnly = true)
	public void displaySingerSummaryV2() {
		List<SingerSummary> result = entityManager.createQuery(
			"select new jpa.example.SingerSummary(s.firstName, s.lastName, a.title) from Singer s " +
				"left join s.albums a " +
				"where a.releaseDate = (select max(a2.releaseDate) from Album a2 where a2.singer = s.id)",
			SingerSummary.class)
			.getResultList();

		int count = 0;
		for (SingerSummary singerSummary : result) {
			System.out.println(++count + ": " + singerSummary.getFirstName() + ", " + singerSummary.getLastName() +
				", " + singerSummary.getLatestAlbum());
		}
	}
}
