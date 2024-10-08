package net.javaguides.springboot.DTO;

import java.util.ArrayList;
import java.util.List;

import net.javaguides.springboot.model.Employee;

public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String userName;
    private String passWord;
    private String role;
    private String department;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

	public EmployeeDTO(Long id, String firstName, String lastName, String emailId, String userName, String passWord,
			String role, String department) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
		this.department = department;
	}

	public EmployeeDTO() {
		super();
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", userName=" + userName + ", passWord=" + passWord + ", role=" + role + ", department=" + department
				+ "]";
	}
	public static EmployeeDTO fromEntity(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(employee.getId());
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setLastName(employee.getLastName());
		employeeDTO.setEmailId(employee.getEmailId());
		employeeDTO.setUserName(employee.getUserName());
		employeeDTO.setPassWord(employee.getPassWord());
		employeeDTO.setRole(employee.getRole());
		employeeDTO.setDepartment(employee.getDepartment());
		return employeeDTO;
	}

	public static List<EmployeeDTO> fromEntityList(List<Employee> employees) {
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		for (Employee employee : employees) {
			employeeDTOs.add(fromEntity(employee));
		}
		return employeeDTOs;
	}

	public Employee toEntity() {
		Employee employee = new Employee();
		employee.setId(this.getId());
		employee.setFirstName(this.getFirstName());
		employee.setLastName(this.getLastName());
		employee.setEmailId(this.getEmailId());
		employee.setUserName(this.getUserName());
		employee.setPassWord(this.getPassWord());
		employee.setRole(this.getRole());
		employee.setDepartment(this.getDepartment());
		return employee;
	}

    
}
