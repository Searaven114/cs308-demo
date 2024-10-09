package com.borau.cs308demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing, enable in production
                .authorizeHttpRequests(req->{
                     req.requestMatchers("/login","/api/user","/api/user/profile","/", "/index", "/error", "/api/user", "api/login", "/register","/about").permitAll();
                     req.requestMatchers("/api/admin/**").hasRole("ADMIN");
                     req.anyRequest().authenticated();

                })
                .formLogin(withDefaults())
//                .formLogin(form -> form
//                        .loginPage("/login")  // Custom login page
//                        .defaultSuccessUrl("/", true)  // Redirect to homepage after successful login
//                        .permitAll()
//                )
                .httpBasic(withDefaults())
                .build();
    }

    //Expose the AuthenticationManager bean to be autowired to services
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
