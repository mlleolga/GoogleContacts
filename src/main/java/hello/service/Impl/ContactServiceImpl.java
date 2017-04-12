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
 * Created by olya on 07.04.2017.
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
        User user = userService.findUserById(userId);
        if(contactsRepository.findByEmailAndUser(contactDTO.getEmail(),user)!=null){
            return null;
        }
        Contacts contact = contactMapper.parserToEntity(contactDTO);
        contact.setGroups(checkGroup(contactDTO.getGroup(),user));
        contact.setUser(user);
      return contactsRepository.save(contact);
    }

    @Override
    public void deleteOneContact(Long id) {
        contactsRepository.delete(id);
    }

    @Override
    public void deleteAllUsersContacts(Long userId) {
        userService.findUserById(userId).getContactsList().forEach(contactsRepository::delete);
    }

    @Override
    public ContactDTO editContact(ContactDTO contactDTO, Long userId) {
      return  contactMapper.parserToDTO(editContact1(contactDTO,userId));
    }

    @Override
    public ContactDTO getContactToEdit(Long id) {
       return contactMapper.parserToDTO(contactsRepository.findOne(id));
    }

    private Contacts editContact1(ContactDTO contactDTO, Long userId){
        User user = userService.findUserById(userId);
        Contacts contact = contactMapper.parserToEntity(contactDTO);
        contact.setGroups(checkGroup(contactDTO.getGroup(),user));
        contact.setUser(user);
        return contactsRepository.save(contact);

    }



    private Groups checkGroup(String groupName, User user){
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
