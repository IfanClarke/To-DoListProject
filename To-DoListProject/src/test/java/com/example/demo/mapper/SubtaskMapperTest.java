package com.example.demo.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.data.model.Subtask;
import com.example.demo.data.model.Task;
import com.example.demo.dto.SubtaskDTO;
import com.example.demo.dto.TaskDTO;

@SpringBootTest
public class SubtaskMapperTest {
	@Autowired
	SubtaskMapper subtaskMapper;
	Subtask subtask;
	SubtaskDTO subtaskDTO;
	
	@BeforeEach
	void init() {
		subtask = new Subtask(1, "Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
		subtaskDTO = new SubtaskDTO(1, "Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
	}
	
	@Test
	void mapToDTOTest() {
		assertThat(subtaskMapper.mapToDTO(subtask)).isEqualTo(subtaskDTO);
	}
	
	@Test
	void mapToTaskTest() {
		assertThat(subtaskMapper.mapToSubtask(subtaskDTO)).isEqualTo(subtask);
	}
}
