package tacos.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.jpa.data.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
