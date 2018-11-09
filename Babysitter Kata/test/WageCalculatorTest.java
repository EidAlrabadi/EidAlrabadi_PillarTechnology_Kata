import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class WageCalculatorTest {

	WageCalculator wageCalculator;
	private String startTime;
	private String endTime;
	@Before
	public void setup()  {
		wageCalculator = new WageCalculator(startTime,endTime);	
		
	}
	//Since only proper inputs will get to this point, I will only test proper inputs since that is the only
	// possible scenario that will occur, due to the error catching of the previous methods.
	
	
	@Test
	public void familyABefore11PMOnlyWageCalculation () {
		wageCalculator = new WageCalculator(startTime,endTime);
		wageCalculator.setStartTime("17:00");  //From 5Pm to 10:59 PM
		wageCalculator.setEndTime("22:59");
		assertEquals(90,wageCalculator.familyAWageCalculation(wageCalculator.getStartTime(), wageCalculator.getEndTime()));
	}
	
	@Test
	public void familyABefore11PMOnlyWageCalculation2 () {
		wageCalculator = new WageCalculator(startTime,endTime);
		wageCalculator.setStartTime("18:52");  
		wageCalculator.setEndTime("19:59");
		assertEquals(30,wageCalculator.familyAWageCalculation(wageCalculator.getStartTime(), wageCalculator.getEndTime()));
	}
	
	
	@Test
	public void familyAAfter11PMWageCalculation() {
		wageCalculator = new WageCalculator(startTime,endTime);
		wageCalculator.setStartTime("00:00");  // from 5:00 PM to 2:59 AM
		wageCalculator.setEndTime("03:59");
		assertEquals(80,wageCalculator.familyAWageCalculation(wageCalculator.getStartTime(), wageCalculator.getEndTime()));
	}
	
	@Test
	public void familyAAfter11PMWageCalculation2() {
		wageCalculator = new WageCalculator(startTime,endTime);
		wageCalculator.setStartTime("01:00");  // from 5:00 PM to 2:59 AM
		wageCalculator.setEndTime("02:43");
		assertEquals(40,wageCalculator.familyAWageCalculation(wageCalculator.getStartTime(), wageCalculator.getEndTime()));
	}
	
	@Test
	public void familyAAfter11PMWageCalculation3() {
		wageCalculator = new WageCalculator(startTime,endTime);
		wageCalculator.setStartTime("23:00");  
		wageCalculator.setEndTime("23:43");
		assertEquals(20,wageCalculator.familyAWageCalculation(wageCalculator.getStartTime(), wageCalculator.getEndTime()));
	}
	
	@Test
	public void familyAStartBefore11PMEndAfter11PM() {
		wageCalculator = new WageCalculator(startTime,endTime);
		wageCalculator.setStartTime("18:53");  
		wageCalculator.setEndTime("03:43");
		assertEquals(175,wageCalculator.familyAWageCalculation(wageCalculator.getStartTime(), wageCalculator.getEndTime()));
	}
	
	@Test
	public void familyAStartBefore11PMEndAfter11PM2 () {
		wageCalculator = new WageCalculator(startTime,endTime);
		wageCalculator.setStartTime("17:43");
				  
		wageCalculator.setEndTime("02:43");
		assertEquals(170,wageCalculator.familyAWageCalculation(wageCalculator.getStartTime(), wageCalculator.getEndTime()));
	}
	
	
	
	@Test
	public void familyAStartBefore11PMEndAfter11PMStartMinutesIsLarger () {
		wageCalculator = new WageCalculator(startTime,endTime);
		wageCalculator.setStartTime("19:59");  
				  
		wageCalculator.setEndTime("03:13");  
		assertEquals(160,wageCalculator.familyAWageCalculation(wageCalculator.getStartTime(), wageCalculator.getEndTime()));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Tear down method after all the tests are completed.
	@After
	public void tearDown() throws Exception {
	    wageCalculator = null;
	    assertNull(wageCalculator);
	}
	
}
