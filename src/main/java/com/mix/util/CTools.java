/*
 * 版权所有 (C) 2001-2012 深圳市有信网络科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *      1、2012-7-29，JiangQian创建。 
 */
package com.mix.util;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicInteger;

public class CTools {

	public CTools() {
	}

	private static AtomicInteger seq = new AtomicInteger(0);

	public static int UTIL_IFILENAMECOUNT = 1000;

	public static int UTIL_IJHIDCOUNT = 1000;
	public static int UTIL_IJHIDCOUNT_19 = 10000;
	public static int UTIL_IJHIDCOUNT_20 = 100000;

	public static int UTIL_IIDCOUNT = 1000;
	public static int UTIL_IIDCOUNT_19 = 10000;
	public static int UTIL_IIDCOUNT_20 = 100000;

	public static Object UTIL_OBJ = new Object();

	public static Object UTIL_OJHID = new Object();

	/**
	 * 阻止通过SQLInject注入登陆系统。通常用于对外接口，如WebService等。 注：目前只是简单替换『' " & =
	 * 』等特殊符号,下一步需要添加对数据库函数的处理。
	 * 
	 * @param in
	 *            原用户名或密码值
	 * @return 经过防SQLInject处理过的用户名或密码
	 */
	private static long UTIL_IID16COUNT = 10;

	public static String antiSQLInjectLogin(String in) {
		String out = in;
		if (out != null) {
			out = out.replaceAll("'", "");
			out = out.replaceAll("\"", "");
			out = out.replaceAll("&", "");
			out = out.replaceAll("=", "");
			// TODO 添加对数据库函数的处理
		}
		return out;
	}

	/**
	 * 从 byteBuffer 中获取字符串
	 * 
	 * @param byteBuffer
	 *            从 channel 中得到的 byteBuffer
	 * @return 字符串
	 */
	public static String bufferToString(java.nio.ByteBuffer byteBuffer) {
		int intLimit = byteBuffer.limit();
		byte[] bytes = new byte[intLimit];
		System.arraycopy(byteBuffer.array(), 0, bytes, 0, byteBuffer.limit());
		return new String(bytes);
	}

	/**
	 * 向 byteBuffer 中写入信息
	 * 
	 * @param byteBuffer
	 *            从 channel 中得到的 byteBuffer
	 * @param message
	 *            要写入的信息
	 */
	public static void addMsgToBuffer(java.nio.ByteBuffer byteBuffer,
			String message) {
		byteBuffer.clear();
		byteBuffer.put(message.getBytes());
		byteBuffer.flip();
	}

	/**
	 * 按照命名规则生成发送方的文件名，发送用
	 * 
	 * @param sStartDept
	 *            开始单位，既是源单位
	 * @param sEndDept
	 *            结束单位，既是目标单位
	 * @return 文件名
	 * @throws java.lang.Exception
	 */
	public static String createFileName(String sStartDept, String sEndDept)
			throws Exception {
		synchronized (UTIL_OBJ) {
			if (UTIL_IFILENAMECOUNT >= 9999) {
				UTIL_IFILENAMECOUNT = 1000;
			}
			StringBuffer sb = new StringBuffer(24);
			sb.append(sEndDept);
			sb.append(sStartDept);
			sb.append(CDateTime.getTimeString("MMddHHmmss"));
			sb.append(UTIL_IFILENAMECOUNT++);
			sb.append(".xml");
			return sb.toString();
		}
	}

	/**
	 * 产生一个 21 位长的文件名，接收用
	 * 
	 * @return 文件名
	 */
	public static String createFileName() {
		synchronized (UTIL_OBJ) {
			if (UTIL_IFILENAMECOUNT >= 9999) {
				UTIL_IFILENAMECOUNT = 1000;
			}
			StringBuffer sb = new StringBuffer(24);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			sb.append("R"); // Receive file
			sb.append(format.format(new java.util.Date()));
			sb.append(UTIL_IFILENAMECOUNT++);
			sb.append(".xml");
			return sb.toString();
		}
	}

	/**
	 * 产生一个 19 位长的 JHID，第 20、21 位由 sp_createIterateWork 内产生
	 * 
	 * @return JHID
	 */
	public static String createJHID() {
		synchronized (UTIL_OJHID) {
			if (UTIL_IJHIDCOUNT >= 9999) {
				UTIL_IJHIDCOUNT = 1000;
			}
			StringBuffer sb = new StringBuffer(19);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			sb.append("9");
			sb.append(format.format(new java.util.Date()));
			sb.append(UTIL_IJHIDCOUNT++);
			return sb.toString();
		}
	}

	/**
	 * 产生一个 20 位长的 JHID
	 * 
	 * @return JHID
	 */
	public static String createID20() {
		synchronized (UTIL_OJHID) {
			if (UTIL_IIDCOUNT_20 >= 999999) {
				UTIL_IIDCOUNT_20 = 100000;
			}
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			sb.append(format.format(new java.util.Date()));
			sb.append(UTIL_IIDCOUNT_20++);
			return sb.toString();
		}
	}

