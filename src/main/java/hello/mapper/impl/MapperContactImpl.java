package hello.mapper.impl;

import hello.domain.ContactDTO;
import hello.domain.entity.Contacts;
import hello.domain.entity.Groups;
import hello.domain.entity.User;
import hello.mapper.ContactMapper;
import hello.mapper.Mapper;
import hello.service.GroupService;
import hello.service.Impl.GroupsServiceImpl;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by sasha on 08.04.2017.
 */
@Component
public class MapperContactImpl implements ContactMapper {

    @Autowired
    private GroupService groupService;

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
        Contacts contact = new Contacts();
        if(contactDTO.getId()!=null) {
            contact.setId(contactDTO.getId());
        }
        contact.setEmail(contactDTO.getEmail());
        contact.setName(contactDTO.getName());
        contact.setPhone(contactDTO.getPhone());
        return contact;
    }

}
