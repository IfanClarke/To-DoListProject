package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.example.demo.data.model.Subtask;

import com.example.demo.data.repository.SubtaskRepository;
import com.example.demo.dto.SubtaskDTO;

import com.example.demo.mapper.SubtaskMapper;

@SpringBootTest
public class SubtaskServiceIntegrationTest {

	@Autowired
	private SubtaskService subtaskService;
	
	@Autowired
	private SubtaskRepository subtaskRepository;
	
	@Autowired
	private SubtaskMapper subtaskMapper;
	
	
	private List<Subtask> subtasks;
	private List<SubtaskDTO> subtaskDTOs;
	
	private Subtask validSubtask;
	private SubtaskDTO validSubtaskDTO;
	
	@BeforeEach
	public void init() {
		validSubtask = new Subtask();

	   
		subtasks = new ArrayList<Subtask>();
		subtaskDTOs = new ArrayList<SubtaskDTO>();
		
		subtaskRepository.deleteAll();
		
		validSubtask = subtaskRepository.save(validSubtask);
		
		validSubtaskDTO = subtaskMapper.mapToDTO(validSubtask);
		
		subtasks.add(validSubtask);
		subtaskDTOs.add(validSubtaskDTO);
		
	}
	
	@Test
	public void readAllSubtasksTest() {
		List<SubtaskDTO> subtasksInDb = subtaskService.readAllSubtasks();
		
		assertThat(subtaskDTOs).isEqualTo(subtasksInDb);
	}
	
	@Test
    public void createSubtaskTest() {
    	Subtask newSubtask = new Subtask(1, "Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
		SubtaskDTO expectedSubtaskDTO = new SubtaskDTO(1, "Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
		
		SubtaskDTO savedSubtask = subtaskService.createSubtask(newSubtask);
		
		expectedSubtaskDTO.setSubtaskid(savedSubtask.getSubtaskid());
		
		assertThat(savedSubtask).isEqualTo(expectedSubtaskDTO);
		
    }
	   @Test
	    public void deleteSubtaskTest() {
	    	assertThat(true).isEqualTo(subtaskService.deleteSubtask(validSubtask.getSubtaskid()));
	    }
}
