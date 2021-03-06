package com.example.demo.data.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.data.model.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	/*@Query(value = "SELECT * FROM Task", nativeQuery = true)
	public List<Task> getAllTasksSQL();

	@Query("SELECT t FROM Task t")
	public List<Task> getAllTasksJPQL();*/

	@Query("SELECT t FROM Task t WHERE t.id = ?1")
	public Task getTaskByIdJPQL(Integer id);
	
	/*@Query("SELECT t from Task t WHERE t.task = ?1")
	public Task getTaskbyNameJPQL(String task);*/
}
