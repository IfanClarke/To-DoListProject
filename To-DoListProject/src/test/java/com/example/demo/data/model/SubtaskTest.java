package com.example.demo.data.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.demo.dto.SubtaskDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class SubtaskTest {

	private Subtask subtaskTest;

	@Test
	public void subtaskConstructor() {
		this.subtaskTest = new Subtask();
	}

	@Test
	public void subtaskConstructor2() {
		this.subtaskTest = new Subtask(1, "clean", "wash-up", "Thursday", "very high", true);
	}

	@Test
	public void subtaskConstructor3() {
		this.subtaskTest = new Subtask(1, 1, "clean the kitchen", "sweep and mop", "today", "high", true, null);
	}

	@Test
	public void hashCodeTest() {
		Subtask subtask1 = new Subtask(1, "clean", "wash-up", "Thursday", "very high", true);
		Subtask subtask2 = new Subtask(1, "clean", "wash-up", "Thursday", "very high", true);
		assertThat(subtask1).hasSameHashCodeAs(subtask2);
	}

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Task.class).withPrefabValues(Subtask.class, new Subtask(),
				new Subtask(1, "clean", "wash-up", "Thursday", "very high", true));
	}

}
