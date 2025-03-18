package com.bjb.pockit.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class StringUtil {

	public static String setTransactionType(String transactionType){
		return switch (transactionType) {
			case "1" -> "Income";
			case "2" -> "Expense";
			case "3" -> "Income Split Bill";
			case "4" -> "Expense Split Bill";
			default -> "Other";
		};
	}

}
