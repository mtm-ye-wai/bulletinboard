package com.mtm.bulletinboard.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter
			.ofPattern("yyyy/MM/dd");

	public static String formatDateTime(LocalDateTime dateTime) {
		return dateTime.format(OUTPUT_FORMATTER);
	}
}
