package com.example.demo.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.example.demo.data.model.Task;
import com.example.demo.data.repository.TaskRepository;
import com.example.demo.dto.TaskDTO;
import com.example.demo.mapper.TaskMapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class TaskServiceIntegrationTest {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskMapper taskMapper;
	
	
	private List<Task> tasks;
	private List<TaskDTO> taskDTOs;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() {
		validTask = new Task("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);

	   validTask.setSubtaskLink(List.of());
		tasks = new ArrayList<Task>();
		taskDTOs = new ArrayList<TaskDTO>();
		
		taskRepository.deleteAll();
		
		validTask = taskRepository.save(validTask);
		
		validTaskDTO = taskMapper.mapToDTO(validTask);
		
		tasks.add(validTask);
		taskDTOs.add(validTaskDTO);
		
		
	}
	
	@Test
	public void readAllTasksTest() {
		List<TaskDTO> tasksInDb = taskService.readAllTasks();
		
	    
		assertThat(taskDTOs).isEqualTo(tasksInDb);
	}

    @Test
    public void createTaskTest() {
    	Task newTask = new Task("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
		TaskDTO expectedTaskDTO = new TaskDTO("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
		
		TaskDTO savedTask = taskService.createTask(newTask);
		
		expectedTaskDTO.setId(savedTask.getId());
		
		assertThat(savedTask).isEqualTo(expectedTaskDTO);
		
    }
    
    @Test
    public void deleteTaskTest() {
    	assertThat(true).isEqualTo(taskService.deleteTask(validTask.getId()));
    }
    
    
    
}
