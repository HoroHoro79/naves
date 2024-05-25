package com.proyecto.naves.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.Data;

@Configuration
@Data
public class SecurityConfig {
	
	
	
	private String generatedPassword = "";
	
	private String usuario = "";
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
		http.httpBasic(withDefaults())
				.authorizeHttpRequests()
				.requestMatchers("/doc/swagger-ui/index.html**").permitAll()
				.requestMatchers("/api/naves/v1/status/**").permitAll()
				.requestMatchers("/api/security/v1/credentials/**").permitAll()
				.requestMatchers("/api/naves/v1/**").authenticated()
				.requestMatchers("/api/security/v1/**").authenticated();
		return http.build();
	}
	
	   @Bean
	    public UserDetailsService userDetailsService() {
			generatedPassword = UUID.randomUUID().toString();
			usuario = "admin";
		   UserDetails user = User.withUsername(usuario)
	                .password(passwordEncoder().encode(generatedPassword))
	                .roles("ADMIN")
	                .build();

	        return new InMemoryUserDetailsManager(user);
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}