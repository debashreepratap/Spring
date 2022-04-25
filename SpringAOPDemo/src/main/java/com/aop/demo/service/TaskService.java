package com.aop.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aop.demo.exception.TaskException;
import com.aop.demo.model.Task;
import com.aop.demo.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public Optional<Task> findById(int id) {
		return taskRepository.findById(id);
	
	}

	public Task createTask(Task task) {
		return taskRepository.save(task);
	}

	@Transactional
	public void deleteById(int id) {
		Optional<Task> task=taskRepository.findById(id);
		boolean flag=task.isPresent();
		
		if (!task.isPresent()) {
		  throw new TaskException(HttpStatus.NOT_FOUND, "Task not Found");
		}
		
		Task tsk= task.get();
	    taskRepository.delete(tsk);
		
		
	}

}
