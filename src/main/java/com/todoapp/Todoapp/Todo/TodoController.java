package com.todoapp.Todoapp.Todo;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	

	@RequestMapping("/todos")
	public List<Todo> retrieveAllTodos() {
		return todoService.retrieveAllTodos();
	}

	@RequestMapping("/todos/{id}")
	public Todo retrieveTodoById(@PathVariable Long id) {
		Todo todo = todoService.retrieveTodoById(id);
		if (todo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return todo;
	}

//	@RequestMapping(value = "/todos", method = RequestMethod.POST)
//	public ResponseEntity<Object> addTodo(@RequestBody TodoVO todo) {
//		Long todoId = todoService.addTodo(todo);
//		if(todoId==null) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		}
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{todoId}").buildAndExpand(todoId)
//				.toUri();
//		return ResponseEntity.created(location).build();
//	}
	
	@RequestMapping(value = "/users/{username}/todos", method = RequestMethod.POST)
	public ResponseEntity<Object> addTodoforUser(@PathVariable String username,@RequestBody TodoVO todo) {
		Long todoId = todoService.addTodo(username,todo);
		System.out.println(todoId);
		if(todoId==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{todoId}").buildAndExpand(todoId)
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeTodoById(@PathVariable Long id) {
		todoService.removeTodoById(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/todos/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateTodoById(@PathVariable Long id, @RequestBody TodoVO todo) {
		Long todoId = todoService.updateTodoById(id, todo);
		System.out.println(todoId);
		if (todoId == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping("/users/{username}/todos")
	//@PreAuthorize("#username == authentication.principal.username")
	public List<Todo> retrieveTodoByUsername(@PathVariable String username) {
		List<Todo> todos = todoService.retrieveTodoUsername(username);
		if (todos == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return todos;
	}

}
