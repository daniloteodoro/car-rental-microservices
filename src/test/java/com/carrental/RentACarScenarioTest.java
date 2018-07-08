package com.carrental;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.carrental.domain.model.car.AvailableCarList;
import com.carrental.domain.model.car.Brand;
import com.carrental.domain.model.car.Car;
import com.carrental.domain.model.car.Category;
import com.carrental.domain.model.car.ExtraProduct;
import com.carrental.domain.model.car.InsuranceType;
import com.carrental.domain.model.car.Model;
import com.carrental.domain.model.customer.Visitor;
import com.carrental.domain.model.reservation.City;
import com.carrental.domain.model.reservation.Country;
import com.carrental.domain.model.reservation.Order;
import com.carrental.domain.model.reservation.Reservation;
import com.carrental.domain.model.reservation.ReservationException;
import com.carrental.domain.service.SearchAvailableCarsService;

/***
 * This class is intended to be used as a playground for exercising the domain model.
 * Starting TDD-like allowed me to focus more on what I really needed from the domain model from the beginning.
 * TODO: Refactor to use Cucumber BDD style
 * @author Danilo Teodoro
 *
 */
public class RentACarScenarioTest {
	
	/***
	 * 1. A visitor searches for cars in Rotterdam for the period of: 03/07/2018 to 08/07/2018 (dd/MM/yyyy)
	 * 2. After looking the list of cars, the visitor selects a VW Golf (EUR 50.00 per day) without any extras (current cost of the reservation is EUR 250.00)
	 * 3. He/she chooses "Additional Driver" as extra (total reservation price becomes EUR 310.00) 
	 * 4. When the visitor is prompted to choose the insurance type, they go for a Full Insurance (0.0 EUR to pay for damages), in a total quote price of EUR 400.00
	 * 5. As the visitor is satisfied with the price, he/she fills up their personal details
	 * 6. Payment is set to be concluded at the rental store and current reservation is converted to a final order after validating visitor's contact information.
	 */
	@Test
	public void testCompleteRentalProcess() {
		
		// TODO: Check for Factories, Repositories, and Services
		
		final Model GOLF = new Model(new Brand("VW"), "Golf", Category.MEDIUMSIZED);
		
		// Current setup does not allow more than one rental point per city
		City rotterdam = new City("Rotterdam", Country.NETHERLANDS);
		LocalDateTime start = LocalDateTime.of(2018, 7, 3, 16, 30);
		LocalDateTime finish = LocalDateTime.of(2018, 7, 8, 16, 00);
		
		// TODO: Mock the car's repository
		SearchAvailableCarsService searchAvailableCars = new SearchAvailableCarsService();
		Visitor visitor = new Visitor();
		
		// 1. A visitor searches for cars in Rotterdam for the period of: 03/07/2018 to 08/07/2018 (dd/MM/yyyy)
		AvailableCarList availableCars = searchAvailableCars.basedOn(rotterdam, start, rotterdam, finish);
		assertThat(availableCars.size(), greaterThan(0));
		
		// 2. After looking the list of cars, the visitor selects a VW Golf
		Optional<Car> selectedCar = availableCars.findByModel(GOLF);
		assertTrue(selectedCar.isPresent());
		
		Car golf = selectedCar.get();
		
		// 2.1 (EUR 60.00 per day)
		assertThat(golf.getPricePerDay(), is(50.0));
		
		/* Start the reservation process by selecting a car and a period/place */
		Reservation reservation = visitor.select(golf, rotterdam, start, rotterdam, finish);
		assertThat(reservation.calculateTotal(), is(250.0));
		assertThat(reservation.getAmountOfDays(), is(5L));
		
		/* Design decision: from Business POV, it's always about cars (not quote or other names). */
		/* Second thought: Making changes on cars that will later be discarded seemed incorrect. Changed to reservation */
		
		// 3. He/she chooses "Additional Driver" as extra (total reservation price becomes EUR 310.00)
		reservation.setExtraProducts(ExtraProduct.ADDITIONAL_DRIVER);
		assertThat(reservation.calculateTotal(), is(310.0));
		
		// 4. When the visitor is prompted to choose the insurance type, they go for a Full Insurance (0.0 EUR to pay for damages), in a total quote price of EUR 400.00
		reservation.setInsurance(InsuranceType.FULL_INSURANCE);
		assertThat(reservation.calculateTotal(), is(400.0));
		
		// 5. As the visitor is satisfied with the price, he/she fills up their personal details
		visitor.setFullName("John Doe");
		visitor.setAddress("Stree 1, NA");
		visitor.setCity(rotterdam);
		visitor.setPhoneNumber("111-222-3333");
		
		// 6. Payment is set to be concluded at the rental store and current reservation is converted to a final order after validating visitor's contact information.
		try {
			visitor.payAtStore(reservation);
			Assert.fail("A ReservationException was expected when paying without informing an e-mail");
		} catch (ReservationException validationError) {
			// This exception is expected
		}
		
		visitor.setEmail("visitor@tryingtorentacar.com");
		
		try {
			Order order = visitor.payAtStore(reservation);
			
			assertThat(order.getTotal(), is(400.0));
			
		} catch (ReservationException validationError) {
			Assert.fail(String.format("Validation error was not expected after filling up all required customer's contact information \n(%s)", validationError.getMessage()));
		}
		
	}

}



