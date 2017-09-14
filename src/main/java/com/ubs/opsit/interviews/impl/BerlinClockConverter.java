package com.ubs.opsit.interviews.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ubs.opsit.interviews.util.Constants;

public class BerlinClockConverter {
	private Logger log = Logger.getLogger(BerlinClockConverter.class);

	/**
	 * Create the Berlin Clock notation for specified Hours, Minutes and Seconds
	 * 
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @return
	 */
	public String createBerlinClock(int hours, int minutes, int seconds) {

		String line0 = (seconds % 2 == 0) ? Constants.YELLOW_LIGHT : Constants.OFF;
		String line1 = getBerlinClockLine(hours / 5, 4, Constants.RED_LIGHT);
		String line2 = getBerlinClockLine(hours % 5, 4, Constants.RED_LIGHT);
		String line3 = getBerlinClockLine(minutes / 5, 11, Constants.YELLOW_LIGHT).replaceAll("YYY", "YYR");
		String line4 = getBerlinClockLine(minutes % 5, 4, Constants.YELLOW_LIGHT);

		log.debug("Line 0 : "+line0);
		log.debug("Line 1 : "+line1);
		log.debug("Line 2 : "+line2);
		log.debug("Line 3 : "+line3);
		log.debug("Line 4 : "+line4);
		
		StringBuilder berlinClockString = new StringBuilder();
		berlinClockString.append(line0).append(Constants.LINE_SEPARATOR);
		berlinClockString.append(line1).append(Constants.LINE_SEPARATOR);
		berlinClockString.append(line2).append(Constants.LINE_SEPARATOR);
		berlinClockString.append(line3).append(Constants.LINE_SEPARATOR);
		berlinClockString.append(line4);

		return berlinClockString.toString();
	}

	/**
	 * Construct the Berlin Clock rows with proper notation
	 * 
	 * @param onLights
	 * @param noOfLightsInARow
	 * @param notation
	 * @return
	 */
	private String getBerlinClockLine(int onLights, int maxLightsInARow, String notation) {
		int offLights = maxLightsInARow - onLights;
		log.debug("Number of On Light : "+ onLights+" \n Number of Off Light : "+offLights);
		String onLight = StringUtils.repeat(notation, onLights);
		String offLight = StringUtils.repeat(Constants.OFF, offLights);

		return onLight + offLight;
	}
}
