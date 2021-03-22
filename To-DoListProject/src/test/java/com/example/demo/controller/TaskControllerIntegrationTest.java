package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.mapper.TaskMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql" },
	 executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TaskControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	
	
}
