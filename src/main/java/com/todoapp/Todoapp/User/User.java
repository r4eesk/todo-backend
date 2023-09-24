package com.todoapp.Todoapp.User;

import java.util.List;

import com.todoapp.Todoapp.Todo.Todo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class User {

	public User(long id, String username, String password, List<Todo> todos, Role authority) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.todos = todos;
		this.authority = authority;
	}

	public User() {
		super();
	}

	@Id
	@GeneratedValue(generator="user_seq")
	@SequenceGenerator(name="user_seq",sequenceName="user_seq", allocationSize=1)
	private long id;

	private String username;

	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Todo> todos;

	private Role authority;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public Role getAuthority() {
		return authority;
	}

	public void setAuthority(Role authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", todos=" + todos
				+ ", authority=" + authority + "]";
	}
}

enum Role {
	ADMIN, USER
}
