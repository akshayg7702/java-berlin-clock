/**
 * 
 */
package com.ubs.opsit.interviews.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ubs.opsit.interviews.TimeConverter;
import com.ubs.opsit.interviews.impl.BerlinClockConverter;
import com.ubs.opsit.interviews.impl.TimeConverterImpl;

/**
 * @author Akshay
 *
 */
public class TimeConverterTest {
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	TimeConverter timeConverter;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		timeConverter = new TimeConverterImpl();			
	}

	/**
	 * Test method for {@link com.ubs.opsit.interviews.impl.TimeConverterImpl#convertTime(java.lang.String)}.
	 */
	@Test
	public void testConvertTimeForPositiveCase() {
		String berlinClock = timeConverter.convertTime("13:17:01");
		assertEquals(berlinClock, "O"+LINE_SEPARATOR+"RROO"+LINE_SEPARATOR+"RRRO"+LINE_SEPARATOR+"YYROOOOOOOO"+LINE_SEPARATOR+"YYOO");
	}
	
	@Test
	public void testConvertTimeMidNight() {
		String berlinClock = timeConverter.convertTime("00:00:00");
		assertEquals(berlinClock, "Y"+LINE_SEPARATOR+"OOOO"+LINE_SEPARATOR+"OOOO"+LINE_SEPARATOR+"OOOOOOOOOOO"+LINE_SEPARATOR+"OOOO");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenInputIsNullExceptionIsThrown() {
		
		timeConverter.convertTime(null);		
	}
	
	@Test
	public void whenInputIsNullValidateExceptionWithMesg() {
		
		expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("Time is not entered. Please time in HH:MM:SS format");
	    
		timeConverter.convertTime(null);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenTimeInWrongFormatExceptionIsThrown() {
		
		timeConverter.convertTime("12:23:67:34");		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConvertTimeWithWrongHour() {
		timeConverter.convertTime("24:00:00");
	}

}
