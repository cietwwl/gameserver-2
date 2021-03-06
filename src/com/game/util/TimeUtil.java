package com.game.util;

import com.server.util.ServerLogger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Supplier;

/**
 * 关于时间的工具类
 */
public class TimeUtil {
	
	public static String CUR_TIME_FORMAT = "";
	
	public static final long ONE_SECOND = 1000;

	public static final long ONE_MIN = 60 * ONE_SECOND;
	public static final long ONE_HOUR = 60 * ONE_MIN;
	public static final long HALF_HOUR = 30 * ONE_MIN;
	public static final long ONE_DAY = 24 * ONE_HOUR;
	
	//public static final SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

	public static final ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(new Supplier<SimpleDateFormat>() {
		@Override
		public SimpleDateFormat get() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	});

	/**
	 * 获得两个时间相差的分钟数
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public static int getDifferMin(Date one, Date two) {
		if (null == one || null == two) {
			return 0;
		}
		return (int) ((two.getTime() - one.getTime()) / (1000 * 60));
	}

	/**
	 * 获得两个时间相差的秒数
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public static int getDifferSec(Date one, Date two) {
		if (null == one || null == two) {
			return 0;
		}
		return (int) ((two.getTime() - one.getTime()) / (1000));
	}

	public static long getTimeNow() {
		return System.currentTimeMillis();
	}

	/**
	 * 今天凌晨时间
	 */
	public static long getTodayBeginTime() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}
	
	public static boolean isSameDate(long one,long two){
		Calendar a = Calendar.getInstance();
		a.setTimeInMillis(one);
		Calendar b = Calendar.getInstance();
		b.setTimeInMillis(two);
		
		return a.get(Calendar.YEAR)==b.get(Calendar.YEAR)&&a.get(Calendar.MONTH)==b.get(Calendar.MONTH)&&a.get(Calendar.DATE)==b.get(Calendar.DATE);
	}
	
	//计算时间消逝
	public static int timePassSec(long one,long two){
		long pass = (one-two)/1000l;
		return (int)pass;
	}
	
	public static Calendar parseDateTime(String str){
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.get().parse(str));
		} catch (ParseException e) {
			ServerLogger.err(e, null);
		}
		return cal;
	}
}
