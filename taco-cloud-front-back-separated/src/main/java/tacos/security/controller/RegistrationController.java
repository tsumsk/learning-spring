package tacos.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.security.dto.RegistrationForm;
import tacos.security.data.User;
import tacos.security.repository.UserRepository;

@RestController
@RequestMapping(path = "/register", produces = "application/json")
public class RegistrationController {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User processRegistration(@RequestBody RegistrationForm registrationForm) {
        return userRepository.save(registrationForm.toUser(passwordEncoder));
    }
}
