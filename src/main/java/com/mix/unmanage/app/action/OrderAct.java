package com.mix.unmanage.app.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mix.unmanage.domain.entity.Order;
import com.mix.unmanage.domain.manager.OrderManager;
import com.mix.util.ServletUtil;

@Controller
@RequestMapping("/order/*")
public class OrderAct {
	private static final Logger log = Logger.getLogger(OrderAct.class);

	@Resource
	private OrderManager orderManager;

	@RequestMapping(value = "orderList4Card.act")
	public String orderList4Card(ModelMap map, HttpServletResponse response,
			HttpServletRequest request) {
		log.info("根据充值卡查询订单=" + ServletUtil.herf(request));

		String brandid = "yx";
		String cno = request.getParameter("cno");
		String cps = request.getParameter("cps");

		if (StringUtils.isBlank(cno) && StringUtils.isBlank(cps)) {
			log.error("根据充值卡查询订单参数错误！");
			return "";
		}

		Map<String, Object> pmap = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(brandid)) {
			pmap.put("brandid", brandid);
		}
		if (StringUtils.isNotBlank(cno)) {
			pmap.put("cno", cno);
		}
		if (StringUtils.isNotBlank(cps)) {
			pmap.put("cps", cps);
		}

		List<Order> list = orderManager.list4Card(pmap);
		map.put("list", list);

		return "order/orderdetail4cars";

	}
}
