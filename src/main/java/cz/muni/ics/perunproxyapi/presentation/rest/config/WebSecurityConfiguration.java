package cz.muni.ics.perunproxyapi.presentation.rest.config;

import cz.muni.ics.perunproxyapi.presentation.rest.models.User;
import cz.muni.ics.perunproxyapi.presentation.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String BASIC_AUTH_PATH = "/ba";

    @Value("${security.path}")
    private String pathToUsers;

    private static final String ROLE_API_USER = "API_USER";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(BASIC_AUTH_PATH + "/**")
                .authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        List<User> users = UserService.getUsersFromYamlFile(pathToUsers);

        for (User user : users) {
            auth.inMemoryAuthentication()
                    .withUser(user.getUsername())
                    .password(passwordEncoder().encode(user.getPassword()))
                    .roles(ROLE_API_USER);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
