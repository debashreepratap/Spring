package com.aop.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aop.demo.model.Task;
import com.aop.demo.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	TaskService taskService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> Tasks = taskService.getAllTasks();
		if (!Tasks.isEmpty()) {
			List<Task> TaskList = taskService.getAllTasks();
			return new ResponseEntity<>(TaskList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable("id") int id) {
		Optional<Task> Task = taskService.findById(id);
		if (Task.isPresent()) {
			return new ResponseEntity<>(Task.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Task> createTaskModel(@RequestBody Task task) {
		// try {

		Task Task1 = taskService.createTask(task);
		return new ResponseEntity<>(Task1, HttpStatus.CREATED);
		/*
		 * } catch (Exception e) { return new
		 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
		 */
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Task> deleteTask(@PathVariable("id") int id) {
		// Optional<Task> Task = taskService.findById(id);
		// if (Task.isPresent()) {
		//try {
			taskService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		//} catch (Exception e) {
			//return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		//}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Task> updateTaskModel(@PathVariable("id") int id, @RequestBody Task task) {
		Optional<Task> taskData = taskService.findById(id);
		if (taskData.isPresent()) {
			Task newTask = taskData.get();
			newTask.setTaskName(task.getTaskName());
			newTask.setTaskType(task.getTaskType());
			newTask.setAssignDate(task.getAssignDate());
			return new ResponseEntity<>(taskService.createTask(newTask), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
