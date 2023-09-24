package com.todoapp.Todoapp.Todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.Todoapp.User.User;
import com.todoapp.Todoapp.User.UserRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private UserRepository userRepository;

	public List<Todo> retrieveAllTodos() {
		return todoRepository.findAll();
	}
	
	public Todo retrieveTodoById(Long id) {
		return todoRepository.findById(id).orElse(null);
	}
	
//	public Long addTodo(TodoVO todoVo) {
//		//System.out.println(todoVo.getUser());
//		User user=userRepository.findById(todoVo.getUser()).orElse(null);
//		if(user==null)return null;
//		//System.out.println(user);
//		Todo todo=new Todo(todoVo.getId(), todoVo.getDescription(), user, todoVo.getTargetDate(), todoVo.isDone());
//		todoRepository.save(todo);
//		return todo.getId();
//	}

	public void removeTodoById(long id) {
		todoRepository.deleteById(id);
	}

	public Long updateTodoById(Long id,TodoVO todoVo) {
		Todo td=retrieveTodoById(id);
		if(td==null)return null;
		Todo todo=new Todo(todoVo.getId(), todoVo.getDescription(), td.getUser(), todoVo.getTargetDate(), todoVo.isDone());
		todoRepository.save(todo);
		return id;
		
	}

	public List<Todo> retrieveTodoUsername(String username) {
		User user = userRepository.findByUsername(username);
		if(user==null) {
			return null;
		}
		List<Todo> todos = todoRepository.findByUser(user);
		
		return todos;
	}

	public Long addTodo(String username, TodoVO todoVo) {
		
		User user=userRepository.findByUsername(username);
		if(user==null)return null;
		Todo todo=new Todo(todoVo.getId(), todoVo.getDescription(), user, todoVo.getTargetDate(), todoVo.isDone());
		todoRepository.save(todo);
		return todo.getId();
	}
	

}
