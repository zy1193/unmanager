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

import com.mix.unmanage.domain.entity.Conf;
import com.mix.unmanage.domain.manager.ConfManager;
import com.mix.util.PaginationUtil;
import com.mix.util.ServletUtil;

@Controller
@RequestMapping("/conf/*")
public class ConfAct {

	private static final Logger log = Logger.getLogger(ConfAct.class);

	@Resource
	private ConfManager confManager;

	@RequestMapping(value = "confList.act")
	public String confList(ModelMap map, HttpServletRequest request) {
		log.info("配置列表请求=" + ServletUtil.herf(request));

		String page = request.getParameter("page");
		String brandid = "yx";// request.getParameter("brandid");

		if (StringUtils.isBlank(page)) {
			page = "1";
		} else if (Integer.parseInt(page) < 1) {
			page = "1";
		}

		Map<String, Object> pmap = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(brandid)) {
			pmap.put("brandid", brandid);
			map.put("brandid", brandid);
		}

		int totalRecordCount = confManager.count(pmap);
		List<Conf> list = null;
		if (totalRecordCount > 0) {
			list = confManager.list(pmap, Integer.parseInt(page),
					PaginationUtil.PAGE_SIZE);
			map.put("list", list);
			map.put("page", Integer.parseInt(page));
			map.put("totalRecordCount", totalRecordCount);
		}

		return "conf/list";
	}

	@RequestMapping(value = "toEditConf.act")
	public String toEditConf(ModelMap map, HttpServletRequest request) {
		log.info("跳转到编辑配置页面：" + ServletUtil.herf(request));

		String id = request.getParameter("id");

		if (StringUtils.isBlank(id)) {
			log.info("编辑配置，参数错误");
			return "error";
		}

		Conf conf = confManager.selectConf(Integer.parseInt(id));
		map.put("conf", conf);

		return "conf/editConf";
	}

	@RequestMapping(value = "editConf.act")
	public void editConf(HttpServletResponse response,
			HttpServletRequest request) {
		log.info("编辑保存配置请求=" + ServletUtil.herf(request));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("保存配置异常！");
			return;
		}

		String id = request.getParameter("id");
		String value = request.getParameter("value");

		if (StringUtils.isBlank(id) || StringUtils.isBlank(value)) {
			log.info("编辑配置，参数错误");
			out.print("编辑配置，参数错误，请联系管理员！");
			return;
		}

		try {
			int i = confManager.editConf(Integer.parseInt(id), value);

			if (1 == i) {
				log.info("编辑配置成功");
				out.print("编辑配置成功！");
				return;
			} else {
				log.info("编辑配置失败");
				out.print("编辑配置失败！");
				return;
			}
		} catch (Exception e) {
			log.info("编辑配置失败", e);
			out.print("编辑配置失败，请联系管理员！");
			return;
		}

	}
}
