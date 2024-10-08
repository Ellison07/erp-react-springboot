package net.javaguides.springboot.DTO;

import java.util.List;
import java.util.stream.Collectors;

import net.javaguides.springboot.model.History;

public class HistoryDTO {
	private Long id;

	private String dates;
	private Long leaves;
	private Double rating;

	private Long Salary;
	private Long SalaryBaseOnPerformance;
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
	@Override
	public String toString() {
		return "HistoryDTO [id=" + id + ", dates=" + dates + ", leaves=" + leaves + ", rating=" + rating + ", Salary="
				+ Salary + ", SalaryBaseOnPerformance=" + SalaryBaseOnPerformance + "]";
	}
	public HistoryDTO(Long id, String dates, Long leaves, Double rating, Long salary, Long salaryBaseOnPerformance) {
		super();
		this.id = id;
		this.dates = dates;
		this.leaves = leaves;
		this.rating = rating;
		Salary = salary;
		SalaryBaseOnPerformance = salaryBaseOnPerformance;
	}
	public HistoryDTO() {
		super();
	}
	// HistoryDTO conversion methods
	public static HistoryDTO fromEntity(History history) {
	    HistoryDTO historyDTO = new HistoryDTO();
	    historyDTO.setId(history.getId());
	    historyDTO.setDates(history.getDates());
	    historyDTO.setLeaves(history.getLeaves());
	    historyDTO.setRating(history.getRating());
	    historyDTO.setSalary(history.getSalary());
	    historyDTO.setSalaryBaseOnPerformance(history.getSalaryBaseOnPerformance());
	    return historyDTO;
	}

	public static List<HistoryDTO> fromEntityList(List<History> histories) {
	    return histories.stream()
	            .map(HistoryDTO::fromEntity)
	            .collect(Collectors.toList());
	}

	public History toEntity() {
	    History history = new History();
	    history.setId(this.id);
	    history.setDates(this.dates);
	    history.setLeaves(this.leaves);
	    history.setRating(this.rating);
	    history.setSalary(this.Salary);
	    history.setSalaryBaseOnPerformance(this.SalaryBaseOnPerformance);
	    return history;
	}

	
}
