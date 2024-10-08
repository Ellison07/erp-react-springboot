package net.javaguides.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
@Table
@Entity
public class PaymentRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String accountNo;
	@Column
	private String branchName;
	@Column
	private String ifsc;
                       // Add the foreign key relationship
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private Employee employee;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public PaymentRequest(Long id, String accountNo, String branchName, String ifsc, Employee employee) {
		super();
		this.id = id;
		this.accountNo = accountNo;
		this.branchName = branchName;
		this.ifsc = ifsc;
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "PaymentRequest [id=" + id + ", accountNo=" + accountNo + ", branchName=" + branchName + ", ifsc=" + ifsc
				+ ", employee=" + employee + "]";
	}
	public PaymentRequest() {
		super();
	}
	
	
	
}
