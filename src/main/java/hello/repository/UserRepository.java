package hello.repository;

import hello.domain.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, String> {
    User findByEmail(String email);
    List<User> findAll();
    User findById(Long id);


}
