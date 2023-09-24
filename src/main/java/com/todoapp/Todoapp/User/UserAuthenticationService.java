package com.todoapp.Todoapp.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String password=null;
		List<GrantedAuthority> authorities =  null;
		User user = userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("No user exists with username "+username);
		}
		password=user.getPassword();
		authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getAuthority()));
		return new org.springframework.security.core.userdetails.User(username, password, authorities);
		
	}
	
	
}
