package net.javaguides.springboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import net.javaguides.springboot.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
    Optional<Employee> findByUserNameAndPassWord(String userName, String passWord);

	List<Employee> findByRole(String role);

	List<Employee> findByDepartment(String department);

	Optional<Employee> findByUserName(String username);

}