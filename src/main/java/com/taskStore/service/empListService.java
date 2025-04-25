package com.taskStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.taskStore.entity.empList;

import com.taskStore.entity.Employee;
import com.taskStore.repository.empListRepository;

@Service
public class empListService {
	
	@Autowired
	private empListRepository empList;
	
	public void saveMyTask(Employee task) {
		empList.save(task);
	}
	
	public List<Employee> getAllEmployees(){
		return empList.findAll();
	}
	
	/* public void deleteById(int taskId) {
		Employee.deleteById(taskId);
	} */
}
