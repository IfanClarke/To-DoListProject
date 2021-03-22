package com.example.demo.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "This task was not found")
public class TaskNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = -67053510114331656L;

	public TaskNotFoundException() {
		super();
	}

	public TaskNotFoundException(String message) {
		super(message);
	}
}
