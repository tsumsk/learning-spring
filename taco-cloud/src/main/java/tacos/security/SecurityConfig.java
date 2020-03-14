package tacos.security;

import java.nio.charset.StandardCharsets;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /* in-memory user store
        auth
          .inMemoryAuthentication()
            .withUser("user1")
              .password("{noop}user1")
              .authorities("ROLE_USER")
            .and()
            .withUser("user2")
              .password("{noop}user2")
              .authorities("ROLE_USER");
         */

        auth
          .jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(
                "select username, password, enabled from Taco_Users " +
                "where username=?")
            .authoritiesByUsernameQuery(
                "select username, authority from Taco_User_Authorities " +
                "where username=?")
            .passwordEncoder(new BCryptPasswordEncoder(10));

    }
}
