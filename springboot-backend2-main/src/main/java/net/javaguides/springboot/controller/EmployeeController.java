package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.DTO.EmployeeDTO;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all employees
	@GetMapping("/employees")
	public List<EmployeeDTO> getAllEmployees(){
		List<Employee> employees = employeeRepository.findAll();
		return EmployeeDTO.fromEntityList(employees);
	}		
	
	// create employee rest api
	@PostMapping("/employees")
	public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		Employee employee = employeeDTO.toEntity();
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeDTO.fromEntity(savedEmployee);
	}
	
	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(EmployeeDTO.fromEntity(employee));
	}	
	
	// update employee rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		Employee updatedEmployee = employeeDTO.toEntity();
		updatedEmployee.setId(existingEmployee.getId());
		
		Employee savedEmployee = employeeRepository.save(updatedEmployee);
		return ResponseEntity.ok(EmployeeDTO.fromEntity(savedEmployee));
	}
	
	// delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	// get employee by username and password
	@GetMapping("/employees/{userName}/{passWord}")
	public ResponseEntity<EmployeeDTO> getEmployeeByUserNamePassWord(@PathVariable String userName, @PathVariable	String passWord) {
		Optional<Employee> employeeOptional = employeeRepository.findByUserNameAndPassWord(userName, passWord);
		Employee employee = employeeOptional.orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with username and password: " + userName + " " + passWord));
		return ResponseEntity.ok(EmployeeDTO.fromEntity(employee));
	}
	
	// get employees by role rest api
	@GetMapping("/employees/role/{role}")
	public List<EmployeeDTO> getEmployeesByRole(@PathVariable String role) {
		List<Employee> employees = employeeRepository.findByRole(role);
		return EmployeeDTO.fromEntityList(employees);
	}

	// get employees by department rest api
	@GetMapping("/employees/department/{department}")
	public List<EmployeeDTO> getEmployeesByDepartment(@PathVariable String department) {
		List<Employee> employees = employeeRepository.findByDepartment(department);
		return EmployeeDTO.fromEntityList(employees);
	}

	// get employees by username rest api
	@GetMapping("/employees/username/{username}")
	public ResponseEntity<EmployeeDTO> getEmployeeByUserName(@PathVariable String username) {
		Optional<Employee> employeeOptional = employeeRepository.findByUserName(username);
		Employee employee = employeeOptional.orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with username: " + username));
		return ResponseEntity.ok(EmployeeDTO.fromEntity(employee));
	}
}
