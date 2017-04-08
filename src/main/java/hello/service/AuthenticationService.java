package hello.service;


import hello.domain.entity.User;

public interface AuthenticationService {
    void authenticate(String email, String password);
    void authenticate(User user);
    void logout();
    String getUserEmail();
    Long getUserId();
}
