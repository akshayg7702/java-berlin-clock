package com.ubs.opsit.interviews.impl;

import org.apache.commons.lang.StringUtils;

import com.ubs.opsit.interviews.TimeConverter;

public class BerlinClockTimeConverterImpl implements TimeConverter {

	private static final int MIN_TIME_LIMIT = 0;
	private static final int MAX_SECONDS = 59;
	private static final int MAX_MINUTES = 59;
	private static final int MAX_HOURS = 23;
	protected static final int NO_OF_TIME_SECTION = 3;

	private static final String RED_LIGHT = "R";
	private static final String YELLOW_LIGHT = "Y";
	private static final String OFF = "O";
	private static final String INVALID_HOUR_ERROR = "Not a valid hour. Please enter hours between 0 and 23";
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final String TIME_NOT_FOUND_ERROR = "Time is not entered. Please time in HH:MM:SS format";
	private static final String INVALID_TIME_FORMAT_ERROR = "Invalid time.Please enter valid time in HH:MM:SS format";
	private static final String NUMERIC_TIME_ERROR = "Time values must be numeric.";
	private static final String INVALID_SECOND_ERROR = "Not a valid second. Please enter hours between 0 and 59";
	private static final String INVALID_MINUTE_ERROR = "Not a valid minute. Please enter hours between 0 and 59";

	@Override
	public String convertTime(String time) {
		int hours = 0;
		int minutes = 0;
		int seconds = 0;

		if (time == null || time.isEmpty()) {
			throw new IllegalArgumentException(TIME_NOT_FOUND_ERROR);
		}
		String[] timeArray = time.split(":");

		if (timeArray.length != NO_OF_TIME_SECTION)
			throw new IllegalArgumentException(INVALID_TIME_FORMAT_ERROR);

		try {
			hours = Integer.parseInt(timeArray[0]);
			minutes = Integer.parseInt(timeArray[1]);
			seconds = Integer.parseInt(timeArray[2]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(NUMERIC_TIME_ERROR);
		}

		if (hours < MIN_TIME_LIMIT || hours > MAX_HOURS)
			throw new IllegalArgumentException(INVALID_HOUR_ERROR);
		if (minutes < MIN_TIME_LIMIT || minutes > MAX_MINUTES)
			throw new IllegalArgumentException(INVALID_MINUTE_ERROR);
		if (seconds < MIN_TIME_LIMIT || seconds > MAX_SECONDS)
			throw new IllegalArgumentException(INVALID_SECOND_ERROR);

		return createBerlinClock(hours, minutes, seconds);
	}

	private String createBerlinClock(int hours, int minutes, int seconds) {

		String line1 = (seconds % 2 == 0) ? YELLOW_LIGHT : OFF;
		String line2 = getBerlinClockLine(hours / 5, 4, RED_LIGHT);
		String line3 = getBerlinClockLine(hours % 5, 4, RED_LIGHT);
		String line4 = getBerlinClockLine(minutes / 5, 11, YELLOW_LIGHT).replaceAll("YYY", "YYR");
		String line5 = getBerlinClockLine(minutes % 5, 4, YELLOW_LIGHT);

		StringBuilder berlinClockString = new StringBuilder();
		berlinClockString.append(line1).append(LINE_SEPARATOR);
		berlinClockString.append(line2).append(LINE_SEPARATOR);
		berlinClockString.append(line3).append(LINE_SEPARATOR);
		berlinClockString.append(line4).append(LINE_SEPARATOR);
		berlinClockString.append(line5).append(LINE_SEPARATOR);

		return berlinClockString.toString();
	}

	private String getBerlinClockLine(int onLights, int noOfLightsInARow, String notation) {
		int offLights = noOfLightsInARow - onLights;
		String on = StringUtils.repeat(notation, onLights);
		String off = StringUtils.repeat(OFF, offLights);

		return on + off;
	}	

}
