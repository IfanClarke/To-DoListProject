package com.example.demo.data.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;


public class TaskTest {
    private Task taskTest;
    
    
    @Test
    public void taskConstructor(){
    	this.taskTest = new Task("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
    }
    
    @Test
    public void taskConstructor2(){
    	this.taskTest = new Task(1, "Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true, null);
    }
    
    @Test
    public void taskConstructor3() {
    	this.taskTest = new Task();
    }
    
    @Test
    public void hashCodeTest() {
    	Task task1 = new Task("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
    	Task task2 = new Task("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
    	assertThat(task1).hasSameHashCodeAs(task2);
    }
   
    @Test
    public void toStringTest() {
    	Task taskTest = new Task("Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
        
    	assertThat(taskTest).hasToString("Task [id=" + taskTest.getId() + ", task=" + taskTest.getTask() + ", description=" + taskTest.getDescription() + ", deadline=" + taskTest.getDeadline()
				+ ", priority=" + taskTest.getPriority() + ", done=" + taskTest.getDone() + ", subtaskLink=" + taskTest.getSubtaskLink() + "]");
	}
    
    @Test
    public void testEquals() {
    	    EqualsVerifier.simple().forClass(Task.class).withPrefabValues(Subtask.class, new Subtask(), new Subtask(1, "clean", "wash-up", "Thursday", "very high", true));
    }
	
    
     
}
