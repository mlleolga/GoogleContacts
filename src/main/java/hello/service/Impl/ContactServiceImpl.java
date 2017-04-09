package hello.service.Impl;

import hello.domain.ContactDTO;
import hello.domain.entity.Contacts;
import hello.domain.entity.Groups;
import hello.domain.entity.User;
import hello.mapper.ContactMapper;
import hello.repository.ContactsRepository;
import hello.service.ContactService;
import hello.service.GroupService;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sasha on 07.04.2017.
 */
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @Autowired
    private ContactsRepository contactsRepository;
    @Autowired
    private ContactMapper contactMapper;

    @Override
    public Contacts addContact(ContactDTO contactDTO, Long userId) {
        Contacts contact = contactMapper.parserToEntity(contactDTO);
        User user = userService.findUserById(userId);
        contact.setGroups(checkGroup(contactDTO.getGroup(),user));
        contact.setUser(user);
      return contactsRepository.save(contact);
    }

    @Override
    public ContactDTO editContact(ContactDTO contactDTO, Long userId) {
      return  contactMapper.parserToDTO(addContact(contactDTO,userId));
    }

    private Groups checkGroup(String groupName,User user){
        Groups group = groupService.findByName(groupName,user);
        if(group == null){
            Groups newGroup = new Groups();
            newGroup.setName(groupName);
            newGroup.setUser(user);
            return groupService.save(newGroup);
        }else{
            return group;
        }
    }
}
