package com.app.todoapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.todo.model.Task;
import com.app.todoapp.services.TaskServices;

@Controller
//@RequestMapping
public class TaskController {
	
	private final TaskServices taskServices;
	public TaskController(TaskServices taskServices) {
		this.taskServices = taskServices;
	}
	
	@GetMapping
	public String getTasks(Model model) {
	    List<Task> tasks = taskServices.getAllTasks();
	    model.addAttribute("tasks", tasks != null ? tasks : new ArrayList<>());
	    return "tasks";
	}
	
	@PostMapping
	public String creatTask(@RequestParam String title) {
		taskServices.createTask(title);
		return "redirect:/";
	}

	@PostMapping("/toggle/{id}")
	public String toggleTask(@PathVariable Long id) {
	    taskServices.toggleTask(id);
	    return "redirect:/";
	}

	@PostMapping("/delete/{id}")
	public String deleteTask(@PathVariable Long id) {
	    taskServices.deleteTask(id);
	    return "redirect:/";
	}
}
