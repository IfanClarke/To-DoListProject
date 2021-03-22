package com.example.exceptions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.demo.exceptions.*;

public class TaskNotFoundExceptionTest {

	@Test
    public void TaskNotFoundExceptionUnitTest() {
		TaskNotFoundException tnfeTest = new TaskNotFoundException();
		
		assertThat(tnfeTest).isNotNull().isInstanceOf(TaskNotFoundException.class);
	}
	
	@Test
    public void TaskNotFoundExceptionUnitTest1() {
		TaskNotFoundException tnfeTest = new TaskNotFoundException("this task is nowhere to be found!");
		
		assertThat(tnfeTest).isNotNull().isInstanceOf(TaskNotFoundException.class);
	}
	
	
}
