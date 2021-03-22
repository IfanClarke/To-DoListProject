package com.example.demo.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.demo.data.model.Subtask;
import com.example.demo.data.model.Task;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TaskDTOTest {
	 private TaskDTO taskDTOTest;
	    
	    
	    @Test
	    public void taskConstructor(){
	    	this.taskDTOTest = new TaskDTO("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
	    }
	    
	    @Test
	    public void taskConstructor2(){
	    	this.taskDTOTest = new TaskDTO(1, "Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true, null);
	    }
	    
	    @Test
	    public void taskConstructor3() {
	    	this.taskDTOTest = new TaskDTO();
	    }
	    
	    @Test
	    public void hashCodeTest() {
	    	TaskDTO taskDTO1 = new TaskDTO("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
	    	TaskDTO taskDTO2 = new TaskDTO("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
	    	assertThat(taskDTO1).hasSameHashCodeAs(taskDTO2);
	    }
	    
	    @Test
	    public void toStringTest() {
	    	TaskDTO taskDTOTest = new TaskDTO("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
	        
	    	assertThat(taskDTOTest).hasToString("TaskDTO [id=" + taskDTOTest.getId() + ", task=" + taskDTOTest.getTask() + ", description=" + taskDTOTest.getDescription() + ", deadline=" + taskDTOTest.getDeadline()
					+ ", priority=" + taskDTOTest.getPriority() + ", done=" + taskDTOTest.getDone() + ", subtaskLink=" + taskDTOTest.getSubtaskLink() + "]");
		}
	    
	    @Test
	    public void testEquals() {
	    	    EqualsVerifier.simple().forClass(TaskDTO.class).withPrefabValues(SubtaskDTO.class, new SubtaskDTO(2, "dry", "wash-up", "Thursday", "very high", true), new SubtaskDTO(1, "clean", "wash-up", "Thursday", "very high", true));
	    }
}
