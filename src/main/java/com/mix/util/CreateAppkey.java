package com.mix.util;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.codec.digest.DigestUtils;

/***
 * 生成appKey的工具类
 * 
 * @author ZHANGYONG
 * 
 */
public class CreateAppkey {

	private static final String publicKey = "GUOLING!@#456";
	private static AtomicInteger seq = new AtomicInteger(0);

	public static String cKey() throws UnsupportedEncodingException {
		StringBuffer buffer = new StringBuffer();
		seq.compareAndSet(9999, 0);
		buffer.append(Integer.toString(seq.getAndAdd(1))).append(publicKey)
				.append(System.currentTimeMillis());

		return DigestUtils.md5Hex(buffer.toString().getBytes("UTF-8"))
				.toUpperCase();
	}

	public static void main(String[] args) {
		try {
			System.out.println("appkey=" + cKey());
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
	}
}
