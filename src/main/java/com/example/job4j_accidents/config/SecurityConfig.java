package com.example.job4j_accidents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private DataSource ds;

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> {
                    try {
                        authz
                                .requestMatchers("/login")
                                .permitAll()
                                .requestMatchers("/**")
                                .hasAnyRole("USER", "ADMIN")
                                .and()
                                .formLogin()
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                                .failureUrl("/login?error=true")
                                .and()
                                .logout()
                                .logoutSuccessUrl("/login?logout=true")
                                .invalidateHttpSession(true)
                                .permitAll()
                                .and()
                                .csrf()
                                .disable();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(ds);
        manager.setUsersByUsernameQuery(
                "select username, password, enabled from users where username = ?");
        manager.setAuthoritiesByUsernameQuery(
                "select username, authority from authorities where username = ?");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
