package com.bjb.pockit.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtil {

	public static LocalDateTime generateDateTimeIndonesia(){
		return LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
	}

}
