package com.unuse.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtil.java
 * 
 * Create by Unuse on 2016-11-28
 */

public class DateUtil {

	private static Logger logger = Logger.getLogger(DateUtil.class);

	public static final long ONE_DAY_MSEC = 24 * 3600 * 1000;
	public static final long ONE_MIN_MSEC = 60 * 1000;
	public static final long ONE_MIN_SEC = 60;
	public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
	public static final String SIMPLE_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	
	public static String formatDate(String pattern){
		return formatDate(pattern, new Date());
	}
	
	public static String formatDate(String pattern, Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static Date toDate(String pattern, String str){
		if (null == str || str.isEmpty()) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			logger.error("DateUtil toDate" ,  e);
		}
		
		return date;
	}
	
	public static Date parseDate(String tmp) {
		if(null == tmp) {
			return null ; 
		}
		
		tmp = tmp.trim(); 
		
		int dateStrLength = "yyyy-MM-dd HH:mm:ss".length();

		if (dateStrLength*2 == tmp.length()) {
			tmp = tmp.substring(dateStrLength, dateStrLength*2);
		} else if (tmp.length() > dateStrLength) {
			tmp = tmp.substring(0, dateStrLength);
		}
		
		String format = tmp.indexOf("-") > 0 ? "yyyy-MM-dd HH:mm:ss" : "yyyy:MM:dd HH:mm:ss";
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			
			return sdf.parse(tmp);
		} catch (ParseException e) {

		} 
		
		return null;
	}
	
	public static Date getYesterdayStartTime() {
        return getYesterdayStartOrEnd(true); 
    } 
	
	public static Date getYesterdayEndTime() {
        return getYesterdayStartOrEnd(false); 
    }
	
	private static Date getYesterdayStartOrEnd(boolean isStart) {
		String format = null;
		if (isStart) {
			format = " 00:00:00";
		} else {
			format = " 23:59:59";
		}
		
		Date yes = new Date();
        
        try { 
        	Calendar cal1 = Calendar.getInstance();
        	cal1.add(Calendar.DATE,-1);
        	
        	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	
        	String yesterday =  sf.format(cal1.getTime()) + format;
        	yes = sdf.parse(yesterday); 
        	
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
      		
        return yes; 
	}
	
	
	public static  Date getCurrentDayStartTime() {
        return getDayStartOrEnd(new Date(), true);
    }
	

	public static  Date getCurrentDayEndTime() {
        return getDayStartOrEnd(new Date(), false);
    } 
	
	public static  Date getDayStartTime(Date cur) {
        return getDayStartOrEnd(cur, true);
    } 
	
	public static  Date getDayEndTime(Date cur) {
        return getDayStartOrEnd(cur, false);
    } 
	
	private static Date getDayStartOrEnd(Date date, boolean isStart) {
		Date now = null;
		String format = null;
		if (isStart) {
			format = " 00:00:00";
		} else {
			format = " 23:59:59";
		}
		
        try {
        	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            now = sdf.parse(sf.format(date) + format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return now; 
	}
	
	public static long calculateMinutes(Date preTime, Date curTime) {
		return (curTime.getTime() - preTime.getTime())/(1000*60);
	}
	
	public static long calculateSeconds(Date preTime, Date curTime) {
		return (curTime.getTime() - preTime.getTime())/(1000);
	}
	
	
	public static long calculateHours(Date preTime, Date curTime) {
		return (curTime.getTime() - preTime.getTime())/(1000*60*60);
	}
	
	//get the day startTime as you want
	public static Date getDateStartTimeAsWant(int i) {
	    return getDateStartOrNowOrEndAsWant(i, 1);
	}
	
	//get the day endTime as you want
	public static Date getDateEndTimeAsWant(int i) {
	    return getDateStartOrNowOrEndAsWant(i, 2);
	}
	
	//get the day now time as you want
	public static Date getDateNowTimeAsWant(int i) {
	    return getDateStartOrNowOrEndAsWant(i, 3);
	}
	
	private static Date getDateStartOrNowOrEndAsWant(int i, int type) {
		Date date = new Date();
		String format = null;
		if (1 == type) {
			format = " 00:00:00";
		} else if (2 == type) {
			format = " 23:59:59";
		} else {
        	SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
			format = " " + sf.format(date);
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, i);
		date = calendar.getTime();
		
		try {
	       	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	       	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	date = sdf.parse(sf.format(date) + format); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	
}
