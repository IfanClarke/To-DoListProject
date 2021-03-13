package com.example.demo.data.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.data.model.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	

	public Task findById(int id);
	
	@Query(value = "SELECT * FROM task", nativeQuery = true)
	public List<Task> getAllTasksSQL();
	
	
	
	@Query("SELECT t FROM Task t")
	public List<Task> getAllTasksJPQL();
	
	
	
	@Query("SELECT t FROM Duck t WHERE t.id = ?1")
	public Task getDuckByIdJPQL(int id);
}
