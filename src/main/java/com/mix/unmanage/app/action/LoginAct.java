package com.mix.unmanage.app.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mix.unmanage.common.Const;
import com.mix.unmanage.domain.entity.Tuser;
import com.mix.unmanage.domain.manager.TuserManager;

@Controller
@RequestMapping("/*")
public class LoginAct {
	private static final Logger log = Logger.getLogger(LoginAct.class);

	@Resource
	private TuserManager tuserManager;

	@RequestMapping(value = "login.act")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "index.act")
	public String index() {
		return "home/index";
	}

	@RequestMapping(value = "relodaUser.act")
	public void relodaUser() {
		tuserManager.reloadUser();

	}

	@RequestMapping(value = "userLogin.act")
	public void login(HttpServletResponse response, HttpServletRequest request,
			Tuser tuser, String securityCode) {

		HttpSession session = request.getSession();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("登录异常！");
		}

		if (!isEmpty(tuser.getUid()) || !isEmpty(tuser.getPwd())) {
			out.print("用户名或者密码为空");
			return;
		}

//		if (!securityCode.equals((String) session
//				.getAttribute(SecurityCode.SESSION_ATTR_NAME))) {
//			out.print("验证码不正确");
//			return;
//		}

		if (tuserManager == null) {
			out.print("服务异常");
			return;
		}

		if (!tuserManager.checkUser(tuser, request)) {
			out.print("用户名或者密码错误");
			return;
		}

		//return;
	}
	
	@RequestMapping(value = "loginout.act")
	public String logout(HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();

		session.removeAttribute(Const.USER_SESSION_NAME);
		return "login";
	}

	public boolean isEmpty(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		} else {
			return true;
		}
	}
}
