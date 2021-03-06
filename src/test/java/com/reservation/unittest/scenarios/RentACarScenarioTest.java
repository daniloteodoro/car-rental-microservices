package com.reservation.unittest.scenarios;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.reservation.domain.model.car.CategoryRepository;
import com.reservation.domain.model.reservation.exceptions.ReservationException;


/***
 * This class is intended to be used as a playground for exercising the domain model.
 * Starting TDD-like allowed me to focus more on what I really needed from the domain model from the beginning.
 * TODO: Check other "TODOs" around the code
 * @author Danilo Teodoro
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RentACarScenarioTest {
	
//	@Mock
//	private CarRepository carRepository;
	
	@Mock
	private CategoryRepository categoryRepository;

	/***
	 * 1. A visitor searches for cars in Rotterdam for the period of: 03/07/2018 to 08/07/2018 (dd/MM/yyyy)
	 * 2. After looking the list of cars, the visitor selects a VW Golf (EUR 40.00 per day) without any extras (current cost of the reservation is EUR 200.00)
	 * 3. He/she chooses "Additional Driver" as extra (total reservation price becomes EUR 260.00) 
	 * 4. When the visitor is prompted to choose the insurance type, they go for a Full Insurance (0.0 EUR to pay for damages), in a total quote price of EUR 350.00
	 * 5. As the visitor is satisfied with the price, he/she fills up their personal details
	 * 6. Payment is set to be concluded at the rental store and current reservation is converted to a final order after validating visitor's contact information.
	 * @throws ReservationException 
	 */
//	@Test
//	public void testCompleteRentalProcess() throws ReservationException, ExtraProductUnavailableException {
//
//		// TODO: Check for Factories, Repositories, and Services
//
//		// Current setup does not allow more than one rental point per city
//		City rotterdam = new City("Rotterdam", Country.NL);
//		LocalDateTime start = LocalDateTime.of(2018, 7, 3, 16, 30);
//		LocalDateTime finish = LocalDateTime.of(2018, 7, 8, 16, 00);
//
//		// TODO: Check whether the car search feature could be implemented inside the RentalStore entity
//
//		List<CategoryFeaturingModel> foundCategories = Arrays.asList(new CategoryFeaturingModel(SampleCategories.MEDIUMSIZED_CATEGORY, SampleModels.VW_GOLF));
//		//when(carRepository.categoryBasedOn(rotterdam, start, rotterdam, finish)).thenReturn(foundCategories);
//		Customer visitor = Customer.EMPTY;
//
//		// 1. A visitor searches for cars in Rotterdam for the period of: 03/07/2018 to 08/07/2018 (dd/MM/yyyy)
//		// TODO: Fix tests
//		List<CategoryFeaturingModel> availableCategories = null; //categoryRepository.availableOn(rotterdam, start, rotterdam, finish);
//		//AvailableCarList availableCars = carRepository.basedOn(rotterdam, start, rotterdam, finish);
//		assertThat(availableCategories.size(), is(greaterThan(0)));
//
//		// 2. After looking the list of cars, the visitor selects a VW Golf
//		CategoryFeaturingModel selectedCategory = availableCategories.get(0);
//
//		// 2.1 (EUR 40.00 per day)
//		assertThat(selectedCategory.getCategory().getPricePerDay().getValue(), is(40.0));
//
//		/* Start the reservation process by selecting a car and a period/place */
//		Reservation reservation = visitor.select(selectedCategory.getCategory(), rotterdam, start, rotterdam, finish);
//		assertThat(reservation.calculateTotal(), is(200.0));
//		assertThat(reservation.getAmountOfDays(), is(5L));
//
//		/* Design decision: from Business POV, it's always about cars (not quote or other names). */
//		/* Second thought: Making changes on cars that will later be discarded seemed incorrect. Changed to reservation */
//
//		// 3. He/she chooses "Additional Driver" as extra (total reservation price becomes EUR 260.00)
//		reservation.addExtraProduct(extraProductRepository.findByName("Additional Driver").orElseThrow(() -> new ExtraProductUnavailableException()));
//		assertThat(reservation.calculateTotal(), is(260.0));
//
//		// 4. When the visitor is prompted to choose the insurance type, they go for a Full Insurance (0.0 EUR to pay for damages), in a total quote price of EUR 350.00
//		reservation.setInsurance(InsuranceType.FULL_INSURANCE);
//		assertThat(reservation.calculateTotal(), is(350.0));
//
//		// 5. As the visitor is satisfied with the price, he/she fills up their personal details
//		visitor.setFullName("John Doe");
//		visitor.setAddress("Stree 1, NA");
//		visitor.setCity(rotterdam);
//		visitor.setPhoneNumber("111-222-3333");
//
//		// 6. Payment is set to be concluded at the rental store and current reservation is converted to a final order after validating visitor's contact information.
//		try {
//			visitor.payAtStore(reservation);
//			Assert.fail("A ReservationException was expected when paying without informing an e-mail");
//		} catch (ReservationException validationError) {
//			// This exception is expected
//		}
//
//		visitor.setEmail("visitor@tryingtorentacar.com");
//
//		try {
//			Order order = visitor.payAtStore(reservation);
//
//			assertThat(order.getTotal(), is(350.0));
//
//		} catch (ReservationException validationError) {
//			Assert.fail(String.format("Validation error was not expected after filling up all required customer's contact information \n(%s)", validationError.getMessage()));
//		}
//
//	}

}




