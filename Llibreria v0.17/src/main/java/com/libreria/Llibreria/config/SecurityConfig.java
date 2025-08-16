package com.libreria.Llibreria.config;

import com.libreria.Llibreria.service.UserDetailsServiceImpl;
import com.libreria.Llibreria.security.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // <-- agrega esto
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired private UserDetailsServiceImpl userDetailsService;
  @Autowired private CustomSuccessHandler customSuccessHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(
          "/", "/index.html",
          "/login", "/login.html",
          "/registro", "/registro.html",
          "/excel.html", "/excel",          // <-- permite la página
          "/css/**", "/js/**", "/img/**"
        ).permitAll()
        .requestMatchers(HttpMethod.POST, "/api/excel/upload").permitAll() // <-- permite el upload
        .requestMatchers("/Dashboard_Administrativo.html").hasRole("ADMIN")
        .anyRequest().authenticated()
      )
      .formLogin(login -> login
        .loginPage("/login.html")
        .loginProcessingUrl("/login")
        .successHandler(customSuccessHandler)
        .failureUrl("/login.html?error=true")
        .permitAll()
      )
      .logout(logout -> logout
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login.html")
        .permitAll()
      )
      // Opción rápida: desactivar CSRF SOLO para estos endpoints
      .csrf(csrf -> csrf.ignoringRequestMatchers(
        "/login", "/logout", "/registro",
        "/api/excel/upload"                 // <-- excluye el upload de CSRF
      ))
      .authenticationProvider(authProvider())
      .build();
  }

  @Bean
  public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth.setUserDetailsService(userDetailsService);
    auth.setPasswordEncoder(noOpPasswordEncoder());
    return auth;
  }

  @Bean
  @SuppressWarnings("deprecation")
  public PasswordEncoder noOpPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
