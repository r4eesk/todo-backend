package com.todoapp.Todoapp.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;



public class JwtRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    
    public Collection<GrantedAuthority> convert(Jwt jwt) {
    	String[] roles = jwt.getClaims().get("roles").toString().split(" ");
        
        if (roles == null || roles.length==0) {
            return new ArrayList<>();
        }
        
        return Arrays.stream(roles).map(SimpleGrantedAuthority::new) .collect(Collectors.toList());
    }
}