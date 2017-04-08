package hello.controller;

import hello.domain.entity.Role;
import hello.domain.entity.User;
import hello.repository.UserRepository;
import hello.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@RestController
@Component
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
    //    importData();
     //   importUser();
    }

    private void importUser() {
        User user = new User();
        user.setEmail("2");
        user.setPassword(passwordEncoder.encode("2"));
        user.setName("sasha");
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public void importData() {
        userRepository.save(createUser());
    }

    @RequestMapping(value = "/logout2", method = RequestMethod.GET)
    public ModelAndView logout() {
        authenticationService.logout();
        return new ModelAndView("redirect:" + "/login.html");
    }

    private User createUser() {
        User user = new User();
        user.setEmail("1");
        user.setPassword(passwordEncoder.encode("1"));
        user.setName("sasha");
        user.setRole(Role.ADMIN);
        return user;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    private void login(@RequestParam  String email, @RequestParam  String password){
        authenticationService.authenticate(email, password);
    }

}
