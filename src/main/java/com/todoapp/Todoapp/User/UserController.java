package com.todoapp.Todoapp.User;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping(path="/basicauth")
	public ResponseEntity<Object> basicAuth(){
		return ResponseEntity.ok().build();
	}
	
	
	@RequestMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> retrieveAllUsers(){
		return userService.retrieveAllUsers();
	}
	
	@RequestMapping("/users/{id}")
	public User retrieveUserById(@PathVariable long id) {
		User user =  userService.retrieveUserById(id);
		if(user==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return user;
	}
	
	@RequestMapping(value="/users/register",method = RequestMethod.POST)
	public ResponseEntity<Object> addUser(@RequestBody User user){
		Long userId=userService.addUser(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{userId}").buildAndExpand(userId).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value="/users/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeUserById(@PathVariable long id){
		userService.removeUserById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/users/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Object> updateUserById(@PathVariable long id,@RequestBody User user){
		Long userId = userService.updateUserById(id,user);
		if(userId==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().build();
	}
	
	
}
