package com.mix.unmanage.app.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mix.unmanage.common.Const;
import com.mix.unmanage.domain.entity.Tuser;
import com.mix.unmanage.domain.manager.TuserManager;
import com.mix.util.ServletUtil;

@Controller
@RequestMapping("/*")
public class ChangePwd {

	private static final Logger log = Logger.getLogger(ChangePwd.class);

	@Resource
	private TuserManager tuserManager;

	@RequestMapping(value = "tochangepwd.act")
	public String index() {
		return "home/changepassword";
	}

	@RequestMapping(value = "changepwd.act")
	public void changePwd(HttpServletResponse response,
			HttpServletRequest request) {

		log.info("1.修改密码请求=" + ServletUtil.herf(request));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("登录异常！");
		}

		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");

		if (StringUtils.isBlank(oldpwd) || StringUtils.isBlank(newpwd)) {
			out.print("密码不能为空！");
			return;
		}

		HttpSession session = request.getSession();
		Tuser t = (Tuser) session.getAttribute(Const.USER_SESSION_NAME);
		Tuser nt = tuserManager.selectUser(t.getUid());

		try {
			if ((DigestUtils.md5Hex(oldpwd.getBytes(Const.CHARSET)).equals(nt
					.getPwd()))) {
				/** 修改密码 */
				nt.setPwd(DigestUtils.md5Hex(newpwd.getBytes(Const.CHARSET)));
				tuserManager.updateUser(nt);

				/** 更新session里的密码 */
				t.setPwd(nt.getPwd());
				session.setAttribute(Const.USER_SESSION_NAME, t);
			} else {
				out.print("密码不正确！");
				return;
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
}
