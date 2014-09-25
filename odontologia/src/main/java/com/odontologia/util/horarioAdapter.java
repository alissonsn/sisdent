package com.odontologia.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class horarioAdapter {

	@SuppressWarnings("deprecation")
	public static Date fromSqlToDate(Timestamp fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, fecha.getDate());
		calendar.set(Calendar.MONTH, fecha.getMonth());
		calendar.set(Calendar.YEAR, fecha.getYear());
		if (fecha.getHours() >= 12) {
			calendar.set(Calendar.AM_PM, Calendar.PM);
		} else {
			calendar.set(Calendar.AM_PM, Calendar.AM);
		}
		calendar.set(Calendar.HOUR, from24to12(fecha));
		calendar.set(Calendar.MINUTE, fecha.getMinutes());
		calendar.set(Calendar.YEAR, fecha.getYear()+1900);			
		return calendar.getTime();
	}

	@SuppressWarnings("deprecation")
	public static Integer from24to12(Timestamp fecha) {
		if (fecha.getHours() >= 0 && fecha.getHours() <= 11) {
			return fecha.getHours();
		} else {
			return fecha.getHours() - 12;
		}
	}

}
