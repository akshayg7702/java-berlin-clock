/**
 * 
 */
package com.ubs.opsit.interviews.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.ubs.opsit.interviews.impl.BerlinClockConverter;

/**
 * @author Akshay
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BerlinClockConverterTest {
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	@Spy
	BerlinClockConverter berlinClockConverter;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		berlinClockConverter = new BerlinClockConverter();
	}

	/**
	 * Test method for {@link com.ubs.opsit.interviews.impl.BerlinClockConverter#createBerlinClock(int, int, int)}.
	 */
	@Test
	public final void testCreateBerlinClockForMidNight() {
		String berlinClock = berlinClockConverter.createBerlinClock(00, 00, 00);
		assertEquals(berlinClock, "Y"+LINE_SEPARATOR+"OOOO"+LINE_SEPARATOR+"OOOO"+LINE_SEPARATOR+"OOOOOOOOOOO"+LINE_SEPARATOR+"OOOO");
	}
	
	@Test
	public void testConvertTimeForPositiveCase() {
		String berlinClock = berlinClockConverter.createBerlinClock(13, 17, 01);
		assertEquals(berlinClock, "O"+LINE_SEPARATOR+"RROO"+LINE_SEPARATOR+"RRRO"+LINE_SEPARATOR+"YYROOOOOOOO"+LINE_SEPARATOR+"YYOO");
	}
	
	@Test
	public void testConvertTimeForNegativeCase() {
		String berlinClock = berlinClockConverter.createBerlinClock(10, 00, 11);
		assertNotEquals(berlinClock, "O"+LINE_SEPARATOR+"RROO"+LINE_SEPARATOR+"RRRO"+LINE_SEPARATOR+"YYROOOOOOOO"+LINE_SEPARATOR+"YYOO");
	}

}
