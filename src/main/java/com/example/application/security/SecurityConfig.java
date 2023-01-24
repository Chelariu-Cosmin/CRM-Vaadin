package com.example.application.security;

import com.example.application.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collections;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    private static class SimpleInMemoryUserDetailsManager extends InMemoryUserDetailsManager {
        public SimpleInMemoryUserDetailsManager() {
            createUser(new User("user",
                    "{noop}userpass",
                    Collections.singleton(new SimpleGrantedAuthority ("ROLE_USER"))
            ));
            createUser(new User("admin",
                    "{noop}userpass",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            ));
        }
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/images/**").permitAll();
        super.configure (http);
        setLoginView (http, LoginView.class);
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new SimpleInMemoryUserDetailsManager();
    }

//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring ().antMatchers ("/images/**");
//        super.configure (web);
//    }

//    @Override
//    protected UserDetailsService userDetailsService() {
//        //Should have a real user saved in database with crypt pass
//        return new InMemoryUserDetailsManager (User.withUsername ("user")
//                .password ("{noop}userpass")
//                .roles ("ROLE_USER")
//                .build ());
//    }
}
