package hello.repository;

import hello.domain.entity.Contacts;
import hello.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sasha on 08.04.2017.
 */
public interface ContactsRepository extends CrudRepository<Contacts, Long> {
    Contacts findByEmailAndUser(String email, User user);

    @Override
    void delete(Long aLong);
}
