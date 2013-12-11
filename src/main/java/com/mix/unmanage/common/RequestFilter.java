/*
 * 版权所有 (C) 2001-2012 深圳市极智传媒。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2012-8-7，JiangQian创建。 
 */
package com.mix.unmanage.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mix.unmanage.domain.entity.Tuser;

public class RequestFilter implements Filter {
	private final static Logger log = Logger.getLogger(RequestFilter.class);

	/* 用这个正则表达式匹配action的地址 * */
	private static final Pattern ACT_URL = Pattern.compile("^.*/(.*\\.act)$");

	/** 登录后可访问的地址 **/
	private static final List<String> LOGINED_PERMIT = new ArrayList<String>();
	static {
		LOGINED_PERMIT.add("listUnite.act");
		LOGINED_PERMIT.add("listUse.act");
		LOGINED_PERMIT.add("dataUse.act");
		LOGINED_PERMIT.add("shopUse.act");
		LOGINED_PERMIT.add("toAddPage.act");
		LOGINED_PERMIT.add("orderInfoList.act");
		LOGINED_PERMIT.add("pandectSales.act");
		LOGINED_PERMIT.add("recSale.act");
		LOGINED_PERMIT.add("examineUnite.act");
		LOGINED_PERMIT.add("delete.act");
		LOGINED_PERMIT.add("add.act");
		LOGINED_PERMIT.add("updateExamineStatus.act");
		LOGINED_PERMIT.add("updateGoodsStatus.act");
		LOGINED_PERMIT.add("changepwd.act");
		LOGINED_PERMIT.add("tochangepwd.act");
		LOGINED_PERMIT.add("index.act");
	}

	/** 登录后不可访问的地址 **/
	private static final List<String> LOGINED_DENY = new ArrayList<String>();
	static {
		// LOGINED_DENY.add("toGetUidByEmail.act");
		// LOGINED_DENY.add("toFindUidByMobile.act");
	}

	/** 登录页面 **/
	private RequestDispatcher login;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		Matcher matcher = ACT_URL.matcher(url);

		if (matcher.matches()) {
			HttpServletResponse rsp = (HttpServletResponse) response;
			rsp.setHeader("Pragma", "No-cache");
			rsp.setHeader("Cache-Control", "no-cache");
			rsp.setHeader("Cache-Control", "no-store");
			rsp.setDateHeader("Expires", 0);

			String actname = matcher.group(1);

			Tuser tuser = (Tuser) req.getSession().getAttribute(
					Const.USER_SESSION_NAME);

			if (LOGINED_PERMIT.contains(actname) && tuser == null) {
				request.setAttribute("actname", actname);
				login.forward(request, response);
				return;
			}

		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext ctx = filterConfig.getServletContext();
		login = ctx.getRequestDispatcher("/login.act");
	}

}
