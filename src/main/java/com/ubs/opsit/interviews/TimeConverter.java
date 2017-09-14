package com.ubs.opsit.interviews;

/**
 * Interface to convert the digital time to Berlin Clock format
 * 
 * @author Akshay
 */
public interface TimeConverter {

	/**
	 * Convert the digital time to Berlin Clock time notation
	 * 
	 * @param aTime
	 * @return time in Berlin Clock format
	 */
    String convertTime(String aTime);

}
