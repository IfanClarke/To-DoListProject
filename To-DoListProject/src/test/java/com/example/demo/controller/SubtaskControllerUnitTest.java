package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.data.model.Subtask;
import com.example.demo.dto.SubtaskDTO;
import com.example.demo.service.SubtaskService;

@WebMvcTest(SubtaskController.class)
public class SubtaskControllerUnitTest {

	@Autowired
	private SubtaskController subtaskController;
	
	@MockBean
	private SubtaskService subtaskService;
	
	private List<Subtask> subtasks;
	private List<SubtaskDTO> subtaskDTOs;
	
	private Subtask validSubtask;
	private SubtaskDTO validSubtaskDTO;
	
	
	@BeforeEach
	public void init() {
		validSubtask = new Subtask(1, "Clean kitchen", "dust and sweep", "2021-06-19 11:10", "Very high", true);
		validSubtaskDTO = new SubtaskDTO(1, "Clean kitchen", "dust and sweep", "2021-06-19 11:10", "Very high", true);
	   
		subtasks = new ArrayList<Subtask>();
		subtaskDTOs = new ArrayList<SubtaskDTO>();
		
		subtasks.add(validSubtask);
		subtaskDTOs.add(validSubtaskDTO);
	}
	
	@Test
	public void getAllSubtasksTest() {
		when(subtaskService.readAllSubtasks()).thenReturn(subtaskDTOs);
		
		ResponseEntity<List<SubtaskDTO>> response = new ResponseEntity<List<SubtaskDTO>>(subtaskDTOs, HttpStatus.OK);
		
		assertThat(response).isEqualTo(subtaskController.getAllSubtasks());
		
		verify(subtaskService, times(1)).readAllSubtasks();
	}
	
	@Test
	public void getSubtaskByIdTest() {

		when(subtaskService.readById(validSubtaskDTO.getSubtaskid())).thenReturn(validSubtaskDTO);
		
		ResponseEntity<SubtaskDTO> response = 
				new ResponseEntity<SubtaskDTO>(validSubtaskDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(subtaskController.getSubtaskById(validSubtaskDTO.getSubtaskid()));
		
		verify(subtaskService, times(1)).readById(validSubtaskDTO.getSubtaskid());
	}
	
	@Test
	public void createSubtaskTest() {
		
		when(subtaskService.createSubtask(validSubtask)).thenReturn(validSubtaskDTO);
	
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validSubtaskDTO.getSubtaskid()));	
	    
		ResponseEntity<SubtaskDTO> response = 
				new ResponseEntity<SubtaskDTO>(validSubtaskDTO, headers, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(subtaskController.createSubtask(validSubtask));
		
		verify(subtaskService, times(1)).createSubtask(validSubtask);
	
	}
	
	@Test
	public void deleteSubtaskTest() {
		when(subtaskService.deleteSubtask(validSubtask.getTaskid())).thenReturn(true);
		
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	
	    assertThat(response).isEqualTo(subtaskController.deleteSubtask(validSubtask.getTaskid()));
	
	    verify(subtaskService, times(1)).deleteSubtask(validSubtask.getTaskid());
	}
	
	@Test
	public void updateTaskTest() {
		
		when(subtaskService.updateSubtask(validSubtask.getTaskid(), validSubtask)).thenReturn(validSubtaskDTO);
		
		HttpHeaders headers = new HttpHeaders();
		
		ResponseEntity<SubtaskDTO> response = new ResponseEntity<SubtaskDTO>(validSubtaskDTO, headers, HttpStatus.OK);
		
		assertThat(response).isEqualTo(subtaskController.updateSubtask(validSubtask.getTaskid(), validSubtask));
		
		verify(subtaskService, times(1)).updateSubtask(validSubtask.getTaskid(), validSubtask);

	}
}
