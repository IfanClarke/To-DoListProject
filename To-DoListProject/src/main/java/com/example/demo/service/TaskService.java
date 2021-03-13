package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.repository.TaskRepository;

@Service
public class TaskService {

	private TaskRepository taskRepository;
	
	@Autowired
	public TaskService(TaskRepository taskRepository) {
	this.taskRepository = taskRepository;	
	}
	
	
	
	
	
	
}

