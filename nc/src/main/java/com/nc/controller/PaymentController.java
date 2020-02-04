package com.nc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nc.payment_dto.PaymentRequestDTO;
import com.nc.payment_dto.PaymentResponseDTO;
import com.nc.payment_dto.RegistrationRequestDTO;
import com.nc.payment_dto.RegistrationResponseDTO;
import com.nc.payment_dto.SubscriptionDTO;
import com.nc.payment_dto.SubscriptionResponseDTO;


@RestController
public class PaymentController {
	
	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/testPayment")
	public String testPayment(@RequestBody PaymentRequestDTO pr) {
		pr.setSuccessUrl("http://localhost:8080/successUrl");
		pr.setFailureUrl("http://localhost:8080/failureUrl");
		ResponseEntity<PaymentResponseDTO> response = restTemplate.postForEntity("https://localhost:8673/sellers/ncApi/paymentUrl", pr, PaymentResponseDTO.class);
		
		return response.getBody().getPaymentUrl();
	}
	
	@GetMapping("/testRegistration")
	public String testRegistration() {
		
		RegistrationRequestDTO registrationDTO = new RegistrationRequestDTO("confirmation link");
		ResponseEntity<RegistrationResponseDTO> response = restTemplate.postForEntity("https://localhost:8673/sellers/ncApi/registrationUrl",
				registrationDTO, RegistrationResponseDTO.class);

		return response.getBody().getRegistrationUrl();
	}
	
	@GetMapping("/testSubscription")
	public String testSubscription() {
		
		SubscriptionDTO subsDTO = new SubscriptionDTO(1, "subscription", "description", "FIXED", "MONTH", "2", "2", "20", "USD",
				"http://localhost:8080/subSuccess","http://localhost:8080/subFailure");
		ResponseEntity<SubscriptionResponseDTO> response = restTemplate.postForEntity("https://localhost:8673/paypal/ncApi/createSubscription",
				subsDTO, SubscriptionResponseDTO.class);

		return response.getBody().getPaymentUrl();
	}
	
	@PostMapping("/successUrl/{paymentId}")
	public void successUrl(@PathVariable("paymentId") String paymentId) {
		System.out.println(paymentId);
		System.out.println("payment sucess");
	}
	@PostMapping("/failureUrl/{paymentId}")
	public void failureUrl(@PathVariable("paymentId") String paymentId) {
		System.out.println(paymentId);
		System.out.println("payment failure");
	}
	
	@PostMapping("/subSuccess/{subscriptionId}")
	public void subSuccess(@PathVariable("subscriptionId") String subscriptionId) {
		System.out.println(subscriptionId);
		System.out.println("sub success");
	}
	@PostMapping("/subFailure/{subscriptionId}")
	public void subFailure(@PathVariable("subscriptionId") String subscriptionId) {
		System.out.println(subscriptionId);
		System.out.println("sub failure");
	}
}
