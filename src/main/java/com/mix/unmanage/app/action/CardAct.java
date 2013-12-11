package com.mix.unmanage.app.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.keicei.util.SequenceUtil;
import com.mix.unmanage.domain.entity.Card;
import com.mix.unmanage.domain.manager.AgentManager;
import com.mix.unmanage.domain.manager.CardManager;
import com.mix.unmanage.domain.manager.GoodsManager;
import com.mix.unmanage.domain.rpc.CreateCardService;
import com.mix.util.PaginationUtil;
import com.mix.util.ServletUtil;

/**
 * 提取充值卡
 * 
 * @author jarry
 * 
 */
@Controller
@RequestMapping("/card/*")
public class CardAct {

	private static final Logger log = Logger.getLogger(CardAct.class);
	@Resource
	private AgentManager agentManager;
	@Resource
	private GoodsManager goodsManager;
	@Resource
	private CreateCardService createCardService;
	@Resource
	private CardManager cardManager;

	@RequestMapping(value = "toCreateCard.act")
	public String toCreateCard(ModelMap map, HttpServletRequest request) {
		log.info("跳转提卡页面=" + ServletUtil.herf(request));
		map.put("agentlist", agentManager.list());
		map.put("goodslist", goodsManager.list());

		return "card/createCard";
	}

	@RequestMapping(value = "createCard.act")
	public void createCard(HttpServletResponse response,
			HttpServletRequest request) {
		log.info("提卡..." + ServletUtil.herf(request));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("生成账号异常！");
		}

		String brandid = "yx";
		String number = request.getParameter("number");
		String agent = request.getParameter("agent");
		String goodsId = request.getParameter("goodsId");
		String endTime = request.getParameter("endTime");

		if (StringUtils.isBlank(number) || StringUtils.isBlank(agent)
				|| StringUtils.isBlank(endTime) || StringUtils.isBlank(goodsId)) {
			log.error("生成账号参数错误！");
			out.print("参数错误！");
		}

		boolean flag = createCardService.createCard(brandid, goodsId, agent,
				endTime, number);

		if (flag) {
			log.error("生成账号成功！");
		} else {
			log.error("生成账号失败！");
			out.print("请稍候再试！");
		}
	}

	@RequestMapping(value = "cardList.act")
	public String cardList(ModelMap map, HttpServletRequest request) {
		log.info("查询卡密列表请求=" + ServletUtil.herf(request));

		String page = request.getParameter("page");
		String goodsId = request.getParameter("goodsId");
		String taskid = request.getParameter("taskid");
		String status = request.getParameter("status");
		String enableFlag = request.getParameter("enableFlag");
		String isvalid = request.getParameter("isvalid");
		String agent = request.getParameter("agent");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");

		if (StringUtils.isBlank(page)) {
			page = "1";
		} else if (Integer.parseInt(page) < 1) {
			page = "1";
		}

		Map<String, Object> pmap = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(goodsId)) {
			pmap.put("goodsId", goodsId);
			map.put("goodsId", goodsId);
		}
		if (StringUtils.isNotBlank(taskid)) {
			pmap.put("taskid", taskid);
			map.put("taskid", taskid);
		}
		if (StringUtils.isNotBlank(status)) {
			pmap.put("status", status);
			map.put("status", status);
		}
		if (StringUtils.isNotBlank(enableFlag)) {
			pmap.put("enableFlag", enableFlag);
			map.put("enableFlag", enableFlag);
		}
		if (StringUtils.isNotBlank(isvalid)) {
			pmap.put("isvalid", isvalid);
			map.put("isvalid", isvalid);
		}
		if (StringUtils.isNotBlank(agent)) {
			pmap.put("agent", agent);
			map.put("agent", agent);
		}
		if (StringUtils.isNotBlank(startTime)) {
			pmap.put("startTime", startTime);
			map.put("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			pmap.put("endTime", endTime);
			map.put("endTime", endTime);
		}

		map.put("agentlist", agentManager.list());
		map.put("goodslist", goodsManager.list());

		int totalRecordCount = cardManager.count(pmap);
		List<Card> list = null;
		if (totalRecordCount > 0) {
			list = cardManager.list(pmap, Integer.parseInt(page),
					PaginationUtil.PAGE_SIZE);
			map.put("list", list);
			map.put("page", Integer.parseInt(page));
			map.put("totalRecordCount", totalRecordCount);
		}

		return "card/list";
	}

	@RequestMapping(value = "exportExcel.act")
	public void exportExcel(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("导出充值卡列表到EXCEL请求=" + ServletUtil.herf(request));

		String goodsId = request.getParameter("goodsId");
		String taskid = request.getParameter("taskid");
		String agent = request.getParameter("agent");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		String enableFlag = request.getParameter("enableFlag");
		String isvalid = request.getParameter("isvalid");

		String filename = "card_info_" + SequenceUtil.id() + ".xls";
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			response.setHeader("Content-disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			response.setContentType("application/msexcel;charset=UTF-8");
		} catch (IOException e) {
			log.error("导出充值卡列表异常！");
		}

		Map<String, Object> pmap = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(goodsId)) {
			pmap.put("goodsId", goodsId);
			map.put("goodsId", goodsId);
		}
		if (StringUtils.isNotBlank(taskid)) {
			pmap.put("taskid", taskid);
			map.put("taskid", taskid);
		}
		if (StringUtils.isNotBlank(status)) {
			pmap.put("status", status);
			map.put("status", status);
		}
		if (StringUtils.isNotBlank(enableFlag)) {
			pmap.put("enableFlag", enableFlag);
			map.put("enableFlag", enableFlag);
		}
		if (StringUtils.isNotBlank(isvalid)) {
			pmap.put("isvalid", isvalid);
			map.put("isvalid", isvalid);
		}
		if (StringUtils.isNotBlank(agent)) {
			pmap.put("agent", agent);
			map.put("agent", agent);
		}
		if (StringUtils.isNotBlank(startTime)) {
			pmap.put("startTime", startTime);
			map.put("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			pmap.put("endTime", endTime);
			map.put("endTime", endTime);
		}

		HSSFWorkbook wb = cardManager.exportExcel(pmap);

		try {
			wb.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

}
