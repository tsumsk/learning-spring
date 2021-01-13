package spring.data.jpa.example;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.data.jpa.example.entities.Singer;

import java.util.List;

@Service("jpaSingerService")
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
	public List<Singer> findByFirstNameAndLastName(String firstName, String lastName) {
		return singerRepository.findByFirstNameAndLastName(firstName, lastName);
	}
}
