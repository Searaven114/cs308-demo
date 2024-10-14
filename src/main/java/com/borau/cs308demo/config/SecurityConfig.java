package com.borau.cs308demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(req->{
                     req

                         //TODO
                         // buradakiler ayrılması lazım, bi kısmı /api/auth/ olarak digerleri ise direkt thymeleaf sayfası oldugu icin
                         // komple ** ile alan ayrılması lazım, direkt MVC'nin View'i oluyorlar.
                         .requestMatchers("/login","/api/user","/api/user/profile","/", "/index", "/error", "/api/user", "api/login", "/register","/about")
                         .permitAll()

                             .requestMatchers("/api/product/**")
                             .hasAnyRole("ADMIN", "CUSTOMER", "PRODUCTMANAGER", "SALESMANAGER")

                         // Admin API
                         .requestMatchers("/api/admin/**")
                         .hasRole("ADMIN")


                         // Product manager API

                         // Sales manager API

                        // Actuator API
                        .requestMatchers("/actuator/**", "/startup-report")
                        .hasRole("ADMIN")

                        // API Documentation API
                        .requestMatchers(
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/v3/api-docs/**",
                            "/v2/api-docs/**"
                        ).permitAll()

                        // Global error page
                        .requestMatchers("/error")
                                .permitAll()


                        .anyRequest().authenticated();

                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                })
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(withDefaults())
//                .formLogin(form -> form
//                        .loginPage("/login")  // Custom login page
//                        .defaultSuccessUrl("/", true)  // Redirect to homepage after successful login
//                        .permitAll()
//                )


                // Şu an login,
                .httpBasic(withDefaults())
                .build();
    }

//    //Expose the AuthenticationManager bean to be autowired to services
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
