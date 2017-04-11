package hello.controller;

import hello.domain.ContactDTO;
import hello.domain.NameDTO;
import hello.domain.UserDTO;
import hello.domain.entity.Contacts;
import hello.domain.entity.Groups;
import hello.service.AuthenticationService;
import hello.service.ContactService;
import hello.service.GroupService;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @Autowired
    ContactService contactService;

    @Autowired
    GroupService groupService;


    @PostMapping(value = "/signup")
    private String login(@RequestParam String email, @RequestParam String password) {
        authenticationService.authenticate(email, password);
        return "contacts";
    }

    @RequestMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("name", "Greet");
        return "greeting";
    }

    @RequestMapping("/login")
    public String loginpage(Model model) {
        model.addAttribute("name", "Greet");
        return "login";
    }

    @RequestMapping("/getContacts")
    @ResponseBody
    public List<ContactDTO> Contacts() {
        //    return authenticationService.getUserEmail();

        return userService.getUsersContacts(authenticationService.getUserId());
    }

    @RequestMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @RequestMapping("/logout")
    public String logout() {
        authenticationService.logout();
        return "/login";
    }


    @RequestMapping("/register")
    public String register() {
        return "/register";
    }

    @ResponseBody
    @PostMapping(value = "/register2", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean newUserRegister(@RequestBody UserDTO userDTO) {
        return userService.saveNewUser(userDTO);
    }

    @ResponseBody
    @PostMapping(value = "/authregister", consumes = MediaType.APPLICATION_JSON_VALUE)
    private boolean login2(@RequestBody UserDTO userDTO) {
        authenticationService.authenticate(userDTO.getEmail(), userDTO.getPassword());
        return true;
    }

    @ResponseBody
    @PostMapping(value = "/addContact", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addContact(@RequestBody ContactDTO contactDTO) {
        //       userService.saveNewUser(userDTO);

        Contacts contacts = contactService.addContact(contactDTO, authenticationService.getUserId());
        if (contacts == null) {
            return false;
        }
        return true;
    }

    @ResponseBody
    @PostMapping(value = "/getGroupsByName", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Groups> findGroupsByPartName(@RequestBody NameDTO nameDTO) {

        return groupService.findByPartName(nameDTO.getName(), authenticationService.getUserId());
    }

    @ResponseBody
    @PostMapping(value = "/editcontact", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ContactDTO editContact(@RequestBody ContactDTO contactDTO) {
        return contactService.editContact(contactDTO, authenticationService.getUserId());
        //   return groupService.findByPartName(nameDTO.getName(),authenticationService.getUserId());

    }

    @ResponseBody
    @GetMapping(value = "/getcontact")
    public ContactDTO getContactDTO(@RequestParam int id) {
            return contactService.getContactToEdit(Long.valueOf(id));
    }

    @DeleteMapping(value = "/deletecontact")
    public String deleteOneContact(@RequestParam int id) {
       contactService.deleteOneContact(Long.valueOf(id));
        return "contacts";
    }
    @DeleteMapping(value = "/deleteUsersContact")
    public String deleteUsersContacts() {
       contactService.deleteAllUsersContacts(authenticationService.getUserId());
        return "contacts";
    }



}
