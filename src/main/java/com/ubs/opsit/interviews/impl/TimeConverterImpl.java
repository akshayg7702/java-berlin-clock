package com.ubs.opsit.interviews.impl;

import org.apache.log4j.Logger;

import com.ubs.opsit.interviews.TimeConverter;
import com.ubs.opsit.interviews.util.Constants;

/**
 * Implementation to convert the digital time to Berlin Clock format
 * 
 * @author Akshay.Gadabad
 */
public class TimeConverterImpl implements TimeConverter {
	
	private Logger log = Logger.getLogger(TimeConverterImpl.class);

	BerlinClockConverter berlinClockConverter; 	
	
	public TimeConverterImpl() {
		berlinClockConverter = new BerlinClockConverter();
	}

	/**
	 * Convert the digital time to Berlin Clock time notation
	 * 
	 * @param aTime
	 * @return time in Berlin Clock format
	 */
	@Override
	public String convertTime(String time) {
		if (time == null || time.isEmpty()) {
			throw new IllegalArgumentException("Time is not entered. Please time in HH:MM:SS format");
		}
		String[] timeArray = time.split(Constants.COLON_SEPARATOR);

		if (timeArray.length != Constants.NO_OF_TIME_SECTION)
			throw new IllegalArgumentException("Invalid time.Please enter valid time in HH:MM:SS format");
		
		int hours=0;
		int minutes=0;
		int seconds=0;
		
		try {
			hours = Integer.parseInt(timeArray[0]);
			minutes = Integer.parseInt(timeArray[1]);
			seconds = Integer.parseInt(timeArray[2]);
			
			log.debug("Hours : "+hours+" Minutes : "+minutes+ " Seconds : "+seconds);
			
		} catch (IllegalArgumentException e) {
			throw new NumberFormatException("NumberFormatException found while parsing String to Integer");
		}
			
		if (hours < Constants.MIN_TIME_LIMIT || hours > Constants.MAX_HOURS)
			throw new IllegalArgumentException("Not a valid hour. Please enter hours between 0 and 23");
		if (minutes < Constants.MIN_TIME_LIMIT || minutes > Constants.MAX_MINUTES)
			throw new IllegalArgumentException("Not a valid minute. Please enter hours between 0 and 59");
		if (seconds < Constants.MIN_TIME_LIMIT || seconds > Constants.MAX_SECONDS)
			throw new IllegalArgumentException("Not a valid second. Please enter hours between 0 and 59");
		
		log.info("Calling createBerlinClock method of BerlinClockConverter");
		
		return berlinClockConverter.createBerlinClock(hours, minutes, seconds);
	}
}
