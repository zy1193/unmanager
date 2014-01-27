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

import com.mix.unmanage.domain.entity.Ad;
import com.mix.unmanage.domain.manager.AdManager;
import com.mix.util.PaginationUtil;
import com.mix.util.ServletUtil;

@Controller
@RequestMapping("/ad/*")
public class AdAct {

	private static final Logger log = Logger.getLogger(AdAct.class);

	@Resource
	private AdManager adManager;
	
	
	@RequestMapping(value = "toAddAd.act")
	public String toAddPage(ModelMap map, HttpServletRequest request) {
		log.info("跳转到增加广告页面请求=" + ServletUtil.herf(request));
		return "ad/add";
	}
	
	@RequestMapping(value = "addAd.act")
	public void addAd(Ad ad, HttpServletResponse response,
			HttpServletRequest request) {
		log.info("添加广告请求=" + ServletUtil.herf(request));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("添加广告异常！");
		}

		if (ad == null) {
			out.print("参数有误！");
			return;
		}

		try {
			int i = adManager.insert(ad);

			if (1 == i) {
				log.info("增加广告成功");
				out.print("增加广告成功！");
			} else {
				log.info("增加广告失败");
				out.print("增加广告失败！");
			}
		} catch (Exception e) {
			log.info("增加广告失败", e);
			out.print("增加广告失败！");
		}

	}

	@RequestMapping(value = "adList.act")
	public String adList(ModelMap map, HttpServletRequest request) {
		log.info("广告列表请求=" + ServletUtil.herf(request));

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

		int totalRecordCount = adManager.count(pmap);
		List<Ad> list = null;
		if (totalRecordCount > 0) {
			list = adManager.list(pmap, Integer.parseInt(page),
					PaginationUtil.PAGE_SIZE);
			map.put("list", list);
			map.put("page", Integer.parseInt(page));
			map.put("totalRecordCount", totalRecordCount);
		}

		return "ad/list";
	}

	@RequestMapping(value = "toEditAd.act")
	public String toEditAd(ModelMap map, HttpServletRequest request) {
		log.info("跳转到编辑广告页面：" + ServletUtil.herf(request));

		String id = request.getParameter("id");

		if (StringUtils.isBlank(id)) {
			log.info("编辑广告，参数错误");
			return "error";
		}

		Ad ad = adManager.selectAd(Integer.parseInt(id));
		map.put("ad", ad);

		return "ad/editAd";
	}

	@RequestMapping(value = "editAd.act")
	public void editAd(HttpServletResponse response, HttpServletRequest request) {
		log.info("编辑保存帐号请求=" + ServletUtil.herf(request));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("保存帐号异常！");
			return;
		}

		String id = request.getParameter("id");
		String brandid = request.getParameter("brandid");
		String adpid = request.getParameter("adpid");
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String img = request.getParameter("img");

		if (StringUtils.isBlank(id) || StringUtils.isBlank(brandid)
				|| StringUtils.isBlank(adpid) || StringUtils.isBlank(name)
				|| StringUtils.isBlank(url) || StringUtils.isBlank(img)) {
			log.info("编辑广告，参数错误");
			out.print("编辑广告，参数错误，请联系管理员！");
			return;
		}

		try {
			int i = adManager.editAd(Integer.parseInt(id), brandid, adpid,
					name, url, img);

			if (1 == i) {
				log.info("编辑广告成功");
				out.print("编辑广告成功！");
				return;
			} else {
				log.info("编辑广告失败");
				out.print("编辑广告失败！");
				return;
			}
		} catch (Exception e) {
			log.info("编辑广告失败", e);
			out.print("编辑广告失败，请联系管理员！");
			return;
		}

	}
	
	@RequestMapping(value = "deleteAd.act")
	public void deleteAd(HttpServletResponse response,
			HttpServletRequest request) {
		log.info("删除广告请求=" + ServletUtil.herf(request));

		String id = request.getParameter("id");

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("删除广告异常！");
			return;
		}

		if (StringUtils.isBlank(id)) {
			out.print("fail");
			return;
		}

		Map<String, Object> map = new HashMap<String, Object>(1);
		map.put("id", id);
		if (1 == adManager.deleteAd(map)) {
			log.info("删除广告成功！");
			out.print("success");
			return;
		} else {
			log.info("删除广告失败！");
			out.print("fail");
			return;
		}

	}

}
