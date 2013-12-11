package com.mix.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

	public static final String herf(HttpServletRequest req) {
		String queryString = req.getQueryString();
		if (queryString == null) {
			@SuppressWarnings("rawtypes")
			Enumeration enu = req.getParameterNames();
			StringBuilder buff = new StringBuilder();
			String name, value;
			for (;;) {
				if (enu.hasMoreElements()) {
					name = (String) enu.nextElement();
					value = req.getParameter(name);
					buff.append('&').append(name).append('=').append(value);
				} else {
					break;
				}
			}
			if (buff.length() > 0) {
				buff.deleteCharAt(0);
				queryString = buff.toString();
			}
		}
		if (queryString == null) {
			return req.getRequestURI();
		} else {
			return new StringBuilder(req.getRequestURI()).append('?')
					.append(queryString).toString();
		}
	}

}
