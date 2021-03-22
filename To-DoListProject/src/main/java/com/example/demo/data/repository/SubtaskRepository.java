package com.example.demo.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.data.model.Subtask;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Integer> {


	@Query (value = "SELECT * FROM Subtask", nativeQuery = true)
	public List<Subtask> getAllSubtasksSQL();

	@Query ("SELECT s FROM Subtask s")
	public List<Subtask> getAllSubtasksJPQL();

	@Query ("SELECT s FROM Subtask s WHERE s.subtaskid = ?1")
	public Subtask getSubtaskByIdJPQL(Integer subtaskid); 
}
