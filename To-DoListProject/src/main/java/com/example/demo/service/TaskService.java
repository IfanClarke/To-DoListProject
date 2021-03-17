package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.model.Task;
import com.example.demo.data.repository.TaskRepository;
import com.example.demo.dto.TaskDTO;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.exceptions.TaskNotFoundException;

@Service
public class TaskService {

	private TaskRepository taskRepository;
	private TaskMapper taskMapper;

	@Autowired
	public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
		this.taskRepository = taskRepository;
		this.taskMapper = taskMapper;
	}

	public List<TaskDTO> readAllTasks() {
		List<Task> tasks = taskRepository.findAll();
		List<TaskDTO> taskDTOs = new ArrayList<TaskDTO>();

		tasks.forEach(task -> taskDTOs.add(taskMapper.mapToDTO(task)));
		return taskDTOs;
	}

	public TaskDTO readById(Integer id) {
		Optional<Task> task = taskRepository.findById(id);

		if (task.isPresent()) {
			return taskMapper.mapToDTO(task.get());
		} else {
			throw new TaskNotFoundException("This task does not exist");
		}
	}

	public TaskDTO readByName(String task) {
		Task taskName = taskRepository.getTaskByNameJPQL(task);

		return taskMapper.mapToDTO(taskName);
	}

	public TaskDTO createTask(Task task) {
		Task newTask = taskRepository.save(task);

		return taskMapper.mapToDTO(newTask);
	}

	public TaskDTO updateTask(Integer id, Task task) throws EntityNotFoundException {
		Optional<Task> taskInDbOpt = taskRepository.findById(id);
		Task taskInDb;

		if (taskInDbOpt.isPresent()) {
			taskInDb = taskInDbOpt.get();
		} else {
			throw new TaskNotFoundException("This task does not exist");
		}

		taskInDb.setDescription(task.getDescription());
		taskInDb.setDone(task.getDone());
		taskInDb.setTask(task.getTask());
		taskInDb.setPriority(task.getPriority());
		taskInDb.setDeadline(task.getDeadline());

		Task updatedTask = taskRepository.save(taskInDb);

		return taskMapper.mapToDTO(updatedTask);

	}

	public boolean deleteTask(Integer id) {
		if (!taskRepository.existsById(id)) {
			throw new TaskNotFoundException();
		}
		taskRepository.deleteById(id);

		boolean exists = taskRepository.existsById(id);

		return !exists;
	}

}
