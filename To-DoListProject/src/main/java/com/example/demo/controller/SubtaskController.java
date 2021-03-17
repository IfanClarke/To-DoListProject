package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.model.Subtask;

import com.example.demo.dto.SubtaskDTO;
import com.example.demo.service.SubtaskService;

@RestController
@RequestMapping(path = "/subtask")
public class SubtaskController {

	
	private SubtaskService subtaskService;
	
	
	@Autowired
	public SubtaskController(SubtaskService subtaskService) {
		this.subtaskService = subtaskService;
	}
	
	
	@GetMapping
	public ResponseEntity<List<SubtaskDTO>> getAllSubtasks() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Location", "http://localhost:8080/subtask/all");

		List<SubtaskDTO> data = subtaskService.readAllSubtasks();
		return new ResponseEntity<List<SubtaskDTO>>(data, httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SubtaskDTO> getSubtaskById(@PathVariable("id") int id) {
		SubtaskDTO subtask = subtaskService.readById(id);
		return new ResponseEntity<SubtaskDTO>(subtask, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<SubtaskDTO> createSubtask(@RequestBody Subtask subtask){
		SubtaskDTO newSubtask = subtaskService.createSubtask(subtask);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newSubtask.getSubtaskid()));
		
		return new ResponseEntity<SubtaskDTO>(newSubtask, headers, HttpStatus.CREATED);
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<SubtaskDTO> updateSubtask(@PathVariable("id") int subtaskid, @RequestBody Subtask subtask)
	{
		SubtaskDTO updateSubtask = subtaskService.updateSubtask(subtaskid, subtask);
		
		return new ResponseEntity<SubtaskDTO>(updateSubtask, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteSubtask(@PathVariable("id") int subtaskid){
		subtaskService.deleteSubtask(subtaskid);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
}
