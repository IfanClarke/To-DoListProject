package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.model.Subtask;
import com.example.demo.data.repository.SubtaskRepository;

import com.example.demo.dto.SubtaskDTO;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.mapper.SubtaskMapper;

@Service
public class SubtaskService {

	private SubtaskRepository subtaskRepository;
	private SubtaskMapper subtaskMapper;

	@Autowired
	public SubtaskService(SubtaskRepository subtaskRepository, SubtaskMapper subtaskMapper) {
		this.subtaskRepository = subtaskRepository;
		this.subtaskMapper = subtaskMapper;
	}

	public List<SubtaskDTO> readAllSubtasks() {
		List<Subtask> subtasks = subtaskRepository.findAll();
		List<SubtaskDTO> subtaskDTOs = new ArrayList<SubtaskDTO>();

		subtasks.forEach(subtask -> subtaskDTOs.add(subtaskMapper.mapToDTO(subtask)));
		return subtaskDTOs;
	}

	public SubtaskDTO readById(Integer id) {
		Optional<Subtask> subtask = subtaskRepository.findById(id);

		if (subtask.isPresent()) {
			return subtaskMapper.mapToDTO(subtask.get());
		} else {
			throw new TaskNotFoundException("This task does not exist");
		}
	}

	public SubtaskDTO readByName(String subtask) {
		Subtask subtaskName = subtaskRepository.getSubtaskByNameJPQL(subtask);

		return subtaskMapper.mapToDTO(subtaskName);
	}

	public SubtaskDTO createSubtask(Subtask subtask) {
		Subtask newSubtask = subtaskRepository.save(subtask);

		return subtaskMapper.mapToDTO(newSubtask);
	}

	public SubtaskDTO updateSubtask(Integer id, Subtask subtask) throws EntityNotFoundException {
		Optional<Subtask> subtaskInDbOpt = subtaskRepository.findById(id);
		Subtask subtaskInDb;

		if (subtaskInDbOpt.isPresent()) {
			subtaskInDb = subtaskInDbOpt.get();
		} else {
			throw new TaskNotFoundException("This task does not exist");
		}

		subtaskInDb.setSubtaskid(subtask.getTaskid());
		subtaskInDb.setDescription(subtask.getDescription());
		subtaskInDb.setDone(subtask.getDone());
		subtaskInDb.setTask(subtask.getTask());
		subtaskInDb.setPriority(subtask.getPriority());
		subtaskInDb.setDeadline(subtask.getDeadline());

		Subtask updatedSubtask = subtaskRepository.save(subtaskInDb);

		return subtaskMapper.mapToDTO(updatedSubtask);

	}

	public boolean deleteSubtask(Integer id) {
		if (!subtaskRepository.existsById(id)) {
			throw new TaskNotFoundException();
		}
		subtaskRepository.deleteById(id);

		boolean exists = subtaskRepository.existsById(id);

		return !exists;
	}

}
