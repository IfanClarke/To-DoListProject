package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.data.model.Task;
import com.example.demo.data.repository.TaskRepository;
import com.example.demo.dto.TaskDTO;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.service.TaskService;


@ExtendWith(MockitoExtension.class)
public class TaskServiceUnitTest {

	@InjectMocks
	private TaskService taskService;
	
	@Mock
	private TaskRepository taskRepository;
	
	@Mock
	private TaskMapper taskMapper;
	
	private List<Task> tasks;
	private List<TaskDTO> taskDTOs;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() {
		validTask = new Task();
		validTaskDTO = new TaskDTO();
	   
		tasks = new ArrayList<Task>();
		taskDTOs = new ArrayList<TaskDTO>();
		
		tasks.add(validTask);
		taskDTOs.add(validTaskDTO);
	}
	
	@Test
	public void readAllTasksTest() {
		when(taskRepository.findAll()).thenReturn(tasks);
		when(taskMapper.mapToDTO(validTask)).thenReturn(validTaskDTO);
		
		
		assertThat(taskDTOs).isEqualTo(taskService.readAllTasks());
		

		verify(taskRepository, times(1)).findAll();
		verify(taskMapper, times(1)).mapToDTO(validTask);
		
	}
	
	 @Test
	public void readByIdTest() {
		when(taskRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validTask));
		when(taskMapper.mapToDTO(validTask)).thenReturn(validTaskDTO);
		
		assertThat(validTaskDTO).isEqualTo(taskService.readById(validTaskDTO.getId()));
		
		verify(taskRepository, times(1)).findById(validTaskDTO.getId());
		verify(taskMapper, times(1)).mapToDTO(validTask);
	
	} 
	
	@Test
	public void updateTaskTest() {
		Task updatedTask = new Task();
		TaskDTO updatedTaskDTO = new TaskDTO();
	   
		when(taskRepository.findById(Mockito.any(Integer.class)))
		.thenReturn(Optional.of(validTask));
	
	when(taskRepository.save(Mockito.any(Task.class)))
		.thenReturn(updatedTask);
	
	when(taskMapper.mapToDTO(Mockito.any(Task.class)))
		.thenReturn(updatedTaskDTO);
	
	TaskDTO toTestDTO = taskService.updateTask(validTask.getId(), updatedTask);
	
	assertThat(updatedTaskDTO).isEqualTo(toTestDTO);
		
	}
	
	@Test
	public void createTaskTest() {
		when(taskRepository.save(Mockito.any(Task.class))).thenReturn(validTask);
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(validTaskDTO);
		
		assertThat(validTaskDTO).isEqualTo(taskService.createTask(validTask));
		
		verify(taskRepository, times(1)).save(validTask);
		verify(taskMapper, times(1)).mapToDTO(validTask);
	}
	
	
}
