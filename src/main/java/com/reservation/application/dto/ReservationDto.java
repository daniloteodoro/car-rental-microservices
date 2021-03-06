package com.reservation.application.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.reservation.domain.model.car.ExtraProduct;
import com.reservation.domain.model.car.InsuranceType;
import com.reservation.domain.model.reservation.Reservation;
import com.reservation.domain.model.reservation.ReservationNumber;

public class ReservationDto {
	
	private ReservationNumber reservationNumber;
	private List<ExtraProduct> extras = new ArrayList<>();
	private InsuranceType insurance = InsuranceType.STANDARD_INSURANCE;
	private CityDto pickupLocation;
	private LocalDateTime pickupDateTime;
	private CityDto dropoffLocation;
	private LocalDateTime dropoffDateTime;
	private CategoryDto category;
	private Double total;
	private CustomerDto customer;
	
	
	public ReservationDto(ReservationNumber reservationNumber, CategoryDto category, CustomerDto customer, List<ExtraProduct> extras, InsuranceType insurance, 
			CityDto pickupLocation, LocalDateTime pickupDateTime, CityDto dropoffLocation, LocalDateTime dropoffDateTime, Double total) {
		super();
		this.reservationNumber = reservationNumber;
		this.category = category;
		this.customer = customer;
		this.extras = extras;
		this.insurance = insurance;
		this.pickupLocation = pickupLocation;
		this.pickupDateTime = pickupDateTime;
		this.dropoffLocation = dropoffLocation;
		this.dropoffDateTime = dropoffDateTime;
		this.total = total;
	}

	public static ReservationDto basedOn(Reservation reservation) {
		List<ExtraProduct> extras = new ArrayList<>();
		CityDto pickupLocation = CityDto.basedOn(reservation.getPickupLocation());
		CityDto dropoffLocation = CityDto.basedOn(reservation.getDropOffLocation());
		
		reservation.getExtras().forEachRemaining(extras::add);
		
		return new ReservationDto(reservation.getReservationNumber(), CategoryDto.basedOn(reservation), CustomerDto.basedOn(reservation.getCustomer()),
								  extras, reservation.getInsuranceType(), pickupLocation, reservation.getPickupDateTime(), dropoffLocation, reservation.getDropOffDateTime(),
								  reservation.calculateTotal());
	}
	
	// Simple constructor for ORM and serializers
	protected ReservationDto() {
		super();
	}
	
	public ReservationNumber getReservationNumber() {
		return reservationNumber;
	}

	public List<ExtraProduct> getExtras() {
		return extras;
	}

	public InsuranceType getInsurance() {
		return insurance;
	}

	public CityDto getPickupLocation() {
		return pickupLocation;
	}

	public LocalDateTime getPickupDateTime() {
		return pickupDateTime;
	}

	public CityDto getDropoffLocation() {
		return dropoffLocation;
	}

	public LocalDateTime getDropoffDateTime() {
		return dropoffDateTime;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public Double getTotal() {
		return total;
	}

	public CustomerDto getCustomer() {
		return customer;
	}
	
}