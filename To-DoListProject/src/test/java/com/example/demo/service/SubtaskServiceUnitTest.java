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

import com.example.demo.data.model.Subtask;
import com.example.demo.data.model.Task;
import com.example.demo.data.repository.SubtaskRepository;
import com.example.demo.data.repository.TaskRepository;
import com.example.demo.dto.SubtaskDTO;
import com.example.demo.mapper.SubtaskMapper;

@ExtendWith(MockitoExtension.class)
public class SubtaskServiceUnitTest {

	@InjectMocks
	private SubtaskService subtaskService;
	
	@Mock
	private SubtaskRepository subtaskRepository;
	
	@Mock
	private SubtaskMapper subtaskMapper;
	
	private List<Subtask> subtasks;
	private List<SubtaskDTO> subtaskDTOs;
	
	private Subtask validSubtask;
	private SubtaskDTO validSubtaskDTO;
	
	@BeforeEach
	public void init() {
		validSubtask = new Subtask(1, "Clean Kitchen", "Sweep and mop", "2021-06-19 11:10", "Very high", true);
		validSubtaskDTO = new SubtaskDTO(1, "Clean Kitchen", "Sweep and mop", "2021-06-19 11:10", "Very high", true);
	   
		subtasks = new ArrayList<Subtask>();
		subtaskDTOs = new ArrayList<SubtaskDTO>();
		
		subtasks.add(validSubtask);
		subtaskDTOs.add(validSubtaskDTO);
	}
	
	@Test
	public void readAllSubtasksTest() {
		when(subtaskRepository.findAll()).thenReturn(subtasks);
		when(subtaskMapper.mapToDTO(validSubtask)).thenReturn(validSubtaskDTO);
		
		
		assertThat(subtaskDTOs).isEqualTo(subtaskService.readAllSubtasks()); 		

		verify(subtaskRepository, times(1)).findAll();
		verify(subtaskMapper, times(1)).mapToDTO(validSubtask);
		
	}
	
	@Test
	public void readBySubtaskIdTest() {
		when(subtaskRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validSubtask));
		when(subtaskMapper.mapToDTO(validSubtask)).thenReturn(validSubtaskDTO);
		
		assertThat(validSubtaskDTO).isEqualTo(subtaskService.readById(Mockito.any(Integer.class)));
		
		verify(subtaskRepository, times(1)).findById(validSubtaskDTO.getSubtaskid());
		verify(subtaskMapper, times(1)).mapToDTO(validSubtask);
	}
	
	@Test
	public void updateSubtaskTest() {
		Subtask updatedSubtask = new Subtask(1, "Clean Living Room", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
		SubtaskDTO updatedSubtaskDTO = new SubtaskDTO(1, "Clean Living Room", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
	   
		when(subtaskRepository.findById(Mockito.any(Integer.class)))
		.thenReturn(Optional.of(validSubtask));
	
	when(subtaskRepository.save(Mockito.any(Subtask.class)))
		.thenReturn(updatedSubtask);
	
	when(subtaskMapper.mapToDTO(Mockito.any(Subtask.class)))
		.thenReturn(updatedSubtaskDTO);
	
	SubtaskDTO toTestDTO = subtaskService.updateSubtask(validSubtask.getSubtaskid(), updatedSubtask);
	
	assertThat(updatedSubtaskDTO).isEqualTo(toTestDTO);
		
	}
	
	@Test
	public void createSubtaskTest() {
		when(subtaskRepository.save(Mockito.any(Subtask.class))).thenReturn(validSubtask);
		when(subtaskMapper.mapToDTO(Mockito.any(Subtask.class))).thenReturn(validSubtaskDTO);
		
		assertThat(validSubtaskDTO).isEqualTo(subtaskService.createSubtask(validSubtask));
		
		verify(subtaskRepository, times(1)).save(validSubtask);
		verify(subtaskMapper, times(1)).mapToDTO(validSubtask);
	}
	
	
}
