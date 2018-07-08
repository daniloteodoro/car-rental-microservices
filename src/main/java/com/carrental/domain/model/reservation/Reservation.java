package com.carrental.domain.model.reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.time.temporal.ChronoUnit;

import com.carrental.domain.model.car.Car;
import com.carrental.domain.model.car.ExtraProduct;
import com.carrental.domain.model.car.InsuranceType;
import com.carrental.domain.model.customer.Customer;
import com.carrental.shared.Entity;

/***
 * A reservation (or booking?) represents a car occupied (not available for new rentals) by a customer during a certain period.
 * @author Danilo Teodoro
 *
 */
public class Reservation implements Entity {

	private ReservationNumber reservationNumber;
	private Customer customer;
	private Car car;
	private List<ExtraProduct> extras = new ArrayList<>();
	private InsuranceType insurance = InsuranceType.STANDARD_INSURANCE;
	private City pickupLocation;
	private LocalDateTime pickupDateTime;
	private City dropoffLocation;
	private LocalDateTime dropoffDateTime;
	
	// TODO: Create Builder
	public Reservation(Customer customer, Car car, City pickupLocation, LocalDateTime pickupDateTime, City dropoffLocation, LocalDateTime dropoffDateTime) {
		super();
		
		Objects.requireNonNull(customer, "Invalid customer");
		Objects.requireNonNull(car, "Invalid car");
		Objects.requireNonNull(pickupLocation, "Invalid pickup location");
		Objects.requireNonNull(pickupDateTime, "Invalid pickup date/time");
		Objects.requireNonNull(dropoffLocation, "Invalid drop-off location");
		Objects.requireNonNull(dropoffDateTime, "Invalid drop-off date/time");		
		
		this.customer = customer;
		this.car = car;
		this.pickupLocation = pickupLocation;
		this.pickupDateTime = pickupDateTime;
		this.dropoffLocation = dropoffLocation;
		this.dropoffDateTime = dropoffDateTime;
		
		this.reservationNumber = new ReservationNumber(UUID.randomUUID().toString());
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setExtraProducts(ExtraProduct... extras) {
		this.extras.clear();
		this.extras.addAll(Arrays.asList(extras));
	}
	
	public Iterator<ExtraProduct> getExtras() {
		return extras.iterator();
	}
	
	public void setInsurance(InsuranceType insurance) {
		this.insurance = insurance;
	}
	
	public City getPickupLocation() {
		return pickupLocation;
	}
	public LocalDateTime getPickupDateTime() {
		return pickupDateTime;
	}
	public City getDropoffLocation() {
		return dropoffLocation;
	}
	public LocalDateTime getDropoffDateTime() {
		return dropoffDateTime;
	}
	
	public InsuranceType getInsurance() {
		return insurance;
	}
	
	public Double getTotalForInsurance() {
		Double total = this.car.getInsurancePriceFor(insurance) * getAmountOfDays();	
		return total;
	}
	
	public Double getTotalForExtras() {
		double extrasVal = 0.0;
		for (ExtraProduct extra: extras) {
			extrasVal += extra.getPrice();
		}
		return extrasVal * getAmountOfDays();
	}
	
	public Double calculateTotal() {
		Double price = getTotalPriceForCar();
		Double insurance = getTotalForInsurance();
		Double extras = getTotalForExtras();
		
		return price + insurance + extras;
	}
	
	private Double getTotalPriceForCar() {
		Double total = car.getPricePerDay() * getAmountOfDays();
		return total;
	}
	
	public Long getAmountOfDays() {
		Long rentalDays = ChronoUnit.DAYS.between(pickupDateTime.toLocalDate(), dropoffDateTime.toLocalDate());
		return rentalDays;
	}
	
	public ReservationNumber getReservationNumber() {
		return reservationNumber;
	}
	
	@Override
	public int hashCode() {
		return reservationNumber.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Reservation other = (Reservation) obj;
		return this.reservationNumber.equals(other.reservationNumber);
	}
	
}




