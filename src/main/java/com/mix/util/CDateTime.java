/*
 * 版权所有 (C) 2001-2012 深圳市有信网络科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *      1、2012-7-29，JiangQian创建。 
 */
package com.mix.util;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class CDateTime {
    public static final int YYMMDDhhmmssxxx = 15;

    public static final int YYYYMMDDhhmmss = 14;

    public static final int YYMMDDhhmmss = 12;

    public static final int YYMMDDhhmm = 10;

    public static final int YYMMDDhh = 8;

    public static final String TIME_FORMAT_yyyy = "yyyy";

    public static final String TIME_FORMAT_MM = "MM";

    public static final String TIME_FORMAT_dd = "dd";
    
    /** 字符串缺省状态 */
    private static final boolean DEFAULT_EMPTY_OK = false;

    /** 定义12月份对应的天数 */
    private static final int[] DAYS_IN_MONTH = { 31, 29, 31, 30, 31, 30, 31,31, 30, 31, 30, 31 };
    
    private static final SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 默认的构造函数
     */
    public CDateTime() {
        mCalendar = Calendar.getInstance();
        mDate = new java.util.Date();
    }

    /**
     * 给出初始日期的构造函数
     * @param pDate  初始日期
     */
    public CDateTime(java.util.Date pDate) {
        mCalendar = Calendar.getInstance();
        mDate = pDate;
    }

    /**
     * 给出年，月，日来生成一个日期的构造函数
     * 
     * @param piYear    年份
     * @param piMonth   月
     * @param piDay     日
     */
    public CDateTime(int piYear, int piMonth, int piDay) {
        mCalendar = Calendar.getInstance();
        mCalendar.set(piYear, piMonth, piDay);
        mDate = mCalendar.getTime();
    }

    public CDateTime(int y, int m, int d, int h, int mi, int sc) {
        mCalendar = Calendar.getInstance();
        mCalendar.set(y, m, d, h, mi, sc);
        mDate = mCalendar.getTime();
    }

    /**
     * 增加时间
     * @param MilSec    增加的微秒数
     * @return 修改后的时间
     */
    public void addTime(int piMilSec) {
        long nd = mDate.getTime();
        nd += (piMilSec);
        mDate = new java.util.Date(nd);
    }

    /**
     * 增加时间
     * @param Day   增加的天数
     * @return 修改后的时间
     */
    public void addDay(int piDay) {
        long nd = mDate.getTime();
        nd += (piDay * 24 * 60 * 60 * 1000);
        mDate = new java.util.Date(nd);
    }

    /**
     * 下一天
     * @return 修改后的时间
     */
    public void nextDay() {
        addDay(1);
    }

    /**
     * 前一天
     * @return 修改后的时间
     */
    public void prevDay() {
        addDay(-1);
    }

    /**
     * 获取时间
     * @return 修改后的时间
     */
    public java.util.Date getTime() {
        return mDate;
    }

    // java.sql.Date
    public java.sql.Date tosqlDate() {
        if (mDate == null)
            return new java.sql.Date(0);
        return new java.sql.Date(mDate.getTime());
    }

    // java.sql.Time
    public java.sql.Time tosqlTime() {
        if (mDate == null)
            return null;
        return new java.sql.Time(mDate.getTime());
    }

    // java.sql.Timestamp
    public java.sql.Timestamp tosqlTimestamp() {
        if (mDate == null) {
            return new java.sql.Timestamp(new java.util.Date().getTime());
        }
        return new java.sql.Timestamp(mDate.getTime());
    }

    // java.sql.Timestamp
    public Object tosqlObject() {
        if (mDate == null) {
            return new java.sql.Timestamp(new java.util.Date().getTime());
        }
        return new java.sql.Timestamp(mDate.getTime());
    }

    public int getMonthDays(String year, String month) {
        int ayear, amonth;
        try {
            ayear = Integer.parseInt(year);
            amonth = Integer.parseInt(month);
        } catch (Exception e) {
            return -1;
        }
        return getMonthDays(ayear, amonth);
    }

    public int getMonthDays(int year, int month) {
        int days = -1;
        int monthdays[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (month >= 1 && month <= 12)
            days = monthdays[month - 1];
        if ((month == 2) && isLeapYear(year))
            days = 29;
        return (days);

    }

    /**
     * 获取微秒时间
     * @return 修改后的时间
     */
    public long getLongTime() {
        return mDate.getTime();
    }

    /**
     * 时间格式的转换
     * 
     * @param psFormat  格式字符串
     * @return 转换后的字符串
     */
    public String format(String psFormat) {
        if (mDate == null)
            return "";
        SimpleDateFormat oFormat = new java.text.SimpleDateFormat(psFormat);
        return oFormat.format(mDate);
    }
    
    /**
     * 时间格式的转换
     * 
     * @param psFormat  日期
     * @param psFormat  格式
     * @return 转换后的时间字符串
     */
    public static String format(java.util.Date pDate, String psFormat) {
        if (pDate == null)
            return "";
        SimpleDateFormat oFormat = new java.text.SimpleDateFormat(psFormat);
        return oFormat.format(pDate);
    }

    /**
     * 数据库时间格式的转换
     * 
     * @param pDate 日期
     * @param psFormat  格式
     * @return 转换后的时间字符串
     */
    public static String format(java.sql.Date pDate, String psFormat) {
        if (pDate == null)
            return "";
        SimpleDateFormat oFormat = new java.text.SimpleDateFormat(psFormat);
        return oFormat.format(pDate);
    }

    /**
     * 得到加、减后的日期
     * 
     * @param pDate Date 时间
     * @param pField    int 被操作的域（Calendar.YEAR / Calendar.MONTH / Calendar.DATE 等）
     * @param pCount    int 加、减的数量
     * @return Date
     */
    public static java.util.Date getTime(java.util.Date pDate, int pField, int pCount) {
        java.util.Calendar gc = new GregorianCalendar();
        gc.setTime(pDate);
        gc.add(pField, pCount);
        gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
        return gc.getTime();
    }

    /**
     * 得到加、减后的日期
     * 
     * @param pDate Date 时间
     * @param pField    int 被操作的域（Calendar.YEAR / Calendar.MONTH / Calendar.DATE 等）
     * @param pCount    int 加、减的数量
     * @return Date
     * @throws Exception 
     */
    public static java.util.Date getTime(java.sql.Timestamp pTimestamp, int pField, int pCount)
            throws Exception {
        java.util.Date pDate = convertTimestampToDate(pTimestamp);
        return getTime(pDate, pField, pCount);
    }

    /**
     * 获取当前时间的 String 类型
     * @return 当前时间的 String 类型
     */
    public static String getTimeString(String sFormat) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(sFormat);
        try {
            return format.format(new java.util.Date());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 产生任意位的字符串
     * 
     * @param time      要转换格式的时间
     * @param format    转换的格式
     * @return String   转换的时间
     */
    private synchronized static String getFormatTime(int time, int format) {
        StringBuffer numm = new StringBuffer();
        int length = String.valueOf(time).length();

        if (format < length)
            return null;

        for (int i = 0; i < format - length; i++) {
            numm.append("0");
        }
        numm.append(time);
        return numm.toString().trim();
    }

    /**
     * 取得本地系统的时间，时间格式由参数决定
     * 
     * @param format    时间格式由常量决定
     * @return String   具有format格式的字符串
     */
    public synchronized static String getTime(int format) {
        StringBuffer cTime = new StringBuffer(10);
        Calendar time = Calendar.getInstance();
        int miltime = time.get(Calendar.MILLISECOND);
        int second = time.get(Calendar.SECOND);
        int minute = time.get(Calendar.MINUTE);
        int hour = time.get(Calendar.HOUR_OF_DAY);
        int day = time.get(Calendar.DAY_OF_MONTH);
        int month = time.get(Calendar.MONTH) + 1;
        int year = time.get(Calendar.YEAR);
        if (format != 14) {
            if (year >= 2000)
                year = year - 2000;
            else
                year = year - 1900;
        }
        if (format >= 2) {
            if (format == 14)
                cTime.append(year);
            else
                cTime.append(getFormatTime(year, 2));
        }
        if (format >= 4)
            cTime.append(getFormatTime(month, 2));
        if (format >= 6)
            cTime.append(getFormatTime(day, 2));
        if (format >= 8)
            cTime.append(getFormatTime(hour, 2));
        if (format >= 10)
            cTime.append(getFormatTime(minute, 2));
        if (format >= 12)
            cTime.append(getFormatTime(second, 2));
        if (format >= 15)
            cTime.append(getFormatTime(miltime, 3));
        return cTime.toString();
    }
    
    /**获得现在的时间的后几分钟的时间*/
    public static Date  overMinute(String datetime,long minute)
    {
        Date date=toDate(datetime);
        date.setTime(date.getTime()+ minute*1000*60);
        return date;        
    }
    /**获得现在的时间的后几个小时的时间*/
    public static Date  overHour(String datetime,long hour)
    {
        Date date=toDate(datetime);
        date.setTime(date.getTime()+ hour*1000*60*60);
        return date;
    }
    /**获得现在的时间的后几天的时间*/
    public static Date  overDay(String datetime,long day)
    {
        Date date=toDate(datetime);
        date.setTime(date.getTime()+ day*1000*60*60*24);
        return date; 
    }
    
    /**
     * 获取当前时间加上若干分钟后的时间
     * @param hours
     * @return
     */
    public static String getTimeAddMinute(int mins){
        Calendar cal=Calendar.getInstance();    
        cal.add(Calendar.MINUTE, mins);
        return formatter1.format(cal.getTime());
    } 
    
    /**
     * 获取当前时间加上若干小时后的时间
     * @param hours
     * @return
     */
    public static String getTimeAddHour(int hours){
        Calendar cal=Calendar.getInstance();    
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return formatter1.format(cal.getTime());
    }
    
    /**
     * 获取当前时间加上若干天后的时间
     * @param hours
     * @return
     */
    public static String getTimeAddDay(int days){
        Calendar cal=Calendar.getInstance();    
        cal.add(Calendar.DAY_OF_YEAR, days);
        return formatter1.format(cal.getTime());
    }
    
    /**
     * 获取当前时间加上若干年后的时间
     * @param hours
     * @return
     */
    public static String getTimeAddYear(int years){
        Calendar cal=Calendar.getInstance();    
        cal.add(Calendar.YEAR, years);
        return formatter1.format(cal.getTime());
    }
    
    public static java.util.Date toDate(String dateTime)
    {
        int index = dateTime.indexOf(" ");
        String date = dateTime.substring(0, index);
        String time = dateTime.substring(index + 1);

        return toDate(date, time);
    }

    /**
     * 字符串转为时间,字符串符合标准日期格式:"YYYY-MM-DD",和标准时间格式:"HH:MM:SS"
     * 
     * @param date 标准日期格式 "YYYY-MM-DD"
     * @param time 标准时间格式 "HH:MM:SS"
     * @return java.util.Date
     */
    public static java.util.Date toDate(String date, String time)
    {
        if (date == null || time == null)
            return null;

        int dateSlash1 = date.indexOf("-");
        int dateSlash2 = date.lastIndexOf("-");

        if (dateSlash1 <= 0 || dateSlash1 == dateSlash2)
            return null;
        
        int timeColon1 = time.indexOf(":");
        int timeColon2 = time.lastIndexOf(":");

        if (timeColon1 <= 0 || timeColon1 ==timeColon2)
            return null;
        
        String year = date.substring(0, dateSlash1);
        String month = date.substring(dateSlash1 + 1, dateSlash2);
        String day = date.substring(dateSlash2 + 1);
        
        String hour = time.substring(0, timeColon1);
        String minute = time.substring(timeColon1 + 1, timeColon2);
        String second = time.substring(timeColon2 + 1);;

        return toDate(year, month, day, hour, minute, second);
    }

    public static java.util.Date toDate(String yearStr,String monthStr, String dayStr, 
        String hourStr, String minuteStr, String secondStr)
    {
        int year, month, day, hour, minute, second;

        try
        {
            year = Integer.parseInt(yearStr);
            month = Integer.parseInt(monthStr);
            day = Integer.parseInt(dayStr);
            hour = Integer.parseInt(hourStr);
            minute = Integer.parseInt(minuteStr);
            second = Integer.parseInt(secondStr);
        }
        catch (Exception e)
        {
            return null;
        }
        return toDate(year, month, day, hour, minute, second);
    }

    /**
     * 通过标准时间输入,年,月,日,时,分,秒,生成java.util.Date
     * 
     * @param year 年
     * @param month 月
     * @param day 日
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @return java.util.Date
     */
    public static java.util.Date toDate(int year, int month, int day,int hour, int minute, int second)
    {
        Calendar calendar = Calendar.getInstance();

        try
        {
            calendar.set(year, month - 1, day, hour, minute, second);
        }
        catch (Exception e)
        {
            return null;
        }
        return calendar.getTime();
    }

    /**
     * 获取当前时间的 String 类型
     * @return 当前时间的 String 类型
     */
    public static String getCurrentTimeString() throws Exception {
        try {
            return CDateTime.getTimeString("yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取当前时间的 Timestamp 类型
     * @return 当前时间的 Timestamp 类型
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(new java.util.Date().getTime());
    }
    
    /**
     * 取当前年份
     * @return
     */
    public static String getCurrentYear(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    /**
     * 取当前月份，小于10的双位数
     * @return
     */
    public static String getCurrentMonth(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int i = calendar.get(Calendar.MONTH) + 1;
        String result = "";
        if (i < 10) {
            result = "0" + String.valueOf(i);
        } else {
            result = String.valueOf(i);
        }
        return result;
    }
    
    /**
     * 取当前月份,小于10的单位数
     * @return
     */
    public static String getCurrentMonthSingle(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int i = calendar.get(Calendar.MONTH) + 1;
        return String.valueOf(i);
    }

    /**
     * 取当前天数,小于10的双位数
     * @return
     */
    public static String getCurrentDay(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int i = calendar.get(Calendar.DAY_OF_MONTH);
        String result = "";
        if (i < 10) {
            result = "0" + String.valueOf(i);
        } else {
            result = String.valueOf(i);
        }
        return result;
    }
    
    /**
     * 取当前天数,小于10的单位数
     * @return
     */
    public static String getCurrentDaySingle(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int i = calendar.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(i);
    }

    /**
     * 取当前小时,小于10的双位数
     * @return
     */
    public static String getCurrentHour(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int i = calendar.get(Calendar.HOUR_OF_DAY);
        String result = "";
        if (i < 10) {
            result = "0" + String.valueOf(i);
        } else {
            result = String.valueOf(i);
        }
        return result;
    }
    
    /**
     * 取当前小时,小于10的单位数
     * @return
     */
    public static String getCurrentHourSingle(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int i = calendar.get(Calendar.HOUR_OF_DAY);
        return String.valueOf(i);
    }
    
    /**
     * 取当前分,小于10的双位数
     * @return
     */
    public static String getCurrentMin(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int i = calendar.get(Calendar.MINUTE);
        String result = "";
        if (i < 10) {
            result = "0" + String.valueOf(i);
        } else {
            result = String.valueOf(i);
        }
        return result;
    }
    
    /**
     * 取当前分,小于10的单位数
     * @return
     */
    public static String getCurrentMinSingle(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int i = calendar.get(Calendar.MINUTE);
        return String.valueOf(i);
    }

    /**
     * 取当前秒,小于10的双位数
     * @return
     */
    public static String getCurrentSecon(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int i = calendar.get(Calendar.SECOND);
        String result = "";
        if (i < 10) {
            result = "0" + String.valueOf(i);
        } else {
            result = String.valueOf(i);
        }
        return result;
    }
    
    /**
     * 取当前秒,小于10的单位数
     * @return
     */
    public static String getCurrentSeconSingle(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int i = calendar.get(Calendar.SECOND);
        return String.valueOf(i);
    }
    
    /**
     * 用日历获取当前时间间隔millis毫秒后的时间
     * @param millis    间隔毫秒数
     */
    public static String getNextStarttime(long millis)
    {
        long currentMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentMillis + millis);
        StringBuilder builder = new StringBuilder();
        builder.append(calendar.get(Calendar.SECOND)).append(" ")
               .append(calendar.get(Calendar.MINUTE)).append(" ")
               .append(calendar.get(Calendar.HOUR_OF_DAY)).append(" ")
               .append(calendar.get(Calendar.DAY_OF_MONTH)).append(" ")
               .append(calendar.get(Calendar.MONTH) + 1).append(" ")
               .append("? ")
               .append(calendar.get(Calendar.YEAR));
        return builder.toString();  
    }
    
    /** 返回当前时间的Date */
    public static Date nowDate()
    {
        return new Date();
    }
    
    /**
     * 将 timestamp 类型转换为日期字符串
     * 
     * @param pTimestamp    输入 timestamp
     * @return 日期字符串
     * @throws java.lang.Exception
     */
    public static String convertTimestampToString(Timestamp pTimestamp)
            throws Exception {
        if (pTimestamp == null) {
            return "";
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.format(pTimestamp);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 将 timestamp 类型转换为日期字符串
     * 
     * @param pTimestamp    Timestamp
     * @param pFormat   String
     * @return String
     * @throws Exception
     */
    public static String convertTimestampToString(Timestamp pTimestamp, String pFormat)
            throws Exception {
        if (pTimestamp == null) {
            return "";
        }

        SimpleDateFormat format = new SimpleDateFormat(pFormat);
        try {
            return format.format(pTimestamp);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * 转换日期字符串至 Timestamp 类型
     * 
     * @param strDate   日期字符串
     * @return Timestamp
     * @throws java.lang.Exception
     */
    public static Timestamp convertStringToTimestamp(String strDate)
            throws Exception {

        if (strDate == null || strDate.equals("")) {
            return null;
        }
        Timestamp t = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            t = new java.sql.Timestamp(format.parse(strDate).getTime());
        } catch (Exception e) {
            throw e;
        }
        return t;
    }
    
    /**
     * 将 timestamp 类型转换为日期字符串
     * 
     * @param strDate   String 输入 timestamp
     * @param sFormat   String 输入 格式
     * @return Timestamp
     * @throws Exception
     */
    public static Timestamp convertStringToTimestamp(String strDate, String sFormat)
            throws Exception {

        if (strDate == null || strDate.equals("")) {
            return null;
        }
        Timestamp t = null;
        SimpleDateFormat format = new SimpleDateFormat(sFormat);

        try {
            t = new java.sql.Timestamp(format.parse(strDate).getTime());
        } catch (Exception e) {
            throw e;
        }
        return t;
    }
    
    /**
     * 将timestamp转换为date（'yyyyMMdd'格式）
     * @param timestamp
     * @throws Exception
     */
    public static java.util.Date convertTimestampToDate(java.sql.Timestamp timestamp)
            throws Exception {
        String tmp = convertTimestampToString(timestamp, "yyyyMMdd");
        java.util.Date ret = convertStringToDate(tmp, "yyyyMMdd");
        return ret;
    }
    
    /**
     * 将timestamp转换为date（自己需要的格式）
     * @param timestamp
     * @throws Exception
     */
    public static java.util.Date convertTimestampToDate(java.sql.Timestamp timestamp,String sFormat)
            throws Exception {
        String tmp = convertTimestampToString(timestamp, sFormat);
        java.util.Date ret = convertStringToDate(tmp, sFormat);
        return ret;
    }
    
    /**
     * 将date转换为timestamp（'yyyyMMdd'格式）
     * @param timestamp
     * @throws Exception
     */
    public static java.sql.Timestamp convertDateToTimestamp(java.util.Date date)
            throws Exception {
        if (date == null)
            return null;

        String tmp = convertDateToString(date, "yyyyMMdd");
        java.sql.Timestamp ret = convertStringToTimestamp(tmp, "yyyyMMdd");
        return ret;
    }
    
    /**
     * 将date转换为timestamp（自己需要的格式）
     * @param timestamp
     * @throws Exception
     */
    public static java.sql.Timestamp convertDateToTimestamp(java.util.Date date,String sFormat)
            throws Exception {
        if (date == null)
            return null;

        String tmp = convertDateToString(date, sFormat);
        java.sql.Timestamp ret = convertStringToTimestamp(tmp, sFormat);
        return ret;
    }
    
    /**
     * 时间格式的转换
     * 
     * @param pDate 日期
     * @param psFormat  格式
     * @return 转换后的时间字符串
     */
    public static String convertDateToString(java.util.Date pDate, String psFormat) {
        return format(pDate, psFormat);
    }

    /**
     * 字符串转换为时间
     * 
     * @param psDateStr 日期
     * @param psFormat  格式
     * @return 转换后的时间字符串
     */
    public static java.util.Date convertStringToDate(String psDateStr, String psFormat) {
        SimpleDateFormat oFormat = new SimpleDateFormat(psFormat);
        ParsePosition pos = new ParsePosition(0);
        java.util.Date d = oFormat.parse(psDateStr, pos);
        return d;
    }

    /**
     * 字符串转换为SQL时间
     * 
     * @param psDateStr 日期
     * @param psFormat  格式
     * @return 转换后的时间字符串
     */
    public static java.sql.Date convertStringToSQLDate(String psDateStr, String psFormat) {
        return new java.sql.Date(convertStringToDate(psDateStr, psFormat).getTime());
    }

    /**
     * 将timestamp中的时、分、秒部分剔除，并返回该timestamp值
     * @param timestamp
     * @throws Exception
     */
    public static java.sql.Timestamp truncTimestampToDay(java.sql.Timestamp timestamp)
            throws Exception {
        String tmp = convertTimestampToString(timestamp, "yyyyMMdd");
        java.sql.Timestamp ret = convertStringToTimestamp(tmp, "yyyyMMdd");
        return ret;
    }

    /**
     * 判断timestamp是否是今天的
     * @param timestamp
     * @throws Exception
     */
    public static boolean isToday(Timestamp timestamp) throws Exception {
        String day1 = convertTimestampToString(timestamp, "yyyyMMdd");
        Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
        String day2 = convertTimestampToString(currTimestamp, "yyyyMMdd");
        return day2.equalsIgnoreCase(day1);
    }

    /**
     * 判断是否润年
     * @param piYear
     * @return
     */
    public static boolean isLeapYear(int piYear) {
        boolean b = ((piYear % 4) == 0) && ((piYear % 100) != 0) || ((piYear % 400) == 0);
        return (b);
    }
    
    /** 检查是否是正确的日期 */
    public static boolean isDate(String date)
    {
        if (isEmpty(date))
            return DEFAULT_EMPTY_OK;

        int dateSlash1 = date.indexOf("-");
        int dateSlash2 = date.lastIndexOf("-");

        if (dateSlash1 <= 0 || dateSlash1 == dateSlash2)
            return false;

        String year = date.substring(0, dateSlash1);
        String month = date.substring(dateSlash1 + 1, dateSlash2);
        String day = date.substring(dateSlash2 + 1);

        return isDate(year, month, day);
    }

    /** 检查是否是正确的日期 */
    public static boolean isDate(String year, String month, String day)
    {
        if (!(isYear(year) && isMonth(month) && isDay(day)))
            return false;

        int intYear = Integer.parseInt(year);
        int intMonth = Integer.parseInt(month);
        int intDay = Integer.parseInt(day);

        if (intDay > DAYS_IN_MONTH[intMonth - 1])
            return false;

        if ((intMonth == 2) && (intDay > (isLeapYear(intYear) ? 29 : 28)))
            return false;

        return true;
    }

    /** 检查是否是正确的年 */
    public static boolean isYear(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        if (!isNonnegativeInteger(s))
            return false;

        return ((s.length() == 2) || (s.length() == 4));
    }

    /** 检查是否是正确的月 */
    public static boolean isMonth(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        return isIntegerInRange(s, 1, 12);
    }

    /** 检查是否是正确的日 */
    public static boolean isDay(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        return isIntegerInRange(s, 1, 31);
    }

    /** 检查字符串是否为空 */
    public static boolean isEmpty(String s)
    {
        return ((s == null) || (s.trim().length() == 0));
    }

    /** 检查是否是整型 */
    public static boolean isInteger(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        // 逐个检查,如果出现一个字符不是数字则返回false
        for (int i = 0; i < s.length(); i++)
        {
            if (!isDigit(s.charAt(i)))
                return false;
        }

        return true;
    }

    /** 检查字符是否是数字 */
    public static boolean isDigit(char c)
    {
        return Character.isDigit(c);
    }

    /** 检查是否是一个非负整数 */
    public static boolean isNonnegativeInteger(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        try
        {
            int temp = Integer.parseInt(s);

            if (temp >= 0)
                return true;
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /** 检查字符串是否是整型,且在a,b之间,>=a,<=b */
    public static boolean isIntegerInRange(String s, int a, int b)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        if (!isSignedInteger(s))
            return false;

        int num = Integer.parseInt(s);

        return ((num >= a) && (num <= b));
    }

    /** 检查是否是带符号的整型(允许第一个字符为"+,-",不接受浮点".",指数"E"等 */
    public static boolean isSignedInteger(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;

        try
        {
            Integer.parseInt(s);

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    /** 检查是否是正确的时间 */
    public static boolean isTime(String time)
    {
        if (isEmpty(time))
            return DEFAULT_EMPTY_OK;

        int timeColon1 = time.indexOf(":");
        int timeColon2 = time.lastIndexOf(":");

        if (timeColon1 <= 0 || timeColon1 == timeColon2)
            return false;
        
        String hour = time.substring(0, timeColon1);
        String minute = time.substring(timeColon1 + 1, timeColon2);
        String second = time.substring(timeColon2 + 1);
        
        return isTime(hour, minute, second);
    }
    /** 检查是否是正确的时间 */
    public static boolean isTime(String hour, String minute, String second)
    {
        if (isHour(hour) && isMinute(minute) && isSecond(second))
            return true;

        return false;
    }
    /** 检查是否是正确的时 */
    public static boolean isHour(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;
        
        return isIntegerInRange(s, 0, 23);
    }

    /** 检查是否是正确的分 */
    public static boolean isMinute(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;
        return isIntegerInRange(s, 0, 59);
    }

    /** 检查是否是正确的秒 */
    public static boolean isSecond(String s)
    {
        if (isEmpty(s))
            return DEFAULT_EMPTY_OK;
        return isIntegerInRange(s, 0, 59);
    }

    /**
     * 获得两个时间的间隔天数
     * @param pDate1
     * @param pDate2
     * @return
     */
    public static int getInvDays(java.util.Date pDate1, java.util.Date pDate2) {
        int sDays = 0;
        SimpleDateFormat format = new SimpleDateFormat("D");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy"); // 当前年
        int year1 = Integer.valueOf(yearFormat.format(pDate1)).intValue();
        int year2 = Integer.valueOf(yearFormat.format(pDate2)).intValue();
        int day1 = Integer.valueOf(format.format(pDate1)).intValue();
        int day2 = Integer.valueOf(format.format(pDate2)).intValue();
        int Invcount = 0;
        // 判断是否跨越年限
        if ((year2 - year1) > 0) {
            int LeapYearCount = getCount(year1, year2);
            Invcount = (year2 - year1) * 365 + LeapYearCount;
        }
        sDays = day2 - day1 + Invcount;
        return sDays;
    }

    /**
     * 获得两个时间的间隔分钟数（前面的Date减后面的Date） 如果后面的Date晚于前面的Date，则返回负值
     * @param pDate1
     * @param pDate2
     * @return
     */
    public static long getInvMinute(java.util.Date pDate1, java.util.Date pDate2) {
        long ret = (pDate1.getTime() - pDate2.getTime()) / 1000 / 60;
        return ret;
    }

    /**
     * 计算指定Date是周几。返回值含义：SUNDAY = 1;MONDAY = 2;TUESDAY = 3;WEDNESDAY = 4;THURSDAY =
     * 5;FRIDAY = 6;SATURDAY = 7;
     * @param date
     * @return 范围为1~7。
     */
    public static int getDayOfWeek(java.util.Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        int dayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK);
        return dayOfWeek;
    }

    /**
     * 获取指定时间所在周的第一天（注意:美国人的习惯，把周日做为第一天）的Date。
     * @param date
     * @return
     */
    public static java.util.Date getFirstDayOfWeek(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        java.util.Date ret = new Date(cal.getTimeInMillis());
        return ret;
    }

    /**
     * 获取两个年限直接有多少个闰年
     * @param year1
     * @param year2
     * @return
     */
    public static int getCount(int year1, int year2) {
        int rncount = 0;
        for (int i = year1; i < year2; i++) {
            if (isLeapYear(i)) {
                rncount++;
            }
        }
        return rncount;
    }

    /**
     * 返回时间段自然日数目的函数，不计入第一天
     * 
     * @param fromDate  开始日期
     * @param toDate    截至日期
     * @throws Exception
     */
    public static int getDayNum(Timestamp fromDate, Timestamp toDate)
            throws Exception {
        //    java.sql.Date fromD = CDateTime.convertTimestamp2Date(fromDate);
        //    java.sql.Date toD = CDateTime.convertTimestamp2Date(toDate);
        int result = CDateTime.getInvDays(fromDate, toDate);

        return result;
    }
    
    /**
     * 取得当前NUM天后的日期时间字符串
     * 
     * @param format 格式,如String format = "yyyy-MM-dd HH:mm:ss";
     * @param num    天数
     * @return String 取得当前NUM天后的日期时间字符串
     */
    public static String getNextDateTimeString(String format,int num)
    {
        Calendar c=Calendar.getInstance();
        c.add(Calendar.DATE, num);
        return convertDateToString(c.getTime(), format);
    }
    
    /**
     * 获取和当前时间毫秒差值
     * 
     * @param dateTime YYYY-MM-DD hh:mm:ss
     * @return 毫秒差
     */
    public static long getTimeMargin(String dateTime)
    {
        int index = dateTime.indexOf(" ");
        String date = dateTime.substring(0, index);
        String time = dateTime.substring(index + 1);
        
        int dateSlash1 = date.indexOf("-");
        int dateSlash2 = date.lastIndexOf("-");

        if (dateSlash1 <= 0 || dateSlash1 == dateSlash2)
            return -1;
        
        int timeColon1 = time.indexOf(":");
        int timeColon2 = time.lastIndexOf(":");

        if (timeColon1 <= 0 || timeColon1 ==timeColon2)
            return -1;

        Calendar calendar = Calendar.getInstance();
        
        try
        {
            int year = Integer.parseInt(date.substring(0, dateSlash1));
            int month = Integer.parseInt(date.substring(dateSlash1 + 1, dateSlash2));
            int day = Integer.parseInt(date.substring(dateSlash2 + 1));
            
            int hour = Integer.parseInt(time.substring(0, timeColon1));
            int minute = Integer.parseInt(time.substring(timeColon1 + 1, timeColon2));
            int second = Integer.parseInt(time.substring(timeColon2 + 1));
            
            calendar.set(year, month - 1, day, hour, minute, second);
        }
        catch (Exception e)
        {
            return -1;
        }
        
        return System.currentTimeMillis() - calendar.getTimeInMillis();
    }
 
    /**
     * 获取两个日期对象相差天数
     * 
     * @param date1 日期对象
     * @param date2 日期对象
     * @return int 日差值
     */
    public static int compareDay(java.util.Date date1, java.util.Date date2)
    {
        if (date1 == null || date2 == null)
            return 0;

        long time1 = date1.getTime();
        long time2 = date2.getTime();

        long margin = time1 - time2;

        /* 转化成天数 */
        int ret = (int)Math.floor((double)margin / (1000 * 60 * 60 * 24));

        return ret;
    }
    /**
     * 获取两个日期对象相差小时数
     * 
     * @param date1 日期对象
     * @param date2 日期对象
     * @return int 日差值
     */
    public static int compareHour(java.util.Date date1, java.util.Date date2)
    {
        if (date1 == null || date2 == null)
            return 0;

        long time1 = date1.getTime();
        long time2 = date2.getTime();

        long margin = time1 - time2;

        int ret = (int)Math.floor((double)margin / (1000 * 60 * 60 ));

        return ret;
    }
    
    /**
     * 获取当前unix时间戳
     */
    public static String getUnixDate(Date dt)
    {
	try {
	    Timestamp appointTime=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt));
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = df.parse(String.valueOf(appointTime));
	    long s=date.getTime();
	    return String.valueOf(s).substring(0, 10);	
	} catch (Exception e) {
	}
	return "";
    }
    
    
    /**
	 * 得到当前日期/时间字符串
	 * 
	 * @return 返回日期/时间字符串
	 */
	public static String getNowDateTimeString(String type) {
		Date date = new Date();
		SimpleDateFormat formattxt = new SimpleDateFormat(type);
		return formattxt.format(date);
	}
    
//  public static java.sql.Timestamp getDbSysdate() throws Exception {
//  Connection mConn = null;
//  Statement mStmt = null;
//  ResultSet rs = null;
//  try {
//      mConn = CDBConnectionFactory.getConnection();
//      String mSql = "SELECT SYSDATE FROM dual";
//      mStmt = mConn.createStatement();
//      rs = mStmt.executeQuery(mSql);
//      if (rs.next()) {
//          return rs.getTimestamp(1);
//      } else {
//          throw new Exception("未能获取数据库时间。");
//      }
//  } catch (Exception e) {
//      throw e;
//  } finally {
//      CDBConnectionFactory.closeDB(rs, mStmt, mConn);
//  }
//}

//public static java.sql.Date getDbSysdate2() throws Exception {
//  Connection mConn = null;
//  Statement mStmt = null;
//  ResultSet rs = null;
//  CDBConnectionFactory connF=new CDBConnectionFactory();
//  try {
//      mConn = CDBConnectionFactory.getConnection();
//      String mSql = "SELECT SYSDATE FROM dual";
//      mStmt = mConn.createStatement();
//      rs = mStmt.executeQuery(mSql);
//      if (rs.next()) {
//          return rs.getDate(1);
//      } else {
//          throw new Exception("未能获取数据库时间。");
//      }
//  } catch (Exception e) {
//      throw e;
//  } finally {
//      CDBConnectionFactory.closeDB(rs, mStmt, mConn);
//  }
//}


//  /**
//   * 日期加减函数，要区分工作日与自然日(自然日类型不考虑返回的日期是否处在节假日的问题)
//   * 
//   * @param pInDate
//   *            输入日期
//   * @param pDateType
//   *            类型。G：工作日 ， Z ：自然日
//   * @param pStep
//   *            加上的天数，为负值表示减
//   * @return
//   * @throws Exception
//   */
//  public static Timestamp getLimitDate(Timestamp pInDate, String pDateType, int pStep)
//          throws Exception {
//      return getLimitDate(null, pInDate, pDateType, pStep);
//  }

//  /**
//   * 日期加减函数，要区分工作日与自然日(自然日类型不考虑返回的日期是否处在节假日的问题)
//   * 
//   * @param pInDate
//   *            输入日期
//   * @param pDateType
//   *            类型。G：工作日 ， Z ：自然日
//   * @param pStep
//   *            加上的天数，为负值表示减
//   * @return
//   * @throws Exception
//   */
//  public static Timestamp getLimitDate(java.util.Date pInDate, String pDateType, int pStep)
//          throws Exception {
//      Timestamp tmp = convertDate2Timestamp(pInDate);
//      return getLimitDate(null, tmp, pDateType, pStep);
//  }

//  /**
//   * 日期加减函数，要区分工作日与自然日(自然日类型不考虑返回的日期是否处在节假日的问题)
//   * 此方法可以绕过CDBManager来获取数据库连接，可用于JUnit测试，
//   * 
//   * @param pInDate
//   *            输入日期
//   * @param pDateType
//   *            类型。G：工作日 ， Z ：自然日
//   * @param pStep
//   *            加上的天数，为负值表示减
//   * @return
//   * @throws Exception
//   */
//  public static Timestamp getLimitDate(Connection pConn, Timestamp pInDate, String pDateType, int pStep)
//          throws Exception {
//      Timestamp result = null;
//      Connection mConn = null;
//      PreparedStatement ps = null;
//      ResultSet rs = null;
//      try {
//          if (pStep == 0) {// 加减0天，直接返回
//              result = pInDate;
//          } else if ("Z".equalsIgnoreCase(pDateType)) { // 自然日
//              java.util.Date oldDate = CDateTime.convertTimestamp2Date(pInDate);
//
//              Calendar cal = Calendar.getInstance();
//              cal.setTime(oldDate);
//              cal.add(Calendar.DAY_OF_MONTH, pStep);
//
//              String tmp = CDateTime.dateToStr(cal.getTime(), "yyyyMMdd");
//              result = CDateTime.convertStringToTimestamp(tmp, "yyyyMMdd");
//          } else if (pStep > 0) {
//              String query = "SELECT max(to_date(b.YEAR||b.MONTH||b.DAY, 'yyyymmdd')) " + " from (SELECT a.YEAR,a.MONTH,a.DAY,a.HOLIDAY FROM T_Sys_Holiday a " + "       WHERE to_date(a.YEAR||a.MONTH||a.DAY, 'yyyymmdd') > trunc(?,'dd') " + "        AND a.HOLIDAY = '0' " + "       ORDER BY to_date(a.YEAR||a.MONTH||a.DAY, 'yyyymmdd')) b " + "WHERE ROWNUM <= ?";
//
//              if (pConn != null) {
//                  mConn = pConn;
//              } else {
//                  mConn = CDBConnectionFactory.getConnection();
//              }
//
//              ps = mConn.prepareStatement(query);
//              ps.setTimestamp(1, pInDate);
//              ps.setInt(2, pStep);
//              rs = ps.executeQuery();
//              if (rs.next()) {
//                  result = rs.getTimestamp(1);
//              }
//          } else if (pStep < 0) {
//              String query = "SELECT MIN(to_date(a.YEAR||a.MONTH||a.DAY, 'yyyymmdd')) " + "  from (SELECT * " + "          FROM T_Sys_Holiday a " + "         WHERE to_date(a.YEAR||a.MONTH||a.DAY, 'yyyymmdd') < trunc(?,'dd') " + "           AND a.HOLIDAY = '0' " + "         ORDER BY to_date(a.YEAR||a.MONTH||a.DAY, 'yyyymmdd') DESC) " + " WHERE ROWNUM <= (-?)";
//
//              if (pConn != null) {
//                  mConn = pConn;
//              } else {
//                  mConn = CDBConnectionFactory.getConnection();
//              }
//              ps = mConn.prepareStatement(query);
//              ps.setTimestamp(1, pInDate);
//              ps.setInt(2, pStep);
//              rs = ps.executeQuery();
//              if (rs.next()) {
//                  result = rs.getTimestamp(1);
//              }
//          }
//      } catch (Exception e) {
//          e.printStackTrace();
//      } finally {
//          if (pConn != null) {
//              CDBConnectionFactory.closeDB(rs, ps, null);
//          } else {
//              CDBConnectionFactory.closeDB(rs, ps, mConn);
//          }
//      }
//      return result;
//  }
//    
//    public static String getPageSql(String sql, int currentPage, int pageSize)
//    {
//        StringBuffer bf = new StringBuffer();
//        int start = pageSize * (currentPage - 1);
//        int end = pageSize * currentPage;
//        bf.append("SELECT * FROM (SELECT  A. * , ROWNUM RN FROM (");
//        bf.append(sql);
//        bf.append(") A WHERE ROWNUM <= " + end + ") WHERE RN > " + start);
//        return bf.toString();
//    }
//
//    public static String getRowCountSql(String sql)
//    {
//        StringBuffer bf = new StringBuffer();
//        bf.append("select count(*) from (");
//        bf.append(sql);
//        bf.append(")");
//        return bf.toString();
//    }

    public static void main(String[] args) {
        //CDateTime CDateTime1 = new CDateTime();
        
        System.out.println(getNextStarttime(30000));
    }

    private java.util.Date mDate;

    private Calendar mCalendar;

}
