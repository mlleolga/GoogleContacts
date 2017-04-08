package hello.service.Impl;

import hello.domain.ContactDTO;
import hello.domain.entity.Contacts;
import hello.domain.entity.Groups;
import hello.domain.entity.User;
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
public class ContactServiceImpl implements ContactService{
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @Autowired
    private ContactsRepository contactsRepository;

    @Override
    public void addContact(ContactDTO contactDTO, Long userId) {
        Contacts contact = new Contacts();
        contact.setEmail(contactDTO.getEmail());
        contact.setName(contactDTO.getName());
        contact.setPhone(contactDTO.getPhone());
        User user = userService.findUserById(userId);
        contact.setUser(user);
        contact.setGroups(checkGroup(contactDTO.getGroup(),user));
        contactsRepository.save(contact);
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
