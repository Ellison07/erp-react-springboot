package net.javaguides.springboot.controller;

import net.javaguides.springboot.DTO.PaymentRequestDTO;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.PaymentRequest;
import net.javaguides.springboot.repository.PaymentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v3/")
public class PaymentController {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;

    @GetMapping("/payments/employee/{employeeId}")
    public ResponseEntity<PaymentRequestDTO> getDataByEmployeeId(@PathVariable Long employeeId) {
        PaymentRequest paymentRequest = paymentRequestRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment request not found with employee ID: " + employeeId));

        PaymentRequestDTO paymentRequestDTO = PaymentRequestDTO.fromEntity(paymentRequest);
        return ResponseEntity.ok(paymentRequestDTO);
    }

    @PostMapping("/payments")
    public ResponseEntity<PaymentRequestDTO> createPaymentRequest(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentRequest paymentRequest = paymentRequestDTO.toEntity();
        PaymentRequest createdPaymentRequest = paymentRequestRepository.save(paymentRequest);
        PaymentRequestDTO createdPaymentRequestDTO = PaymentRequestDTO.fromEntity(createdPaymentRequest);

        return ResponseEntity.ok(createdPaymentRequestDTO);
    }

    @DeleteMapping("/payments/{employeeId}")
    public ResponseEntity<?> deletePaymentRequest(@PathVariable Long employeeId) {
        PaymentRequest paymentRequest = paymentRequestRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment request not found with employee ID: " + employeeId));

        paymentRequestRepository.delete(paymentRequest);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/payments/{employeeId}")
    public ResponseEntity<PaymentRequestDTO> updatePaymentRequest(
            @PathVariable Long employeeId, @RequestBody PaymentRequestDTO updatedPaymentRequestDTO) {
        PaymentRequest paymentRequest = paymentRequestRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment request not found with employee ID: " + employeeId));

        paymentRequest.setAccountNo(updatedPaymentRequestDTO.getAccountNo());
        paymentRequest.setBranchName(updatedPaymentRequestDTO.getBranchName());
        paymentRequest.setIfsc(updatedPaymentRequestDTO.getIfsc());

        PaymentRequest updatedPayment = paymentRequestRepository.save(paymentRequest);
        PaymentRequestDTO updatedPaymentRequest = PaymentRequestDTO.fromEntity(updatedPayment);

        return ResponseEntity.ok(updatedPaymentRequest);
    }

}
