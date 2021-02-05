package spring.http.invoker.example;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.http.invoker.example.entities.Singer;

@Service("singerService")
@Transactional
public class SingerServiceImpl implements SingerService {

	@Autowired
	private SingerRepository singerRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Singer> findAll() {
		return Lists.newArrayList(singerRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public List<Singer> findByFirstName(String firstName) {
		return singerRepository.findByFirstName(firstName);
	}

	@Transactional(readOnly = true)
	@Override
	public Singer findById(Long id) {
		return singerRepository.findById(id).get();
	}

	@Override
	public Singer save(Singer singer) {
		return singerRepository.save(singer);
	}

	@Override
	public void delete(Singer singer) {
		singerRepository.delete(singer);
	}
}
