package net.javaguides.springboot.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"userName"})})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String emailId;
    @Column
    private String userName;
    @Column
    private String passWord;
    @Column 
    private String role;
    @Column 
    private String department;
    
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentRequest> paymentRequests = new ArrayList<>();
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> histories = new ArrayList<>();
	
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
	public List<PaymentRequest> getPaymentRequests() {
		return paymentRequests;
	}
	public void setPaymentRequests(List<PaymentRequest> paymentRequests) {
		this.paymentRequests = paymentRequests;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public List<History> getHistories() {
		return histories;
	}
	public void setHistories(List<History> histories) {
		this.histories = histories;
	}
	public Employee(Long id, String firstName, String lastName, String emailId, String userName, String passWord,
			String role, String department, List<PaymentRequest> paymentRequests, List<History> histories) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
		this.department = department;
		this.paymentRequests = paymentRequests;
		this.histories = histories;
	}
	public Employee() {
		super();
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", userName=" + userName + ", passWord=" + passWord + ", role=" + role + ", department=" + department
				+ ", paymentRequests=" + paymentRequests + ", histories=" + histories + "]";
	}
	
	

}
