package hello.repository;

import hello.domain.entity.Contacts;
import hello.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface ContactsRepository extends CrudRepository<Contacts, Long> {
    Contacts findByEmailAndUser(String email, User user);

    @Override
    void delete(Long aLong);
}
