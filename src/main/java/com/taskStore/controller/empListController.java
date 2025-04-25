package com.taskStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taskStore.entity.Admin;
import com.taskStore.entity.Employee;
import com.taskStore.repository.AdminRepository;
import com.taskStore.repository.empListRepository;
import com.taskStore.service.EmployeeServiceImpl;



@Controller
public class empListController {
	
	@Autowired
	private EmployeeServiceImpl  employeeService;

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private empListRepository employeeRepo;

	
	
	/* @RequestMapping("/deleteMyList/{id}")
	public String deleteMyList(@PathVariable("id") int id) {
		employeeService.deleteById(id);
		return "redirect:/emp_List";
	} */
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/admin_login")
	public String adminLogin() {
		return "adminLogin";
	}
	@GetMapping("/home1")
	public String home1() {
		return "home1";

	}
	@PostMapping("/adminLogin")
	public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
		Admin admin = adminRepo.findByUsernameAndPassword(username, password);
		if (admin != null) {
			return "adminDashboard";
		} 
		else {
			model.addAttribute("error", "Invalid credentials");
			return "adminLogin";
		}
	}
	//geeting all employee detail
	@GetMapping("/employees")
	public String viewHomePage(Model model) {
		return findPaginated(1, "name", "asc", model);		
		//return "employees";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// save employee to database
		employeeService.saveEmployee(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Employee employee = employeeService.getEmployeeById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/employees";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "employees";
	}
	
	@GetMapping("/employee/dashboard")
	public String showDashboard() {
		return "employee-dashboard";
	}
	
	



	
}
