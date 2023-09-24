package com.todoapp.Todoapp.Todo;

import java.time.LocalDate;


public class TodoVO {

	public TodoVO() {
		super();
	}

	public TodoVO(long id, String description, String user, LocalDate targetDate, boolean done) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
		this.targetDate = targetDate;
		this.done = done;
	}

	
	private long id;
	private String description;
	private String user;
	private LocalDate targetDate;
	private boolean done;

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getUser() {
		return user;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public boolean isDone() {
		return done;
	}

	@Override
	public String toString() {
		return "TodoVO [id=" + id + ", description=" + description + ", userId=" + user + ", targetDate=" + targetDate
				+ ", done=" + done + "]";
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
