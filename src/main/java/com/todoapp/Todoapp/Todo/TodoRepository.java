package com.todoapp.Todoapp.Todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.Todoapp.User.User;

public interface TodoRepository extends JpaRepository<Todo, Long>{
	List<Todo> findByUser(User user);
}
