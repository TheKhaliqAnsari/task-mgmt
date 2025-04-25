package com.taskStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taskStore.entity.Attendance;
import com.taskStore.entity.Employee;
import com.taskStore.repository.attendanceRepository;
import com.taskStore.repository.empListRepository;
import com.taskStore.service.EmployeeServiceImpl;
import com.taskStore.service.attendanceService;

import jakarta.servlet.http.HttpSession;


@Controller
public class attendanceController {

    @Autowired
	private empListRepository employeeRepository;

	@Autowired
	private attendanceRepository attendanceRepo;

    @Autowired
    private attendanceService attendanceServ;

    @Autowired
    private EmployeeServiceImpl employeeServ;

    /* @PostMapping("/emp_login")
    public String empLogin() {
        //TODO: process POST request
        
        return "redirect:/employeeLogin";
    } */
    
    /* @PostMapping("/employeeLogin")
	public String login(@RequestParam String email,
                    @RequestParam String password,
                    Model model) {

    Employee emp = employeeRepository.findByEmail(email);

    if (emp != null && emp.getPassword().equals(password)) {
        // save login time in Attendance
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(emp.getId());
        attendance.setLoginTime(LocalDateTime.now());
        attendanceRepo.save(attendance);

        model.addAttribute("employee", emp);
        return "redirect:/employee/dashboard"; // redirect to employee dashboard or home
    } else {
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }
} */

@GetMapping("/emp_login")
	public String empLogin() {
		return "login";
	}

    @PostMapping("/employeeLogin")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
    
        Employee employee = employeeRepository.findByEmail(email);
    
        if (employee != null && employee.getPassword().equals(password)) {
            session.setAttribute("employee", employee); // Store under 'employee' key to match self-service
            session.setMaxInactiveInterval(1800); 
            System.out.println("Employee logged in: " + employee.getEmail());
            return "redirect:/employee/dashboard"; 
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login"; // Your login view
        }
    }

@GetMapping("/employee/self-service")
public String showSelfServicePortal(HttpSession session, Model model) {
    Employee employee = (Employee) session.getAttribute("employee");

    if (employee == null) {
        System.out.println("No employee found in session. Redirecting...");
        return "redirect:/employee/dashboard"; // redirect to login if not logged in
    }

    model.addAttribute("employee", employee);
    //System.out.println("Session employee: " + session.getAttribute("employee"));

    return "selfServicePortal";
}

@GetMapping("/admin/attendance")
    public String getAttendanceList(Model model) {
        List<Attendance> attendanceList = attendanceServ.getAllAttendance();
        model.addAttribute("attendanceList", attendanceList);
        return "attendance-list";
    }

@PostMapping("employee/profile/update")
    public String updateProfile(@ModelAttribute("employee") Employee employee,
                                HttpSession session) {

        // Get the employee from session
        Employee emp = (Employee) session.getAttribute("employee");

        if (emp == null) {
            // Session expired or not set, redirect to login
            return "redirect:/employee/dashboard";
        }

        // Update editable fields
        emp.setPhoneNo(employee.getPhoneNo());
        emp.setGender(employee.getGender());
        emp.setMaritalStatus(employee.getMaritalStatus());

        emp.setCity(employee.getCity());
        emp.setState(employee.getState());
        emp.setZipCode(employee.getZipCode());
        emp.setCountry(employee.getCountry());
        emp.setAlternateEmail(employee.getAlternateEmail());

        emp.setEmergencyContactName(employee.getEmergencyContactName());
        emp.setEmergencyContactRelation(employee.getEmergencyContactRelation());
        emp.setEmergencyContactPhone(employee.getEmergencyContactPhone());
        emp.setEmergencyContactEmail(employee.getEmergencyContactEmail());

        // Save updated data in DB
        employeeServ.save(emp);

        // Update session with new employee object
        session.setAttribute("employee", emp);

        return "selfServicePortal";
    }


}
