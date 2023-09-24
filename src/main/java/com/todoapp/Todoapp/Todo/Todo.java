package com.todoapp.Todoapp.Todo;

import java.time.LocalDate;

import com.todoapp.Todoapp.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
public class Todo {

	public Todo() {
		super();
	}

	public Todo(long id, String description, User user, LocalDate targetDate, boolean done) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
		this.targetDate = targetDate;
		this.done = done;
	}

	@Id
	@GeneratedValue(generator="todo_seq")
	@SequenceGenerator(name="todo_seq",sequenceName="todo_seq", allocationSize=1)
	private long id;
	
	@NotNull
	private String description;
	
	@ManyToOne
	@NotNull
	private User user;
	
	@NotNull
	private LocalDate targetDate;
	private boolean done;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean getDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", username=" + user+ ", targetDate="
				+ targetDate + ", isDone=" + done + "]";
	}

}
