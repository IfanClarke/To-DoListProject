package com.example.demo.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.example.demo.data.model.Task;
import com.example.demo.dto.TaskDTO;
import com.example.demo.service.TaskService;


@WebMvcTest(TaskController.class)
public class TaskControllerUnitTest {

	@Autowired
	private TaskController taskController;
	
	@MockBean
	private TaskService taskService;
	
	private List<Task> tasks;
	private List<TaskDTO> taskDTOs;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	
	
	@BeforeEach
	public void init() {
		validTask = new Task("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
		validTaskDTO = new TaskDTO("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
	   
		tasks = new ArrayList<Task>();
		taskDTOs = new ArrayList<TaskDTO>();
		
		tasks.add(validTask);
		taskDTOs.add(validTaskDTO);
	}
	
	@Test
	public void getAllTasksTest() {
		when(taskService.readAllTasks()).thenReturn(taskDTOs);
		
		ResponseEntity<List<TaskDTO>> response = new ResponseEntity<List<TaskDTO>>(taskDTOs, HttpStatus.OK);
		
		assertThat(response).isEqualTo(taskController.getAllTasks());
		
		verify(taskService, times(1)).readAllTasks();
	}
	
	@Test
	public void getTaskByIdTest() {

		when(taskService.readById(validTaskDTO.getId())).thenReturn(validTaskDTO);
		
		ResponseEntity<TaskDTO> response = 
				new ResponseEntity<TaskDTO>(validTaskDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(taskController.getTaskById(validTaskDTO.getId()));
		
		verify(taskService, times(1)).readById(validTaskDTO.getId());
	}
	
	@Test
	public void createTaskTest() {
		
		when(taskService.createTask(validTask)).thenReturn(validTaskDTO);
	
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validTaskDTO.getId()));	
	    
		ResponseEntity<TaskDTO> response = 
				new ResponseEntity<TaskDTO>(validTaskDTO, headers, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(taskController.createTask(validTask));
		
		verify(taskService, times(1)).createTask(validTask);
	
	}
	
	@Test
	public void deleteTaskTest() {
		when(taskService.deleteTask(validTask.getId())).thenReturn(true);
		
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	
	    assertThat(response).isEqualTo(taskController.deleteTask(validTask.getId()));
	
	    verify(taskService, times(1)).deleteTask(validTask.getId());
	}
	
	@Test
	public void updateTaskTest() {
		
		when(taskService.updateTask(validTask.getId(), validTask)).thenReturn(validTaskDTO);
		
		HttpHeaders headers = new HttpHeaders();
		
		ResponseEntity<TaskDTO> response = new ResponseEntity<TaskDTO>(validTaskDTO, headers, HttpStatus.OK);
		
		assertThat(response).isEqualTo(taskController.updateTask(validTask.getId(), validTask));
		
		verify(taskService, times(1)).updateTask(validTask.getId(), validTask);

	}
}
