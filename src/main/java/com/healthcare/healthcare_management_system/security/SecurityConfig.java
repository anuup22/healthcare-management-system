package com.healthcare.healthcare_management_system.security;

import com.healthcare.healthcare_management_system.model.User;
import com.healthcare.healthcare_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserRepository userRepository;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/login", "/register").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/doctor/**").hasRole("DOCTOR")
        .antMatchers("/patient/**").hasRole("PATIENT")
        .anyRequest().authenticated()
        .and()
        .formLogin().permitAll()
        .and()
        .logout().permitAll();
  }

  @Override
  protected UserDetailsService userDetailsService() {
    return username -> {
      User user = userRepository.findByEmail(username);
      if (user == null) {
        throw new UsernameNotFoundException("User not found");
      }
      return new org.springframework.security.core.userdetails.User(user.getEmail(),
          user.getPassword(),
          AuthorityUtils.createAuthorityList("ROLE_" + user.getRole().name()));
    };
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
