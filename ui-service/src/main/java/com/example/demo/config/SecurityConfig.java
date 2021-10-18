package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import com.example.demo.converter.KeycloakRoleConverter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JwtAuthenticationConverter coverter = new JwtAuthenticationConverter();
		coverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
		
		http.cors()
		.and().authorizeRequests().anyRequest().authenticated()
		.and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(coverter);
	}

	
}
