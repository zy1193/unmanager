package com.mix.unmanage.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mix.unmanage.common.Const;
import com.mix.unmanage.domain.entity.Tuser;

@Controller
@RequestMapping("/*")
public class LogoAct {

	private static final Logger log = Logger.getLogger(LogoAct.class);

	@RequestMapping(value = "logo.act")
	public String logo(HttpServletResponse response, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Tuser tuser = (Tuser) (session.getAttribute(Const.USER_SESSION_NAME));
		request.setAttribute("tuser", tuser);
		return "home/logo";
	}

}
