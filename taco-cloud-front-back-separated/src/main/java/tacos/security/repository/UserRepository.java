package tacos.security.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.security.data.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
