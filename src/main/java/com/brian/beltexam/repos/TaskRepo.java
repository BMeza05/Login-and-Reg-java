package com.brian.beltexam.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brian.beltexam.models.Task;



@Repository
public interface TaskRepo extends CrudRepository <Task, Long> {
	
	List<Task> findAll();
	
	//Get one Task
}