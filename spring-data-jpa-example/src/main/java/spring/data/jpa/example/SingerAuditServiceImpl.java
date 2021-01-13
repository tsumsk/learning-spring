package spring.data.jpa.example;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.data.jpa.example.entities.SingerAudit;

import java.util.List;

@Service("singerAuditService")
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService {
	@Autowired
	private SingerAuditRepository singerAuditRepository;

	@Transactional(readOnly = true)
	@Override
	public List<SingerAudit> findAll() {
		return Lists.newArrayList(singerAuditRepository.findAll());
	}

	@Override
	public SingerAudit findById(Long id) {
		return singerAuditRepository.findById(id).orElse(null);
	}

	@Override
	public SingerAudit save(SingerAudit singerAudit) {
		return singerAuditRepository.save(singerAudit);
	}
}
