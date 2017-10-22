package com.wangzhixuan.commons.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作
 * @author zhaozhi3758
 */
public class DateUtil {
	public static final String LONG_MODEL = "yyyy-MM-dd HH:mm:ss";
	public static final String LONG3_MODEL = "yyMMddHHmmss";
	public static final String LONG2_MODEL = "yyyy-MM-dd HH:mm";
	public static final String SHORT_MODEL = "yyyy-MM-dd";
	public static final String TIME_MODEL = "hh:mm:ss";
	public static final String YEAR_MODEL = "yyyy";
	
	/**
	 * 一小时毫秒
	 */
	public static final Long HOUR_MILLIS = 1000L*60*60;
	/**
	 * 一天毫秒
	 */
	public static final Long DAY_MILLIS = HOUR_MILLIS*24;
	
	/**
	 * 时间格式化
	 * @param d
	 * @return
	 */
	public static String dateFormat(Date d){
		SimpleDateFormat sdf=new SimpleDateFormat(LONG_MODEL);
		return sdf.format(d);
	}
	
/**
 * 年月日格式化
 * @param d
 * @return
 */
	public static String dateFormatForymd(Date d){
		SimpleDateFormat sdf=new SimpleDateFormat(SHORT_MODEL);
		return sdf.format(d);
	}
		
	
	
	public static String dateFormatShort(Date d){
		SimpleDateFormat sdf=new SimpleDateFormat(LONG3_MODEL);
		return sdf.format(d);
	}
	
	/**
	 * 时间格式化
	 * @param d
	 * @return
	 */
	public static String dateFormatforYear(Date d){
		SimpleDateFormat sdf=new SimpleDateFormat(YEAR_MODEL);
		return sdf.format(d);
	}
	
