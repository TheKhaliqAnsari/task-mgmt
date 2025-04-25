package com.taskStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taskStore.entity.Employee;
//import com.taskStore.entity.empList;
import com.taskStore.entity.taskList;
import com.taskStore.service.empListService;
import com.taskStore.service.taskService;


@Controller
public class taskController {
	
	@Autowired
	private taskService service;
	
	@Autowired
	private empListService myTaskService;

	//@Autowired
	//private taskRepository taskRepository;
	
	/* @GetMapping("/")
	public String home1() {
		return "home1";
	} */
	
	@GetMapping("/new_Task")
	public String taskRegister() {
		return "taskRegister";
	}
	
	@GetMapping("/task_List")
	public ModelAndView getAllTask() {
		List<taskList>list=service.getAllTask();
		return new ModelAndView("taskList","Task",list);
	}
	
	@PostMapping("/save")
	public String addTask(@ModelAttribute taskList b) {
		service.save(b);
		return "redirect:/task_List";
	}
	@GetMapping("/emp_List")
	public String getMyTask(Model model)
	{
		List<Employee>list=myTaskService.getAllEmployees();
		model.addAttribute("Task",list);
		return "empList";
	}
	/* @GetMapping("/emp_Id")
	public String getEmpId(Model model) {
		return "assignTaskById";
	} */
	
	/* @RequestMapping("/assignTask/{taskId}")
	public String assignTask(@PathVariable("taskId") int taskId, @RequestParam("empId") int empId) {

    // 1. Get task by ID from taskList
    taskList task = service.getTaskById(taskId);

    // 2. Create new empList object and populate it
    empList empTask = new empList();
    empTask.setEmpId(empId);                // assign to given employee
    empTask.setTaskId(task.getTaskId());    // assign task details
    empTask.setTask(task.getTask());
    empTask.setTaskDetail(task.getTaskDetail());
    empTask.setStatus(task.getStatus());

    // 3. Save to empList table
    myTaskService.saveMyTask(empTask);

    // 4. Redirect to employee list or task list
    return "redirect:/emp_List"; // or wherever you show assigned tasks
} */
	
	@RequestMapping("/taskEdit/{taskId}")
	public String editTask(@PathVariable("taskId") int taskId,Model model) {
		taskList b=service.getTaskById(taskId);
		model.addAttribute("Task",b);
		return "taskEdit";
	}
	@RequestMapping("/deleteTask/{taskId}")
	public String deleteTask(@PathVariable("taskId")int taskId) {
		service.deleteById(taskId);
		return "redirect:/task_List";
	}
	/* @PostMapping("/assignTask")
public String assignTaskToEmployee(@RequestParam int taskId, @RequestParam Long empId) {
    taskList task = taskRepository.findById(taskId).orElse(null);
    if (task != null) {
        task.setEmployeeId(empId);
        taskRepository.save(task);
    }
    return "redirect:/task_List"; // or wherever you want
} */
@GetMapping("/assignTask")
	public String assignTask() {
		return "assignTask";
	}
	
	
}