package com.mix.unmanage.app.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mix.unmanage.domain.entity.PayRes;
import com.mix.unmanage.domain.rpc.PayService;
import com.mix.util.ServletUtil;

/**
 * 在线充值
 * 
 * @author jarry
 * 
 */
@Controller
@RequestMapping("/recharge/*")
public class PayAct {

	private static final Logger log = Logger.getLogger(PayAct.class);

	@Resource
	private PayService payService;

	@RequestMapping(value = "toPay4Offline.act")
	public String toPay4Offline(ModelMap map, HttpServletRequest request) {
		log.info("跳转在线充值页面=" + ServletUtil.herf(request));

		return "recharge/pay4Offline";
	}

	@RequestMapping(value = "pay4Offline.act")
	public void pay4Offline(HttpServletResponse response,
			HttpServletRequest request) {
		log.info("线下卡充值=" + ServletUtil.herf(request));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("线下卡充值异常！");
			return;
		}

		String brandid = "yx";
		String uid = request.getParameter("uid");
		String cno = request.getParameter("cno");
		String cps = request.getParameter("cps");
		String goodsid = "2";
		String paytype = "kccard_direct";

		if (StringUtils.isBlank(uid) || StringUtils.isBlank(cno)
				|| StringUtils.isBlank(cps)) {
			log.error("线下卡充值参数错误！");
			out.print("参数错误！");
			return;
		}

		PayRes payRes = payService.pay4Offline(brandid, uid, cno, cps, goodsid,
				paytype);

		if (payRes != null) {
			log.info("线下卡充值成功！");
			out.print(payRes.getMsg());
			return;
		} else {
			log.info("线下卡充值提交失败！");
			out.print("线下卡充值提交失败，请稍候再试！");
			return;
		}

	}

}
