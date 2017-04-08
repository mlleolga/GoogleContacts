package hello.mapper.impl;

import hello.domain.ContactDTO;
import hello.domain.entity.Contacts;
import hello.domain.entity.Groups;
import hello.mapper.ContactMapper;
import hello.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by sasha on 08.04.2017.
 */
@Component
public class MapperContactImpl implements ContactMapper {

    @Override
    public ContactDTO parserToDTO(Contacts contacts) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setEmail(contacts.getEmail());
        contactDTO.setName(contacts.getName());
        contactDTO.setPhone(contacts.getPhone());
        Groups groups = contacts.getGroups();
        contactDTO.setGroup(groups.getName());
        return contactDTO;
    }

    @Override
    public Contacts parserToEntity(ContactDTO contactDTO) {
        return null;
    }


}
