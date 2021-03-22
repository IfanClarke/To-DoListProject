package com.example.demo.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.data.model.Task;
import com.example.demo.dto.TaskDTO;

@SpringBootTest
public class TaskMapperTest {
    
	@Autowired
	TaskMapper taskMapper;
	Task task;
	TaskDTO taskDTO;
	
	@BeforeEach
	void init() {
		task = new Task("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
		taskDTO = new TaskDTO("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
	}
	
	@Test
	void mapToDTOTest() {
		assertThat(taskMapper.mapToDTO(task)).isEqualTo(taskDTO);
	}
	
	@Test
	void mapToTaskTest() {
		assertThat(taskMapper.mapToTask(taskDTO)).isEqualTo(task);
	}
}
