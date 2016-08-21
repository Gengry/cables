package com.gry.cable.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author lihonggang 日期类，目前提供的方法 有： Date和String之间的互转功能 计算两个日期之间的时间差方法，有计算相差分钟的
 */
public class DateUtil {

	public static Date now() {
		return Calendar.getInstance().getTime();
	}

	public static String toString(Date d) {
		return toString(d, "yyyy-MM-dd HH:mm:ss");
	}

	public static String toString(Date d, String fmt) {
		if (null == d)
			return "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			return sdf.format(d);
		} catch (Exception e) {

		}
		return d.toString();
	}

	public static Date toDate(String d, String fmt) {
		if (null == d)
			return new Date();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			return sdf.parse(d);
		} catch (Exception e) {

		}
		return new Date();
	}

	/**
	 * 得到二个日期间的间隔分钟数
	 * 
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return 开始时间和结束时间之间相差的分钟数
	 */
	public static int getMinutes(Date begin, Date end) {
		long m = 0;

		try {
			m = (end.getTime() - begin.getTime()) / (60 * 1000);
		} catch (Exception e) {
			return 0;
		}

		return (int) m;
	}

	public static String addDay(int days) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return myFormatter.format(calendar.getTime());
	}

	public static String addDay(String date, int days) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		try {
			trialTime = myFormatter.parse(date);
		} catch (ParseException e) {
		}
		calendar.setTime(trialTime);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return myFormatter.format(calendar.getTime());
	}

	public static String subDay(int days) {
		// SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		// Calendar calendar = new GregorianCalendar();
		// Date trialTime = new Date();
		// calendar.setTime(trialTime);
		// calendar.add(Calendar.DAY_OF_MONTH, -days);
		return addDay(-days);
	}

	public static String subDay(String date, int days) {
		return addDay(date, -days);
	}

	public static String addMonth(int month) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		calendar.add(Calendar.MONTH, month);
		return myFormatter.format(calendar.getTime());
	}

	public static String subMonth(int month) {
		// SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		// Calendar calendar = new GregorianCalendar();
		// Date trialTime = new Date();
		// calendar.setTime(trialTime);
		// calendar.add(Calendar.MONTH, -month);
		return addMonth(-month);// myFormatter.format(calendar.getTime());
	}

	public static void main(String[] args) throws Exception {
		System.out.print(DateUtil.subDay("2010-3-1", 1));
	}

}