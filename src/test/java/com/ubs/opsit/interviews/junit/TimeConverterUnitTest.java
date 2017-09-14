/**
 * 
 */
package com.ubs.opsit.interviews.junit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ubs.opsit.interviews.TimeConverter;
import com.ubs.opsit.interviews.impl.BerlinClockConverter;
import com.ubs.opsit.interviews.impl.TimeConverterImpl;

/**
 * @author Akshay
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeConverterUnitTest {
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	@InjectMocks
	TimeConverterImpl timeConverterImpl;

	@Mock
	BerlinClockConverter berlinClockConverterMock;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		timeConverterImpl = new TimeConverterImpl();
	}

	/**
	 * Test method for
	 * {@link com.ubs.opsit.interviews.impl.TimeConverterImpl#convertTime(java.lang.String)}.
	 */
	@Test
	public void testConvertTimeForPositiveCase() {	
	
		when(berlinClockConverterMock.createBerlinClock(13, 17, 01)).thenReturn("O" + LINE_SEPARATOR + "RROO"
				+ LINE_SEPARATOR + "RRRO" + LINE_SEPARATOR + "YYROOOOOOOO" + LINE_SEPARATOR + "YYOO");

		String berlinClock = timeConverterImpl.convertTime("13:17:01");
		assertEquals(berlinClock, "O" + LINE_SEPARATOR + "RROO" + LINE_SEPARATOR + "RRRO" + LINE_SEPARATOR
				+ "YYROOOOOOOO" + LINE_SEPARATOR + "YYOO");
	}

	@Test
	public void testConvertTimeMidNight() {
		
		when(berlinClockConverterMock.createBerlinClock(00, 00, 00)).thenReturn("Y" + LINE_SEPARATOR + "OOOO" + LINE_SEPARATOR + "OOOO" + LINE_SEPARATOR
				+ "OOOOOOOOOOO" + LINE_SEPARATOR + "OOOO");
		
		String berlinClock = timeConverterImpl.convertTime("00:00:00");
		assertEquals(berlinClock, "Y" + LINE_SEPARATOR + "OOOO" + LINE_SEPARATOR + "OOOO" + LINE_SEPARATOR
				+ "OOOOOOOOOOO" + LINE_SEPARATOR + "OOOO");
	}
	

	
	@Test
	public void testConvertTimeBeforeMidNight() {
		
		when(berlinClockConverterMock.createBerlinClock(23, 59, 59)).thenReturn("O" + LINE_SEPARATOR + "RRRR" + LINE_SEPARATOR + "RRRO" + LINE_SEPARATOR
				+ "YYRYYRYYRYY" + LINE_SEPARATOR + "YYYY");
		
		String berlinClock = timeConverterImpl.convertTime("23:59:59");
		assertEquals(berlinClock, "O" + LINE_SEPARATOR + "RRRR" + LINE_SEPARATOR + "RRRO" + LINE_SEPARATOR
				+ "YYRYYRYYRYY" + LINE_SEPARATOR + "YYYY");
	}
	
	
	@Test
	public void testConvertTimeWithHoursMoreThan24ThrowsException() {
		
		when(berlinClockConverterMock.createBerlinClock(24, 00, 00)).thenReturn("Y" + LINE_SEPARATOR + "RRRR" + LINE_SEPARATOR + "RRRR" + LINE_SEPARATOR
				+ "OOOOOOOOOOO" + LINE_SEPARATOR + "OOOO");
		
		expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("Not a valid hour. Please enter hours between 0 and 23");
	    
		timeConverterImpl.convertTime("24:00:00");		
	}
	
	@Test
	public void testConvertTimeWithMinutesMoreThan59ThrowsException() {
		
		when(berlinClockConverterMock.createBerlinClock(23, 70, 00)).thenReturn("Y" + LINE_SEPARATOR + "RRRR" + LINE_SEPARATOR + "RRRO" + LINE_SEPARATOR
				+ "YYRYYRYYRYYRYY" + LINE_SEPARATOR + "OOOO");
		
		expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("Not a valid minute. Please enter hours between 0 and 59");
	    
		timeConverterImpl.convertTime("23:70:00");		
	}
	
	
	
	@Test
	public void testConvertTimeWithSecondsMoreThan59ThrowsException() {
		
		when(berlinClockConverterMock.createBerlinClock(23, 00, 80)).thenReturn("Y" + LINE_SEPARATOR + "RRRR" + LINE_SEPARATOR + "RRRO" + LINE_SEPARATOR
				+ "OOOOOOOOOOO" + LINE_SEPARATOR + "OOOO");
		
		expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("Not a valid second. Please enter hours between 0 and 59");
	    
		timeConverterImpl.convertTime("23:00:80");		
	}	

}
