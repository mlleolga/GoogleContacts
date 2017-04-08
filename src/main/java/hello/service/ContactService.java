package hello.service;

import hello.domain.ContactDTO;
import org.springframework.stereotype.Service;

/**
 * Created by sasha on 07.04.2017.
 */
@Service
public interface ContactService {
    void addContact(ContactDTO contactDTO,Long userId);

}
