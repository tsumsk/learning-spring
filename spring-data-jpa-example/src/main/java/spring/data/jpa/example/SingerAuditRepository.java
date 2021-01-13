package spring.data.jpa.example;

import org.springframework.data.repository.CrudRepository;
import spring.data.jpa.example.entities.SingerAudit;

public interface SingerAuditRepository extends CrudRepository<SingerAudit, Long> {
}
