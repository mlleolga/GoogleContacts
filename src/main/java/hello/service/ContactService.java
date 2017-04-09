package hello.service;

import hello.domain.ContactDTO;
import hello.domain.entity.Contacts;
import org.springframework.stereotype.Service;

/**
 * Created by sasha on 07.04.2017.
 */
@Service
public interface ContactService {
    Contacts addContact(ContactDTO contactDTO, Long userId);

    ContactDTO editContact(ContactDTO contactDTO, Long userId);

}
