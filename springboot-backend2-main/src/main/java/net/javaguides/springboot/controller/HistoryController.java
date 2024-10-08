package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import net.javaguides.springboot.DTO.HistoryDTO;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.History;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.HistoryRepository;
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v2/")
public class HistoryController {
    @Autowired
    private HistoryRepository historyRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
 // Create history for an employee
    @PostMapping("/employees/{employeeId}/histories")
    public HistoryDTO createHistory(@PathVariable Long employeeId, @RequestBody HistoryDTO historyDTO) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + employeeId));
        
        History history = historyDTO.toEntity();
        history.setEmployee(employee);
        History createdHistory = historyRepository.save(history);
        
        return HistoryDTO.fromEntity(createdHistory);
    }

    // Get all histories of an employee
    @GetMapping("/employees/{employeeId}/histories")
    public List<HistoryDTO> getAllHistoriesByEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + employeeId));
        
        List<History> histories = employee.getHistories();
        return HistoryDTO.fromEntityList(histories);
    }
}
    // Update a history for an employee
//    @PutMapping("/employees/{employeeId}/histories/{historyId}")
//    public ResponseEntity<HistoryDTO> updateHistory(@PathVariable Long employeeId, @PathVariable Long historyId,
//            @RequestBody HistoryDTO historyDTO) {
//        Employee employee = employeeRepository.findById(employeeId)
//            .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + employeeId));
//        
//        History history = historyRepository.findById(historyId)
//            .orElseThrow(() -> new ResourceNotFoundException("History not exist with id :" + historyId));
//        
//        history.setEmployee(employee);
//        history.setDates(historyDTO.getDates());
//        history.setLeaves(historyDTO.getLeaves());
//        history.setRating(historyDTO.getRating());
//        history.setSalary(historyDTO.getSalary());
//        history.setSalaryBaseOnPerformance(historyDTO.getSalaryBaseOnPerformance());
//        
//        History updatedHistory = historyRepository.save(history);
//        return ResponseEntity.ok(HistoryDTO.fromEntity(updatedHistory));
//    }
//
//    // Delete a history for an employee
//    @DeleteMapping("/employees/{employeeId}/histories/{historyId}")
//    public ResponseEntity<Map<String, Boolean>> deleteHistory(@PathVariable Long employeeId,
//            @PathVariable Long historyId) {
//        Employee employee = employeeRepository.findById(employeeId)
//            .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + employeeId));
//        
//        History history = historyRepository.findById(historyId)
//            .orElseThrow(() -> new ResourceNotFoundException("History not exist with id :" + historyId));
//        employee.getHistories().remove(history);
//        employeeRepository.save(employee);
//        
//        historyRepository.delete(history);
//        
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }
//}
       
