package com.reservation.domain.model.car.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends CarRentalException {

	private static final long serialVersionUID = 4297245597101677210L;

	public CustomerNotFoundException() {
		super("Customer not found");
	}
	public CustomerNotFoundException(String s) {
		super(s);
	}

}