	/**
	 * 产生一个 12 位长的 JHID
	 * 
	 * @return JHID
	 */
	public static String createID12() {
		synchronized (UTIL_OJHID) {
			if (UTIL_IIDCOUNT_20 >= 999999) {
				UTIL_IIDCOUNT_20 = 100000;
			}
			StringBuffer sb = new StringBuffer(12);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

			sb.append(format.format(new java.util.Date()));
			sb.append(UTIL_IIDCOUNT_20++);
			return sb.toString();
		}
	}

	/**
	 * 生成16位的ID
	 * 
	 * @return
	 */
	public static long createID16() {
		// TODO 这个方法可以抽取到CUtil类中
		if (UTIL_IID16COUNT >= 99) {
			UTIL_IID16COUNT = 10;
		}
		StringBuffer sb = new StringBuffer(20);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		sb.append(format.format(new java.util.Date()));
		sb.append(UTIL_IID16COUNT++);

		return Long.parseLong(sb.toString());

	}

	/**
	 * 得到数据库的类型
	 * 
	 * @param sType
	 *            数据库名称
	 * @return 数据库类型
	 */
	public static int getSqlTypes(String sType) {
		if (sType.equalsIgnoreCase("Date")
				|| sType.equalsIgnoreCase("DateTime")) {
			return java.sql.Types.DATE;
		}
		if (sType.equalsIgnoreCase("CHAR")) {
			return java.sql.Types.CHAR;
		}
		if (sType.equalsIgnoreCase("VARCHAR")) {
			return java.sql.Types.VARCHAR;
		}
		if (sType.equalsIgnoreCase("INTEGER")) {
			return java.sql.Types.INTEGER;
		}
		if (sType.equalsIgnoreCase("NUMERIC")) {
			return java.sql.Types.NUMERIC;
		}
		if (sType.equalsIgnoreCase("DECIMAL")) {
			return java.sql.Types.DECIMAL;
		}
		return -99; // 一个不存在的类型
	}

	/**
	 * 数组扩容
	 * 
	 * @param src
	 *            byte[] 源数组数据
	 * @param size
	 *            int 扩容的增加量
	 * @return byte[] 扩容后的数组
	 */
	public static byte[] grow(byte[] src, int size) {
		byte[] tmp = new byte[src.length + size];
		System.arraycopy(src, 0, tmp, 0, src.length);
		return tmp;
	}

	/**
	 * 数组扩容
	 * 
	 * @param src
	 *            char[] 源数组数据
	 * @param size
	 *            int 扩容的增加量
	 * @return char[] 扩容后的数组
	 */
	public static char[] grow(char[] src, int size) {
		char[] tmp = new char[src.length + size];
		System.arraycopy(src, 0, tmp, 0, src.length);
		return tmp;
	}

	/**
	 * 对输入字符串按指定字符集转码，其中有几种字符集不需要转码
	 * 
	 * @param str
	 *            需要进行转码的字符串
	 * @param sCharset
	 *            输出字符串使用的字符集
	 * @return 转码后的字符串
	 * @throws java.lang.Exception
	 */
	public static String strEncode(String str, String sCharset)
			throws Exception {
		/*
		 * JVM对字符集的处理： JVM核心完全使用Unicode字符集，编码上采用UTF-16LE(x86和Unix)。 Java编译器扫描.
		 * java源文件时将完成预转换，比如在中文Windows上编译.java文件时，你可能已经注意
		 * 到.java文件中的字符串和.class中的不一样。因为.java文件本身用的是gb2312编码，
		 * 而.class内则是UTF-16LE编码。如果你的编辑器支持，你可能会选择直接用UTF-8来书
		 * 写.java源程序，这时Java编译器就会用UTF-8对源程序解码。
		 */
		if (str == null) {
			return null;
		}

		// 如果是以下几种字符集，则不需要进行转码
		if (sCharset.equalsIgnoreCase("GB2312")
				|| sCharset.equalsIgnoreCase("GBK")
				|| sCharset.equalsIgnoreCase("UTF8")
				|| sCharset.equalsIgnoreCase("UTF16")) {
			return str;
		}

		String sTemp;
		try {
			sTemp = new String(str.getBytes(), sCharset);
		} catch (java.io.UnsupportedEncodingException uee) {
			throw uee;
		}

		return sTemp;
	}

