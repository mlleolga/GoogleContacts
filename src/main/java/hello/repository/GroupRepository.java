package hello.repository;

import hello.domain.entity.Groups;
import hello.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by olya on 07.04.2017.
 */
public interface GroupRepository extends CrudRepository<Groups, String> {

        Groups findByNameAndUser(String name,User user);

           List<Groups> findByNameIgnoreCaseContainingAndUser(String name, User user);
}
