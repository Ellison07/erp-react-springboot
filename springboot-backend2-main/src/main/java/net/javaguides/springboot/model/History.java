package net.javaguides.springboot.model;

import jakarta.persistence.*;
//import java.util.Date;

@Entity
@Table
public class History {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column
	private String dates;
	@Column
	private Long leaves;
	@Column
	private Double rating;
	@Column
	private Long Salary;
	@Column 
	private Long SalaryBaseOnPerformance;
	@ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public Long getLeaves() {
		return leaves;
	}
	public void setLeaves(Long leaves) {
		this.leaves = leaves;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public Long getSalary() {
		return Salary;
	}
	public void setSalary(Long salary) {
		Salary = salary;
	}
	public Long getSalaryBaseOnPerformance() {
		return SalaryBaseOnPerformance;
	}
	public void setSalaryBaseOnPerformance(Long salaryBaseOnPerformance) {
		SalaryBaseOnPerformance = salaryBaseOnPerformance;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public History(Long id, String dates, Long leaves, Double rating, Long salary, Long salaryBaseOnPerformance,
			Employee employee) {
		super();
		this.id = id;
		this.dates = dates;
		this.leaves = leaves;
		this.rating = rating;
		Salary = salary;
		SalaryBaseOnPerformance = salaryBaseOnPerformance;
		this.employee = employee;
	}
	public History() {
		super();
	}
	@Override
	public String toString() {
		return "History [id=" + id + ", dates=" + dates + ", leaves=" + leaves + ", rating=" + rating + ", Salary="
				+ Salary + ", SalaryBaseOnPerformance=" + SalaryBaseOnPerformance + ", employee=" + employee + "]";
	}
	
}
