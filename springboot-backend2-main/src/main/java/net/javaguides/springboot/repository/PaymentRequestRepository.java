package net.javaguides.springboot.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.PaymentRequest;

@Repository

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {



	Optional<PaymentRequest> findByEmployeeId(Long employeeId);

}
