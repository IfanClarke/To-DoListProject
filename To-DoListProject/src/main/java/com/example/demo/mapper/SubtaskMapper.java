package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.model.Subtask;
import com.example.demo.dto.SubtaskDTO;


@Component
public class SubtaskMapper {

	private ModelMapper modelMapper;
	
	@Autowired
	public SubtaskMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public SubtaskDTO mapToDTO(Subtask subtask) {
		return this.modelMapper.map(subtask, SubtaskDTO.class);
	}
	
	public Subtask mapToSubtask(SubtaskDTO subtaskDTO) {
		return this.modelMapper.map(subtaskDTO, Subtask.class);
	}
}
