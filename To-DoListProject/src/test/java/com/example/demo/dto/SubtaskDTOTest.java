package com.example.demo.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SubtaskDTOTest {
	private SubtaskDTO subtaskDTOTest;
    
    
    @Test
    public void taskConstructor(){
    	this.subtaskDTOTest = new SubtaskDTO(1, "Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
    }
    
    
    @Test
    public void taskConstructor2() {
    	this.subtaskDTOTest = new SubtaskDTO();
    }
    
    @Test
    public void hashCodeTest() {
    	SubtaskDTO subtaskDTO1 = new SubtaskDTO(1, "Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
    	SubtaskDTO subtaskDTO2 = new SubtaskDTO(1, "Clean House", "Ensure every room is clean", "2021-06-19 11:10", "Very high", true);
    	assertThat(subtaskDTO1).hasSameHashCodeAs(subtaskDTO2);
    }
}