	/**
	 * 时间格式化
	 * @param d
	 * @return
	 */
	public static String dateFormat(Date d,String model){
		if (d==null) {
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(model);
		return sdf.format(d);
	}
/*	*//**
	 * 判断日期 格式化后是否相同
	 * @param d
	 * @param d2
	 * @return
	 *//*
	public static Boolean equals(Date d,Date d2,String model){
		SimpleDateFormat sdf=new SimpleDateFormat(model);
		return StringUtils.equals(sdf.format(d), sdf.format(d2));
	}
	*/
	/**
	 * 字符串转日期，支持两种格式 1."日期 时间" 2."纯日期"
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static Date StringToDate(String s){
		
		DateFormat sdf = new SimpleDateFormat(LONG_MODEL);
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			DateFormat sdf2 = new SimpleDateFormat(SHORT_MODEL);
			try {
				return sdf2.parse(s);
			} catch (ParseException e2) {}
		}
		return null;
	}
	
	/**
	 * 字符串转日期，"纯日期"
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static Date StringToDateTo2(String s){
		
		DateFormat sdf = new SimpleDateFormat(SHORT_MODEL);
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			DateFormat sdf2 = new SimpleDateFormat(SHORT_MODEL);
			try {
				return sdf2.parse(s);
			} catch (ParseException e2) {}
		}
		return null;
	}
	
	/**
	 * 字符串转年份，"纯日期"
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static Date StringToDateToYear(String s){
		
		DateFormat sdf = new SimpleDateFormat(SHORT_MODEL);
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			DateFormat sdf2 = new SimpleDateFormat(SHORT_MODEL);
			try {
				return sdf2.parse(s);
			} catch (ParseException e2) {}
		}
		return null;
	}
	
	
	public static Date StringToDate(String s,String format){
		if(s.length()>0){
			try {
				DateFormat sdf = new SimpleDateFormat(format);
				return sdf.parse(s);
			} catch (ParseException e) {
			}
		}
		return null;
	}
	

	/**
	 * 计算 h个小时后的时间 "以当前时间为基准"
	 * @param h
	 * @return
	 */
	public static Date calculateDate(int h){
		
		return calculateDate(new Date(),h,0);
	}
	
	/**
	 * 计算 h个小时，m分钟后的时间 "以当前时间为基准"
	 * @param h
	 * @param m
	 * @return
	 */
	public static Date calculateDate(int h,int m){
		
		return calculateDate(new Date(),h,m);
	}
	
	/**
	 * 计算 h个小时后的时间 "以fromdate为基准"
	 * @param fromdate
	 * @param h
	 * @return
	 */
	public static Date calculateDate(Date fromdate, int h){
		
		return calculateDate(fromdate,h,0);
	}
	
	/**
	 * 计算 h个小时，m分钟后的时间 "以fromdate为基准"
	 * @param fromdate
	 * @param h
	 * @param m
	 * @return
	 */
	public static Date calculateDate(Date fromdate, int h, int m){
		
		Date date = null; 
		Calendar cal = Calendar.getInstance();  
		cal.setTime(fromdate); 
		cal.add(Calendar.HOUR_OF_DAY, h);
		cal.add(Calendar.MINUTE, m);
		date = cal.getTime(); 
		return date;
	}
	
	/**
	 * 两个时间相差的分钟数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long dateDiff(Date d1,Date d2){
		
		return dateCompare(d1,d2) / 1000 / 60;
	}
	
	/**
	 * 时间大小的比较，返回相差的毫秒数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long dateCompare(Date d1,Date d2){
		Calendar cal = Calendar.getInstance(); 
		Calendar ca2 = Calendar.getInstance(); 
		cal.setTime(d1);
		ca2.setTime(d2);
		long l1 = cal.getTimeInMillis();
		long l2 = ca2.getTimeInMillis();
		return l1 - l2;
	}
	/**
	 * 毫秒转换小时
	 * @param millis
	 * @return
	 */
	public static Integer toHour(Long millis){
		return Long.valueOf(millis/HOUR_MILLIS).intValue();
	}
	/**
	 * 转换成天
	 * @param millis
	 * @return
	 */
	public static Integer toDay(Long millis){
		return Long.valueOf(millis/DAY_MILLIS).intValue();
	}
	/**
	 * 获取下个月第一天
	 * @param date
	 * @return
	 */
	public static Date nextMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	public static Date nextDay(Date date){
		return addDay(date, 1);
	}
	
	public static Date addYear(Date date,int year){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}
	public static Date addMonth(Date date,int month){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}
	public static Date addDay(Date date,int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}
	
	/**
	 * 本月第一天
	 * @param date
	 * @return
	 */
	public static Date firstDayOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	public static Integer upYear(Date date){
		Calendar calendar = Calendar.getInstance();
		if(date!=null){
			calendar.setTime(date);
		}else{
			calendar.setTime(new Date());
		}
		return calendar.get(Calendar.YEAR)-1;
	}
	
	public static Date currentDate(){
		return new Date();
	}
	public static Date currentDate(String format){
		String d = dateFormat(currentDate(),format);
		return StringToDate(d, format);
	}
	public static Date toDate(Date date,String format){
		String d = dateFormat(date,format);
		return StringToDate(d, format);
	}
	
	public static Long currentTimeMillis(){
		return System.currentTimeMillis();
	}
	
	public static String getMaxDate(String dates,String dateFormat){
		String[] strs = dates.split(",");
		String result = "";
		Date d;
		Date max = null;
		for(int i=0;i<strs.length;i++){
			d = StringToDate(strs[i], dateFormat);
			if(d == null){
				continue;
			}
			if(max==null||max.getTime()<d.getTime()){
				max = d;
			}
		}
		if(max!=null){
			result = dateFormat(max, dateFormat);
		}
		return result;
	}

	public static Date dateTimeNow() {
		return new Date(System.currentTimeMillis());
	}

// 往后加一天
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
	}

	public static void main(String[] args) {
	
	System.out.println(dateFormatForymd(addDay(new Date(), 0)));
}
}


