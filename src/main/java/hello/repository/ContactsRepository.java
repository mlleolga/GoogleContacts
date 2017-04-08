package hello.repository;

import hello.domain.entity.Contacts;
import hello.domain.entity.Groups;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sasha on 08.04.2017.
 */
public interface ContactsRepository extends CrudRepository<Contacts, String> {
}
