package spring.data.jpa.example;

import spring.data.jpa.example.entities.SingerAudit;

import java.util.List;

public interface SingerAuditService {
	List<SingerAudit> findAll();
	SingerAudit findById(Long id);
	SingerAudit save(SingerAudit singerAudit);
}
