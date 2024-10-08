package net.javaguides.springboot.DTO;

import java.util.ArrayList;
import java.util.List;

import net.javaguides.springboot.model.PaymentRequest;

public class PaymentRequestDTO {
    private Long id;
    private String accountNo;
    private String branchName;
    private String ifsc;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public PaymentRequestDTO(Long id, String accountNo, String branchName, String ifsc) {
		super();
		this.id = id;
		this.accountNo = accountNo;
		this.branchName = branchName;
		this.ifsc = ifsc;
	}
	public PaymentRequestDTO() {
		super();
	}
	@Override
	public String toString() {
		return "PaymentRequestDTO [id=" + id + ", accountNo=" + accountNo + ", branchName=" + branchName + ", ifsc="
				+ ifsc + "]";
	}

	 public static PaymentRequestDTO fromEntity(PaymentRequest paymentRequest) {
	        return new PaymentRequestDTO(
	                paymentRequest.getId(),
	                paymentRequest.getAccountNo(),
	                paymentRequest.getBranchName(),
	                paymentRequest.getIfsc()
	        );
	    }

	    public static List<PaymentRequestDTO> fromEntityList(List<PaymentRequest> paymentRequests) {
	        List<PaymentRequestDTO> paymentRequestDTOs = new ArrayList<>();
	        for (PaymentRequest paymentRequest : paymentRequests) {
	            paymentRequestDTOs.add(fromEntity(paymentRequest));
	        }
	        return paymentRequestDTOs;
	    }

	    public PaymentRequest toEntity() {
	        PaymentRequest paymentRequest = new PaymentRequest();
	        paymentRequest.setId(this.id);
	        paymentRequest.setAccountNo(this.accountNo);
	        paymentRequest.setBranchName(this.branchName);
	        paymentRequest.setIfsc(this.ifsc);
	        return paymentRequest;
	    }
    
    // getters and setters
}