package com.nc.payment_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {

	private long sellerId;
	private float price;
	private String currency;
	private String successUrl;
	private String failureUrl;
	
}
