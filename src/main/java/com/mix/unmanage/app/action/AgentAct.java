package com.mix.unmanage.app.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.mix.unmanage.domain.entity.Agent;
import com.mix.unmanage.domain.manager.AgentManager;
import com.mix.util.PaginationUtil;
import com.mix.util.ServletUtil;

@Controller
@RequestMapping("/*")
public class AgentAct {

	private static final Logger log = Logger.getLogger(AgentAct.class);

	@Resource
	private AgentManager agentManager;

	@RequestMapping(value = "agentList.act")
	public String agentList(ModelMap map, HttpServletRequest request) {
		log.info("查询代理商列表请求=" + ServletUtil.herf(request));

		String page = request.getParameter("page");
		String name = request.getParameter("name");

		if (StringUtils.isBlank(page)) {
			page = "1";
		} else if (Integer.parseInt(page) < 1) {
			page = "1";
		}

		Map<String, Object> pmap = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(name)) {
			pmap.put("name", name);
			map.put("name", name);
		}

		int totalRecordCount = agentManager.count(pmap);
		List<Agent> list = null;
		if (totalRecordCount > 0) {
			list = agentManager.list(pmap, Integer.parseInt(page),
					PaginationUtil.PAGE_SIZE);
			map.put("list", list);
			map.put("page", Integer.parseInt(page));
			map.put("totalRecordCount", totalRecordCount);
		}

		return "agent/list";
	}

	@RequestMapping(value = "toAddPage.act")
	public String toAddPage(ModelMap map, HttpServletRequest request) {
		log.info("跳转到增加代理商页面请求=" + ServletUtil.herf(request));
		return "agent/add";
	}

	@RequestMapping(value = "addAgent.act")
	public void addAgent(Agent agent, HttpServletResponse response,
			HttpServletRequest request) {
		log.info("添加agent请求=" + ServletUtil.herf(request));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("添加代理商异常！");
		}

		if (agent == null) {
			out.print("参数有误！");
			return;
		}

		try {
			int i = agentManager.insert(agent);

			if (1 == i) {
				log.info("增加代理商成功");
				out.print("增加代理商成功！");
			} else {
				log.info("增加代理商失败");
				out.print("增加代理商失败！");
			}
		} catch (Exception e) {
			log.info("增加代理商失败", e);
			out.print("增加代理商失败，可能是名字重复！");
		}

	}
}
