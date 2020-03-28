package tacos.security.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.security.data.User;

@Data
public class RegistrationForm {

    private String username;

    private String password;

    private String confirm;

    private String street;

    private String city;

    private String state;

    private String zip;

    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), street, city, state, zip, phone);
    }
}
