package hello.mapper.impl;

import hello.domain.ContactDTO;
import hello.domain.entity.Contacts;
import hello.domain.entity.Groups;
import hello.mapper.ContactMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperContactImpl implements ContactMapper {

    @Override
    public ContactDTO parserToDTO(Contacts contacts) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setEmail(contacts.getEmail());
        contactDTO.setName(contacts.getName());
        contactDTO.setPhone(contacts.getPhone());
        contactDTO.setId(contacts.getId());
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
