package testMySQL.repository;

import org.springframework.data.repository.CrudRepository;
import testMySQL.model.User;

public interface UserRepository extends CrudRepository<User,Integer> {
}
