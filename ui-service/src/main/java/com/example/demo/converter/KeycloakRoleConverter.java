package com.example.demo.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@Override
	public Collection<GrantedAuthority> convert(Jwt source) {
		Map<String, Object> realm_access = (Map<String, Object>) source.getClaims().get("realm_access");

		if (realm_access == null || realm_access.isEmpty()) {
			return new ArrayList<>();
		}

		Collection<GrantedAuthority> authorities = ((List<String>) realm_access.get("roles")).stream()
				.map(role -> "ROLE_" + role).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

		String scope = (String) source.getClaims().get("scope");
		Collection<GrantedAuthority> scopes = Arrays.asList(scope.split(" ")).stream().map(sc -> "SCOPE_" + sc)
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		authorities.addAll(scopes);

		return authorities;
	}

}
