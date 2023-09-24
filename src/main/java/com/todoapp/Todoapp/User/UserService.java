package com.todoapp.Todoapp.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	public User retrieveUserById(Long id) {
		
		return userRepository.findById(id).orElse(null);
	}

	public long addUser(User user) {
		User testUser = userRepository.findByUsername(user.getUsername());
		if(testUser!=null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists!");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return user.getId();
	}

	public void removeUserById(long id) {
		userRepository.deleteById(id);
	}

	public Long updateUserById(long id,User user) {
		User usr=retrieveUserById(id);
		if(usr==null)return null;
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return id;	
	}	

}
