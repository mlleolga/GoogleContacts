package hello.service.Impl;


import hello.domain.ContactDTO;
import hello.domain.UserDTO;
import hello.domain.entity.Role;
import hello.domain.entity.User;
import hello.mapper.ContactMapper;
import hello.repository.UserRepository;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean saveNewUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        if (userRepository.findByEmail(userDTO.getEmail()) == null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setName(userDTO.getName());
            user.setRole(Role.USER);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ContactDTO> getUsersContacts(Long id) {
        List<ContactDTO> contactDTOS = new ArrayList<>();
        userRepository.findById(id).getContactsList().forEach(contacts -> {
            contactDTOS.add(contactMapper.parserToDTO(contacts));
        });
        return contactDTOS;
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId);
    }


}
