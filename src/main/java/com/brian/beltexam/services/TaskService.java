package com.brian.beltexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brian.beltexam.models.Task;
import com.brian.beltexam.repos.TaskRepo;



@Service
public class TaskService {

	@Autowired
	private TaskRepo taskRepo;

//	@Autowired
//	private UserRepo userRepo;

	// create Task
	public Task createTask(Task t) {
//		User thisUser = User;
//		i.setUser(thisUser);
		return taskRepo.save(t);
	}

	// one task
	public Task getOneTask(Long id) {
		Optional<Task> optionalTask = taskRepo.findById(id);
		if (optionalTask.isPresent()) {
			return optionalTask.get();
		}
		return null;
	}

	//update task
	
	public Task updateTask(Task t) {
		return taskRepo.save(t);
	}
	
	//Delete task
	
	public void deleteTask(Long id) {
	 taskRepo.deleteById(id);
	}
	
	// All tasks
	public List<Task> allTasks() {
		return taskRepo.findAll();
	}
}
