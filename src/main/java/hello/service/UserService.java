package hello.service;


import hello.domain.ContactDTO;
import hello.domain.UserDTO;
import hello.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService{

        List<User> getAllUsers();

        boolean saveNewUser(UserDTO userDTO);

        List<ContactDTO> getUsersContacts(Long id);

        User findUserById(Long userId);
}
