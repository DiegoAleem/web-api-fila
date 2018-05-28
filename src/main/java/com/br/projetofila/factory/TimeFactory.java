package com.br.projetofila.factory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFactory {
	
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static Date getCurrentTime() {
    	Date date = new Date();
    	return date;
    }
	
	public static String getCurrentTimeAsString() {
		Date date = new Date();
		String dateString = formatter.format(date);
        return dateString;
    }
	
}