	/**
	 * 对按指定字符集编码的输入字符串转码为 JAVA 默认字符集的字符串
	 * 
	 * @param str
	 *            采用指定字符集编码的字符串
	 * @param sCharset
	 *            指定的字符集
	 * @return JAVA 默认字符集的字符串
	 * @throws java.lang.Exception
	 */
	public static String strDecode(String str, String sCharset)
			throws Exception {
		if (str == null) {
			return null;
		}

		// 如果是以下几种字符集，则不需要进行转码
		if (sCharset.equalsIgnoreCase("GB2312")
				|| sCharset.equalsIgnoreCase("GBK")
				|| sCharset.equalsIgnoreCase("UTF8")
				|| sCharset.equalsIgnoreCase("UTF16")) {
			return str;
		}

		String sTemp;
		try {
			sTemp = new String(str.getBytes(sCharset));
		} catch (java.io.UnsupportedEncodingException uee) {
			throw uee;
		}
		return sTemp;
	}

	/**
	 * 将 XML 的保留符号（<、>、&、'、"）替换成对应的转义字符
	 * 
	 * @param str
	 *            输入字符串
	 * @return 转义后的字符串
	 */
	public static String toXMLStr(String str) {
		if (str == null) {
			return null;
		}

		if (str.length() < 1) {
			return str;
		}

		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("'", "&apos;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	/**
	 * 将 List 集合中的字符转换为 'value1','value2','value3'...，用于 SQL 查询
	 * 
	 * @param list
	 *            AbstractList
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String listToString(AbstractList list) {
		StringBuffer sReturn = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sReturn.append("'").append(String.valueOf(list.get(i)))
					.append("',");
		}
		if (sReturn.length() > 0) {
			return sReturn.substring(0, sReturn.length() - 1);
		} else {
			return "''";
		}
	}

	public static String arrayToString(String[] args) {
		StringBuffer sReturn = new StringBuffer();
		sReturn.append("(");
		for (int i = 0; i < args.length; i++) {
			sReturn.append("'").append(args[i]).append("',");
		}
		return sReturn.substring(0, sReturn.length() - 1) + ")";

	}

	@SuppressWarnings("unchecked")
	public static String arrayListToString(ArrayList args) {
		StringBuffer sReturn = new StringBuffer();
		sReturn.append("(");
		for (int i = 0; i < args.size(); i++) {
			sReturn.append("'").append(args.get(i)).append("',");
		}
		return sReturn.substring(0, sReturn.length() - 1) + ")";
	}

	/**
	 * 取输入的字节的 MD5 摘要
	 * 
	 * @param strBytes
	 *            byte[] 输入的字节
	 * @throws java.security.NoSuchAlgorithmException
	 * @return byte[] MD5 摘要
	 */
	public static byte[] getMD5Digest(byte[] strBytes)
			throws java.security.NoSuchAlgorithmException {
		java.security.MessageDigest mdTemp = java.security.MessageDigest
				.getInstance("MD5");
		mdTemp.update(strBytes);

		return mdTemp.digest();
	}

	/**
	 * 取输入字符串的 MD5 摘要（字符串形式）
	 * 
	 * @param str
	 *            String 输入字符串
	 * @throws NoSuchAlgorithmException
	 * @return String MD5 摘要（字符串形式），如果输入值为null或""，则返回""
	 */
	public static String getMD5Digest(String str)
			throws java.security.NoSuchAlgorithmException {
		if (str == null || "".equalsIgnoreCase(str)) {
			return "";
		}

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		byte[] md = getMD5Digest(str.getBytes());
		int j = md.length;
		char strOut[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			strOut[k++] = hexDigits[byte0 >>> 4 & 0xf];
			strOut[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(strOut);
	}

	/**
	 * 取输入字符串的 MD5 摘要（字符串形式）
	 * 
	 * @param str
	 *            String 输入字符串
	 * @throws NoSuchAlgorithmException
	 * @return String MD5 摘要（字符串形式），如果输入值为null或""，则返回""
	 */
	public static String getLowerMD5Digest(String str)
			throws java.security.NoSuchAlgorithmException {
		if (str == null || "".equalsIgnoreCase(str)) {
			return "";
		}

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		byte[] md = getMD5Digest(str.getBytes());
		int j = md.length;
		char strOut[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			strOut[k++] = hexDigits[byte0 >>> 4 & 0xf];
			strOut[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(strOut);
	}

	/**
	 * 生成 18 位的编号
	 * 
	 * @return String
	 */
	public static String getNewID() {
		synchronized (UTIL_OJHID) {
			if (UTIL_IJHIDCOUNT >= 9999) {
				UTIL_IJHIDCOUNT = 1000;
			}
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			sb.append(format.format(new java.util.Date()));
			sb.append(UTIL_IJHIDCOUNT++);
			return sb.toString();
		}
	}

	/**
	 * 生成 13 位的编号
	 * 
	 * @return String
	 */
	public static String getNewID_13() {
		synchronized (UTIL_OJHID) {
			if (UTIL_IJHIDCOUNT >= 9999) {
				UTIL_IJHIDCOUNT = 1000;
			}
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

			sb.append(format.format(new java.util.Date()));
			sb.append("-");
			sb.append(UTIL_IJHIDCOUNT++);
			return sb.toString();
		}
	}

	/**
	 * 生成 19 位的编号
	 * 
	 * @return String
	 */
	public static String getNewID_19() {
		synchronized (UTIL_OJHID) {
			if (UTIL_IJHIDCOUNT_19 >= 99999) {
				UTIL_IJHIDCOUNT_19 = 10000;
			}
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			sb.append(format.format(new java.util.Date()));
			sb.append(UTIL_IJHIDCOUNT_19++);
			return sb.toString();
		}
	}

	/**
	 * 生成 20 位的编号
	 * 
	 * @return String
	 */
	public static String getNewID_20() {
		synchronized (UTIL_OJHID) {
			if (UTIL_IJHIDCOUNT_20 >= 999999) {
				UTIL_IJHIDCOUNT_20 = 100000;
			}
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			sb.append(format.format(new java.util.Date()));
			sb.append(UTIL_IJHIDCOUNT_20++);
			return sb.toString();
		}
	}

	/**
	 * 生成 18 位的编号
	 * 
	 * @return String
	 */
	public static long getNewNumID() {
		synchronized (UTIL_OJHID) {
			if (UTIL_IIDCOUNT >= 9999) {
				UTIL_IIDCOUNT = 1000;
			}
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			sb.append(format.format(new java.util.Date()));
			sb.append(UTIL_IIDCOUNT++);
			return Long.parseLong(sb.toString());
		}
	}

	/**
	 * 生成 19 位的编号
	 * 
	 * @return String
	 */
	public static long getNewNumID_19() {
		synchronized (UTIL_OJHID) {
			if (UTIL_IIDCOUNT_19 >= 99999) {
				UTIL_IIDCOUNT_19 = 10000;
			}
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			sb.append(format.format(new java.util.Date()));
			sb.append(UTIL_IIDCOUNT_19++);
			return Long.parseLong(sb.toString());
		}
	}

	/**
	 * 生成 20 位的编号
	 * 
	 * @return String
	 */
	public static long getNewNumID_20() {
		synchronized (UTIL_OJHID) {
			if (UTIL_IIDCOUNT_20 >= 999999) {
				UTIL_IIDCOUNT_20 = 100000;
			}
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			sb.append(format.format(new java.util.Date()));
			sb.append(UTIL_IIDCOUNT_20++);
			return Long.parseLong(sb.toString());
		}
	}

	/**
	 * 生成 20 位的编号
	 * 
	 * @return String
	 */
	public static String getNewID_20_ByRandom() {
		DecimalFormat decfmt = new DecimalFormat("00000");
		double randKey_int = (double) Math.random() * 100000;// 6位随机码
		String randKey = decfmt.format(randKey_int);
		/** 假设并发是10个 */
		seq.compareAndSet(9, 0);

		StringBuffer sb = new StringBuffer(20);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		sb.append(format.format(new java.util.Date()));
		sb.append(randKey);
		sb.append(Integer.toString(seq.getAndAdd(1)));
		return sb.toString();
	}

	/**
	 * 生成4位随机数
	 */
	public static String getRandom4Num() {
		DecimalFormat decfmt = new DecimalFormat("0000");
		double randKey_int = (double) Math.random() * 10000;// 6位随机码
		String randKey = decfmt.format(randKey_int);
		return randKey;
	}

	/**
	 * 生成新文件名称,保留原来后缀名(注意：文件不一定有后缀)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getNewFileName(String fileName) {
		String suffix = "";
		int lastDot = fileName.lastIndexOf(".");
		int lastslash = fileName.lastIndexOf(System
				.getProperty("file.separator"));
		if (lastDot > lastslash) {
			suffix = fileName.substring(lastDot);
		} else {
		}

		String newFileName = createID20() + suffix;
		return newFileName;
	}

	/**
	 * 获取给定时间所在周的第一天(Sunday)的日期和最后一天(Saturday)的日期 *
	 * 
	 * @param calendar
	 * @return Date数组，[0]为第一天的日期，[1]最后一天的日期
	 */
	public static java.util.Date[] getWeekStartAndEndDate(java.util.Date pDate) {
		java.util.Calendar calendar = new GregorianCalendar();
		calendar.setTime(pDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 0, 0, 0);

		java.util.Date[] dates = new java.util.Date[2];
		java.util.Date firstDateOfWeek, lastDateOfWeek; // 得到当天是这周的第几天
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 减去dayOfWeek,得到第一天的日期，因为Calendar用０－６代表一周七天，所以要减一
		calendar.add(Calendar.DAY_OF_WEEK, -(dayOfWeek - 1));
		firstDateOfWeek = calendar.getTime(); // 每周7天，加６，得到最后一天的日子
		calendar.add(Calendar.DAY_OF_WEEK, 6);
		lastDateOfWeek = calendar.getTime();
		dates[0] = firstDateOfWeek;
		dates[1] = lastDateOfWeek;
		return dates;
	}

	/**
	 * 获取给定时间所在月的第一天的日期和最后一天的日期
	 * 
	 * @param calendar
	 * @return Date数组，[0]为第一天的日期，[1]最后一天的日期
	 */
	public static java.util.Date[] getMonthStartAndEndDate(java.util.Date pDate) {
		java.util.Calendar calendar = new GregorianCalendar();
		calendar.setTime(pDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 0, 0, 0);

		java.util.Date[] dates = new java.util.Date[2];
		java.util.Date firstDateOfMonth, lastDateOfMonth; // 得到当天是这月的第几天
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 减去dayOfMonth,得到第一天的日期，因为Calendar用０代表每月的第一天，所以要减一
		calendar.add(Calendar.DAY_OF_MONTH, -(dayOfMonth - 1));
		firstDateOfMonth = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1); // calendar.getActualMaximum(Calendar.DAY_OF_MONTH)得到这个月有几天
		lastDateOfMonth = calendar.getTime();
		dates[0] = firstDateOfMonth;
		dates[1] = lastDateOfMonth;
		return dates;
	}

	/**
	 * 获取给定时间所在季度的第一天的日期和最后一天的日期
	 * 
	 * @param calendar
	 * @return Date数组，[0]为第一天的日期，[1]最后一天的日期
	 */
	public static java.util.Date[] getSeasonStartAndEndDate(java.util.Date pDate) {
		java.util.Calendar calendar = new GregorianCalendar();
		calendar.setTime(pDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 0, 0, 0);

		java.util.Date[] dates = new java.util.Date[2];
		java.util.Date firstDateOfMonth, lastDateOfMonth; // 得到当天是这月的第几天
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 减去dayOfMonth,得到第一天的日期，因为Calendar用０代表每月的第一天，所以要减一
		calendar.add(Calendar.DAY_OF_MONTH, -(dayOfMonth - 1));
		firstDateOfMonth = calendar.getTime();

		SimpleDateFormat sfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sfMonth = new SimpleDateFormat("MM");
		int month = Integer.parseInt(sfMonth.format(firstDateOfMonth));
		int year = Integer.parseInt(sfYear.format(firstDateOfMonth));
		if (month >= 1 && month <= 3) {
			calendar.set(year, 0, 1, 0, 0, 0);
			firstDateOfMonth = calendar.getTime();
			calendar.set(year, 2, 1, 0, 0, 0);
			lastDateOfMonth = getMonthStartAndEndDate(calendar.getTime())[1]; // 当月的结束
		} else if (month >= 4 && month <= 6) {
			calendar.set(year, 3, 1, 0, 0, 0);
			firstDateOfMonth = calendar.getTime();
			calendar.set(year, 5, 1, 0, 0, 0);
			lastDateOfMonth = getMonthStartAndEndDate(calendar.getTime())[1]; // 当月的结束
		} else if (month >= 7 && month <= 9) {
			calendar.set(year, 6, 1, 0, 0, 0);
			firstDateOfMonth = calendar.getTime();
			calendar.set(year, 8, 1, 0, 0, 0);
			lastDateOfMonth = getMonthStartAndEndDate(calendar.getTime())[1]; // 当月的结束
		} else {
			calendar.set(year, 9, 1, 0, 0, 0);
			firstDateOfMonth = calendar.getTime();
			calendar.set(year, 11, 1, 0, 0, 0);
			lastDateOfMonth = getMonthStartAndEndDate(calendar.getTime())[1]; // 当月的结束
		}
		dates[0] = firstDateOfMonth;
		dates[1] = lastDateOfMonth;
		return dates;
	}

	/**
	 * 获取给定时间所在 半年 的第一天的日期和最后一天的日期
	 * 
	 * @param calendar
	 * @return Date数组，[0]为第一天的日期，[1]最后一天的日期
	 */
	public static java.util.Date[] getHalfYearStartAndEndDate(
			java.util.Date pDate) {
		java.util.Calendar calendar = new GregorianCalendar();
		calendar.setTime(pDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 0, 0, 0);

		java.util.Date[] dates = new java.util.Date[2];
		java.util.Date firstDateOfMonth, lastDateOfMonth; // 得到当天是这月的第几天
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 减去dayOfMonth,得到第一天的日期，因为Calendar用０代表每月的第一天，所以要减一
		calendar.add(Calendar.DAY_OF_MONTH, -(dayOfMonth - 1));
		firstDateOfMonth = calendar.getTime();

		SimpleDateFormat sfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sfMonth = new SimpleDateFormat("MM");
		int month = Integer.parseInt(sfMonth.format(firstDateOfMonth));
		int year = Integer.parseInt(sfYear.format(firstDateOfMonth));
		if (month >= 1 && month <= 6) {
			calendar.set(year, 0, 1, 0, 0, 0);
			firstDateOfMonth = calendar.getTime();
			calendar.set(year, 5, 1, 0, 0, 0);
			lastDateOfMonth = getMonthStartAndEndDate(calendar.getTime())[1]; // 当月的结束
		} else {
			calendar.set(year, 6, 1, 0, 0, 0);
			firstDateOfMonth = calendar.getTime();
			calendar.set(year, 11, 1, 0, 0, 0);
			lastDateOfMonth = getMonthStartAndEndDate(calendar.getTime())[1]; // 当月的结束
		}
		dates[0] = firstDateOfMonth;
		dates[1] = lastDateOfMonth;
		return dates;
	}

	/**
	 * 计算当前日期是第几周，by sjw
	 * 
	 * @param date
	 * @return
	 */
	public static int getTheWeekNo(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = 0;
		i = c.get(Calendar.WEEK_OF_YEAR);
		return i;
	}

	/**
	 * 计算当前日期是第几季度，by sjw
	 * 
	 * @param date
	 * @return
	 */
	public static int getTheSeasonNo(java.util.Date date) {
		int i = 0;
		int j = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		i = c.get(Calendar.MONTH) + 1;
		j = (i / 3) + 1;
		if (j > 4) {
			j = 4;
		}

		return j;
	}

	/**
	 * 去除List里相同的object
	 * 
	 * @param listIn
	 *            ArrayList
	 * @return ArrayList
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList filterList(ArrayList listIn) {
		ArrayList listOut = new ArrayList();
		for (int i = 0; i < listIn.size(); i++) {
			if (!listOut.contains(listIn.get(i))) {
				listOut.add(listIn.get(i));
			}
		}
		return listOut;
	}

	/**
	 * 左填充，和 Oralce 内置函数 lpad() 同样的功能
	 * 
	 * @param str
	 *            String 补填充的字符串
	 * @param length
	 *            int 填充的长度
	 * @param pad
	 *            String 填充的内容
	 * @return String
	 */
	public static String lpad(String str, int length, String pad) {
		String output = "";
		for (int i = 0; i < length; i++) {
			output += pad;
		}
		output = output + str;
		return output.substring(output.length() - length);
	}

	/**
	 * 左填充，和 Oralce 内置函数 rpad() 同样的功能
	 * 
	 * @param str
	 *            String 补填充的字符串
	 * @param length
	 *            int 填充的长度
	 * @param pad
	 *            String 填充的内容
	 * @return String
	 */
	public static String rpad(String str, int length, String pad) {
		String output = "";
		for (int i = 0; i < length; i++) {
			output += pad;
		}
		output = str + output;
		return output.substring(0, length);
	}

	public static String insertString(String str, int step, String insertLeft,
			String insertRight) {
		String sReturn = "";
		if (str.length() < step) {
			return str;
		}
		for (int i = 0; i < str.length(); i = i + step) {
			int iEnd = i + step;
			if (iEnd < str.length()) {
				sReturn += insertLeft + str.substring(i, iEnd) + insertRight;
			} else {
				sReturn += insertLeft + str.substring(i, str.length());
			}
		}
		return sReturn;
	}

	/**
	 * 将横向读的文本转化为纵向读的，类似： 一二 一五 三四 变成： 二六 五六 三七 七 四
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String getStrX2Y(String str) {
		if (str.length() == 0) {
			return str;
		}
		String pad = "";
		char[] strs = str.toCharArray();
		switch (str.length()) {
		case 1:
			return String.valueOf(strs[0]) + pad + pad + pad + pad + pad + pad
					+ pad;
		case 2:
			return String.valueOf(strs[0]) + String.valueOf(strs[1]) + pad
					+ pad + pad + pad + pad + pad;
		case 3:
			return String.valueOf(strs[0]) + String.valueOf(strs[2])
					+ String.valueOf(strs[1]) + pad + pad + pad + pad + pad;
		case 4:
			return String.valueOf(strs[0]) + String.valueOf(strs[2])
					+ String.valueOf(strs[1]) + String.valueOf(strs[3]) + pad
					+ pad + pad + pad;
		case 5:
			return String.valueOf(strs[0]) + String.valueOf(strs[3])
					+ String.valueOf(strs[1]) + String.valueOf(strs[4])
					+ String.valueOf(strs[2]) + pad + pad + pad;
		case 6:
			return String.valueOf(strs[0]) + String.valueOf(strs[3])
					+ String.valueOf(strs[1]) + String.valueOf(strs[4])
					+ String.valueOf(strs[2]) + String.valueOf(strs[5]) + pad
					+ pad;
		case 7:
			return String.valueOf(strs[0]) + String.valueOf(strs[4])
					+ String.valueOf(strs[1]) + String.valueOf(strs[5])
					+ String.valueOf(strs[2]) + String.valueOf(strs[6])
					+ String.valueOf(strs[3]) + pad;
		case 8:
			return String.valueOf(strs[0]) + String.valueOf(strs[4])
					+ String.valueOf(strs[1]) + String.valueOf(strs[5])
					+ String.valueOf(strs[2]) + String.valueOf(strs[6])
					+ String.valueOf(strs[3]) + String.valueOf(strs[7]);
		}
		return str;
	}

	public String getStr(String str) {
		StringBuffer temp = new StringBuffer();
		int strLength = 0; // 保存要截取的长度
		if (str.length() <= 4) { // 如果字符串总长度小于4
			strLength = 2; // 只截前2个字符
		} else if (str.length() <= 6) { // 如果字符串总长度小于4
			strLength = 3; // 只截前2个字符
		} else if (str.length() <= 8) { // 如果字符串总长度小于8
			strLength = 4; // 只截前4个字符
		} else if (str.length() <= 10) { // 如果字符串总长度小于10
			strLength = 5; // 只截前5个字符
		}

		temp = temp.append(str.substring(0, strLength)); // 截前 strLength
		// 个字符.并保存到temp里
		String t2 = str.substring(strLength); // 剩下的字符
		// //System.out.println("t2=" + t2);
		int j = 0;
		// temp现在保存的是前strLength个字符.
		// 再把t2的字符插入到temp里
		for (int i = 0; i <= temp.length(); i++) {
			if (i % 2 != 0) {
				if (j < t2.length()) { // 判断长度
					temp.insert(i, t2.charAt(j)); // 把t2里的第j个字符插入到temp的第i个位置
					j++;
				}
			}
		}
		return temp.toString();
	}

	/**
	 * 用来创建文件和文件夹
	 * 
	 * @param fileName
	 *            文件或文件夹名称
	 * @throws IOException
	 * @throws Exception
	 */
	public static boolean createFile(String fileName) throws IOException,
			Exception {
		File file = new File(fileName);
		if (file.exists()) /* does file exist? If so, can it be written to */
		{
			if (file.canWrite() == false) {
				return false;
			}
		} else {
			String path = null; /* Does not exist. Create the directories */

			int firstSlash = fileName.indexOf(File.separatorChar);
			int finalSlash = fileName.lastIndexOf(File.separatorChar);

			if (finalSlash == 0) { /* error, not valid path */
			} else if (finalSlash == 1) /* UNIX root dir */
			{
				path = File.separator;
			} else if (firstSlash == finalSlash) { /*
													 * for example c:\ Then make
													 * sure slash is part of
													 * path
													 */
				path = fileName.substring(0, finalSlash + 1);
			} else {
				path = fileName.substring(0, finalSlash);
			}

			File dir = new File(path);
			dir.mkdirs();
		}
		return true;
	}

	private static String HexCode[] = { "0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "a", "b", "c", "d", "e", "f" };

	public static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return HexCode[d1] + HexCode[d2];
	}

	public static String byteArrayToHexString(byte b[]) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result = result + byteToHexString(b[i]);
		}
		return result;
	}

	public static int byte2int(byte b[], int offset) {
		return b[offset + 3] & 0xff | (b[offset + 2] & 0xff) << 8
				| (b[offset + 1] & 0xff) << 16 | (b[offset] & 0xff) << 24;
	}

	public static int byte2int(byte b[]) {
		return b[3] & 0xff | (b[2] & 0xff) << 8 | (b[1] & 0xff) << 16
				| (b[0] & 0xff) << 24;
	}

	public static long byte2long(byte b[]) {
		return (long) b[7] & (long) 255 | ((long) b[6] & (long) 255) << 8
				| ((long) b[5] & (long) 255) << 16
				| ((long) b[4] & (long) 255) << 24
				| ((long) b[3] & (long) 255) << 32
				| ((long) b[2] & (long) 255) << 40
				| ((long) b[1] & (long) 255) << 48 | (long) b[0] << 56;
	}

	public static long byte2long(byte b[], int offset) {
		return (long) b[offset + 7] & (long) 255
				| ((long) b[offset + 6] & (long) 255) << 8
				| ((long) b[offset + 5] & (long) 255) << 16
				| ((long) b[offset + 4] & (long) 255) << 24
				| ((long) b[offset + 3] & (long) 255) << 32
				| ((long) b[offset + 2] & (long) 255) << 40
				| ((long) b[offset + 1] & (long) 255) << 48
				| (long) b[offset] << 56;
	}

	public static byte[] int2byte(int n) {
		byte b[] = new byte[4];
		b[0] = (byte) (n >> 24);
		b[1] = (byte) (n >> 16);
		b[2] = (byte) (n >> 8);
		b[3] = (byte) n;
		return b;
	}

	/**
	 * n 为待转数据，buf[]为转换后的数据，offset为buf[]中转换的起始点 转换后数据从低到高位
	 */
	public static void int2byte(int n, byte buf[], int offset) {
		buf[offset] = (byte) (n >> 24);
		buf[offset + 1] = (byte) (n >> 16);
		buf[offset + 2] = (byte) (n >> 8);
		buf[offset + 3] = (byte) n;
	}

	public static byte[] short2byte(int n) {
		byte b[] = new byte[2];
		b[0] = (byte) (n >> 8);
		b[1] = (byte) n;
		return b;
	}

	public static void short2byte(int n, byte buf[], int offset) {
		buf[offset] = (byte) (n >> 8);
		buf[offset + 1] = (byte) n;
	}

	public static byte[] long2byte(long n) {
		byte b[] = new byte[8];
		b[0] = (byte) (int) (n >> 56);
		b[1] = (byte) (int) (n >> 48);
		b[2] = (byte) (int) (n >> 40);
		b[3] = (byte) (int) (n >> 32);
		b[4] = (byte) (int) (n >> 24);
		b[5] = (byte) (int) (n >> 16);
		b[6] = (byte) (int) (n >> 8);
		b[7] = (byte) (int) n;
		return b;
	}

	public static void long2byte(long n, byte buf[], int offset) {
		buf[offset] = (byte) (int) (n >> 56);
		buf[offset + 1] = (byte) (int) (n >> 48);
		buf[offset + 2] = (byte) (int) (n >> 40);
		buf[offset + 3] = (byte) (int) (n >> 32);
		buf[offset + 4] = (byte) (int) (n >> 24);
		buf[offset + 5] = (byte) (int) (n >> 16);
		buf[offset + 6] = (byte) (int) (n >> 8);
		buf[offset + 7] = (byte) (int) n;
	}

	/**
	 * 创建多级目录，调用方法类似：makeDirectory("F:", "public_html\\dzjc");
	 * 
	 * @param root
	 *            String 根目录，注意最后不要"\"，例如："F:\abc"
	 * @param path
	 *            String
	 *            多级目录，注意开始和最后都不要"\"，例如："public_html\\dzjc\\supervise\\xzxk
	 *            \\zhjc"
	 */
	public static void makeDirectory(String root, String path) {
		String[] paths = path.split("\\\\");
		String fullPath = "";
		for (int i = 0; i < paths.length; i++) {
			// //System.out.println(paths[i]);
			fullPath += "\\" + paths[i];
			File file = new File(root + fullPath);
			file.mkdir();
		}
	}

	public static int setZtMaxHeight(int height) {
		// 动态设置数据轴上的显示最大值
		if (height < 10) {
			height = 10;
		} else if (height < 95) {
			height = 100;
		} else if (height < 1000) {
			// 如果类似195大于190的,就最高变成300,如果是200可能柱子上的数字显示不出来
			if ((height / 10) > ((height / 100) * 10 + 9)) {
				// 除100是为了确定数轴上显示的是整数
				height = (height / 100) * (100) + 200;
			} else {
				height = (height / 100) * (100) + 100;
			}
		} else if (height < 10000) {
			if ((height / 100) > ((height / 1000) * 10 + 9)) {
				height = (height / 1000) * (1000) + 2000;
			} else {
				height = (height / 1000) * (1000) + 1000;
			}
		} else if (height < 100000) {
			if ((height / 1000) > ((height / 10000) * 10 + 9)) {
				height = (height / 10000) * (10000) + 20000;
			} else {
				height = (height / 10000) * (10000) + 10000;
			}
		} else if (height < 1000000) {
			if ((height / 10000) > ((height / 100000) * 10 + 9)) {
				height = (height / 100000) * (100000) + 200000;
			} else {
				height = (height / 100000) * (100000) + 100000;
			}
		} else {
			height = (height / 1000000) * (1000000) + 2000000;
		}
		return height;
	}

	/**
	 * 获取BASE64编码后的字符串（编码）
	 * 
	 * @param s
	 *            String
	 * @return String
	 */
	public static String getBASE64(String s) {
		if (s == null) {
			return null;
		}
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	/**
	 * 从BASE64编码的内容里获取原内容（解码）
	 * 
	 * @param s
	 *            String
	 * @return String
	 * @throws Exception
	 */
	public static String getFromBASE64(String s) throws Exception {
		if (s == null) {
			return null;
		}
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取异常的堆栈信息
	 * 
	 * @param e
	 * @return
	 */
	public static String getExceptionDetail(Exception e) {
		StringBuffer msg = new StringBuffer("null");
		if (e != null) {
			msg = new StringBuffer("");
			String message = e.toString();
			int length = e.getStackTrace().length;
			if (length > 0) {
				msg.append(message + "\n");
				for (int i = 0; i < length; i++) {
					msg.append("\t" + e.getStackTrace()[i] + "\n");
				}
			} else {
				msg.append(message);
			}
		}
		return msg.toString();

	}

	public static void main(String[] args) {
		// System.out.println(getNewID_20_ByRandom());
	}

}
