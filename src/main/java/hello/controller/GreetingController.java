package hello.controller;

import hello.domain.ContactDTO;
import hello.domain.NameDTO;
import hello.domain.UserDTO;
import hello.domain.entity.Groups;
import hello.service.AuthenticationService;
import hello.service.ContactService;
import hello.service.GroupService;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public String contacts(){
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
    @RequestMapping(value = "/register2" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
   public boolean newUserRegister(@RequestBody UserDTO userDTO) {
  userService.saveNewUser(userDTO);
  return true;
    }

    @ResponseBody
    @RequestMapping(value = "/addContact" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addContact(@RequestBody ContactDTO contactDTO) {
 //       userService.saveNewUser(userDTO);
        contactService.addContact(contactDTO,  authenticationService.getUserId());
       return true;
    }


    @ResponseBody
    @RequestMapping(value = "/getGroupsByName" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Groups> findGroupsByPartName(@RequestBody NameDTO nameDTO) {

        return groupService.findByPartName(nameDTO.getName(),authenticationService.getUserId());
    }


}