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

import com.mix.unmanage.domain.entity.SysMsg;
import com.mix.unmanage.domain.manager.SysMsgManager;
import com.mix.util.PaginationUtil;
import com.mix.util.ServletUtil;

@Controller
@RequestMapping("/sysMsg/*")
public class SysMsgAct {

	private static final Logger log = Logger.getLogger(SysMsgAct.class);

	@Resource
	private SysMsgManager sysMsgManager;

	@RequestMapping(value = "sysMsgList.act")
	public String sysMsgList(ModelMap map, HttpServletRequest request) {
		log.info("查询通知公告列表请求=" + ServletUtil.herf(request));

		String page = request.getParameter("page");
		if (StringUtils.isBlank(page)) {
			page = "1";
		} else if (Integer.parseInt(page) < 1) {
			page = "1";
		}
		Map<String, Object> pmap = new HashMap<String, Object>();

		int totalRecordCount = sysMsgManager.count(pmap);
		List<SysMsg> list = null;
		if (totalRecordCount > 0) {
			list = sysMsgManager.list(pmap, Integer.parseInt(page),
					PaginationUtil.PAGE_SIZE);
			map.put("list", list);
			map.put("page", Integer.parseInt(page));
			map.put("totalRecordCount", totalRecordCount);
		}

		return "sysmsg/sysmsglist";
	}

	@RequestMapping(value = "toAddPage.act")
	public String toAddPage(ModelMap map, HttpServletRequest request) {
		log.info("跳转到系统公告页面请求=" + ServletUtil.herf(request));
		return "sysmsg/add";
	}

	@RequestMapping(value = "addSysMsg.act")
	public void addSysMsg(SysMsg sysMsg, HttpServletResponse response,
			HttpServletRequest request) {
		log.info("添加系统公告请求=" + ServletUtil.herf(request));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("添加系统公告异常！");
		}

		if (sysMsg == null) {
			out.print("参数有误！");
			return;
		}

		try {
			int i = sysMsgManager.addSysMsg("yx", sysMsg.getTitle(),
					sysMsg.getMsg(), "");

			if (1 == i) {
				log.info("增加系统公告成功");
				out.print("增加系统公告成功！");
			} else {
				log.info("增加系统公告失败");
				out.print("增加系统公告失败！");
			}
		} catch (Exception e) {
			log.info("增加系统公告失败", e);
			out.print("增加系统公告失败，可能是名字重复！");
		}

	}

	@RequestMapping(value = "toEditSysMsg.act")
	public String toEditSysMsg(ModelMap map, HttpServletRequest request) {
		log.info("跳转到编辑系统公告页面：" + ServletUtil.herf(request));

		String id = request.getParameter("id");

		if (StringUtils.isBlank(id)) {
			log.info("编辑系统公告，参数错误");
			return "error";
		}

		map.put("sysMsg", sysMsgManager.getSysMsgInfo(Integer.parseInt(id)));

		return "sysmsg/edit";
	}

	@RequestMapping(value = "editSysMsg.act")
	public void editSysMsg(SysMsg sysMsg, HttpServletResponse response,
			HttpServletRequest request) {
		log.info("编辑系统公告请求=" + ServletUtil.herf(request));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("保存系统公告异常！");
		}

		if (sysMsg == null) {
			out.print("参数有误！");
			return;
		}

		try {
			int i = sysMsgManager.updateSysMsg("yx", sysMsg.getTitle(),
					sysMsg.getMsg(), "", sysMsg.getId());

			if (1 == i) {
				log.info("编辑系统公告成功");
				out.print("编辑系统公告成功！");
			} else {
				log.info("编辑系统公告失败");
				out.print("编辑系统公告失败！");
			}
		} catch (Exception e) {
			log.info("编辑系统公告失败", e);
			out.print("编辑系统公告失败，请联系管理员！");
		}

	}
	
	@RequestMapping(value = "deleteSysMsg.act")
	public void deleteSysMsg(HttpServletResponse response,
			HttpServletRequest request) {
		log.info("删除系统公告请求=" + ServletUtil.herf(request));

		String id = request.getParameter("id");

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("删除系统公告异常！");
			return;
		}

		if (StringUtils.isBlank(id)) {
			out.print("fail");
			return;
		}

		Map<String, Object> map = new HashMap<String, Object>(1);
		map.put("id", id);
		if (1 == sysMsgManager.delSysMsg(Integer.parseInt(id))) {
			log.info("删除系统公告成功！");
			out.print("success");
			return;
		} else {
			log.info("删除系统公告失败！");
			out.print("fail");
			return;
		}

	}

}
