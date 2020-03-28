package tacos.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import tacos.jpa.data.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
