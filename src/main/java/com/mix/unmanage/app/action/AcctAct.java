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
import com.mix.unmanage.domain.entity.Acct;
import com.mix.unmanage.domain.manager.AcctManager;
import com.mix.unmanage.domain.manager.AgentManager;
import com.mix.unmanage.domain.manager.GoodsManager;
import com.mix.unmanage.domain.rpc.CreateAcctService;
import com.mix.util.PaginationUtil;
import com.mix.util.ServletUtil;

/**
 * 创建账户信息 ，包月帐号和余额帐号
 * 
 * @author jarry
 * 
 */
@Controller
@RequestMapping("/acct/*")
public class AcctAct {

	private static final Logger log = Logger.getLogger(AcctAct.class);

	@Resource
	private AgentManager agentManager;
	@Resource
	private CreateAcctService createAcctService;
	@Resource
	private AcctManager acctManager;
	@Resource
	private GoodsManager goodsManager;

	@RequestMapping(value = "toCreateTimeAcct.act")
	public String toCreateTimeAcct(ModelMap map, HttpServletRequest request) {
		log.info("跳转提帐号页面--包月=" + ServletUtil.herf(request));
		map.put("list", agentManager.list());
		map.put("goodslist", goodsManager.list());

		return "acct/timeAcct";
	}

	@RequestMapping(value = "createTimeAcct.act")
	public void createTimeAcct(HttpServletResponse response,
			HttpServletRequest request) {
		log.info("提卡--包月=" + ServletUtil.herf(request));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("生成账号异常！");
		}

		String brandid = "yx";
		String number = request.getParameter("number");
		String agent = request.getParameter("agent");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String acctType = request.getParameter("acctType");
		String goodsId = request.getParameter("goodsId");
		String bindLimit = request.getParameter("bindLimit");

		if (StringUtils.isBlank(number) || StringUtils.isBlank(agent)
				|| StringUtils.isBlank(year) || StringUtils.isBlank(month)
				|| StringUtils.isBlank(day)) {
			log.error("生成账号参数错误！");
			out.print("参数错误！");
		}

		boolean flag = createAcctService.createTimeAcct(brandid, number, agent,
				year, month, day, acctType, goodsId, bindLimit);

		if (flag) {
			log.error("生成账号成功！");
		} else {
			log.error("生成账号失败！");
			out.print("请稍候再试！");
		}

	}

	@RequestMapping(value = "toCreateMoneyAcct.act")
	public String toCreateMoneyAcct(ModelMap map, HttpServletRequest request) {
		log.info("跳转提卡页面--余额=" + ServletUtil.herf(request));
		map.put("list", agentManager.list());

		return "acct/moneyAcct";
	}

	@RequestMapping(value = "createMoneyAcct.act")
	public void createMoneyAcct(HttpServletResponse response,
			HttpServletRequest request) {
		log.info("提卡--余额=" + ServletUtil.herf(request));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("生成账号异常！");
		}

		String brandid = "yx";
		// String pwd = request.getParameter("pwd");
		String number = request.getParameter("number");
		String money = request.getParameter("money");
		String agent = request.getParameter("agent");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");

		if (StringUtils.isBlank(number) || StringUtils.isBlank(agent)
				|| StringUtils.isBlank(year) || StringUtils.isBlank(month)
				|| StringUtils.isBlank(day)) {
			log.error("生成账号参数错误！");
			out.print("参数错误！");
		}

		boolean flag = createAcctService.createMoneyAcct(brandid, number,
				money, year, month, day, agent);

		if (flag) {
			log.error("生成账号成功！");
		} else {
			log.error("生成账号失败！");
			out.print("请稍候再试！");
		}

	}

	@RequestMapping(value = "deleteAcct.act")
	public void deleteAcct(HttpServletResponse response,
			HttpServletRequest request) {
		log.info("删除帐号请求=" + ServletUtil.herf(request));

		String uid = request.getParameter("uid");

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("删除帐号异常！");
			return;
		}

		if (StringUtils.isBlank(uid)) {
			out.print("fail");
			return;
		}

		Map<String, Object> map = new HashMap<String, Object>(1);
		map.put("uid", uid);
		if (1 == acctManager.deleteAcct(map)) {
			log.info("删除帐号成功！");
			out.print("success");
			return;
		} else {
			log.info("删除帐号失败！");
			out.print("fail");
			return;
		}

	}

	@RequestMapping(value = "acctList.act")
	public String acctList(ModelMap map, HttpServletRequest request) {
		log.info("查询帐号列表请求=" + ServletUtil.herf(request));

		String page = request.getParameter("page");
		String uid = request.getParameter("uid");
		String phone = request.getParameter("phone");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String agent = request.getParameter("agent");
		String taskname = request.getParameter("taskname");
		String first = request.getParameter("first");
		String isvalid = request.getParameter("isvalid");
		String acctType = request.getParameter("acctType");

		if (StringUtils.isBlank(page)) {
			page = "1";
		} else if (Integer.parseInt(page) < 1) {
			page = "1";
		}

		Map<String, Object> pmap = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(uid)) {
			pmap.put("uid", uid);
			map.put("uid", uid);
		}
		if (StringUtils.isNotBlank(phone)) {
			pmap.put("phone", phone);
			map.put("phone", phone);
		}
		if (StringUtils.isNotBlank(startTime)) {
			pmap.put("startTime", startTime);
			map.put("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			pmap.put("endTime", endTime);
			map.put("endTime", endTime);
		}
		if (StringUtils.isNotBlank(agent)) {
			pmap.put("agent", agent);
			map.put("agent", agent);
		}
		if (StringUtils.isNotBlank(taskname)) {
			pmap.put("taskname", taskname);
			map.put("taskname", taskname);
		}
		if (StringUtils.isNotBlank(first)) {
			pmap.put("first", first);
			map.put("first", first);
		}
		if (StringUtils.isNotBlank(isvalid)) {
			pmap.put("isvalid", isvalid);
			map.put("isvalid", isvalid);
		}
		if (StringUtils.isNotBlank(acctType)) {
			pmap.put("acctType", acctType);
			map.put("acctType", acctType);
		}

		int totalRecordCount = acctManager.count(pmap);
		List<Acct> list = null;
		if (totalRecordCount > 0) {
			list = acctManager.list(pmap, Integer.parseInt(page),
					PaginationUtil.PAGE_SIZE);
			map.put("list", list);
			map.put("page", Integer.parseInt(page));
			map.put("totalRecordCount", totalRecordCount);
		}

		return "acct/list";
	}

	@RequestMapping(value = "exportExcel.act")
	public void exportExcel(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("导出帐号列表到EXCEL请求=" + ServletUtil.herf(request));

		String uid = request.getParameter("uid");
		String phone = request.getParameter("phone");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String agent = request.getParameter("agent");
		String taskname = request.getParameter("taskname");
		String first = request.getParameter("first");
		String isvalid = request.getParameter("isvalid");
		String acctType = request.getParameter("acctType");

		String filename = "acct_info_" + SequenceUtil.id() + ".xls";
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			response.setHeader("Content-disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			response.setContentType("application/msexcel;charset=UTF-8");
		} catch (IOException e) {
			log.error("导出帐号列表异常！");
		}

		Map<String, Object> pmap = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(uid)) {
			pmap.put("uid", uid);
			map.put("uid", uid);
		}
		if (StringUtils.isNotBlank(phone)) {
			pmap.put("phone", phone);
			map.put("phone", phone);
		}
		if (StringUtils.isNotBlank(startTime)) {
			pmap.put("startTime", startTime);
			map.put("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			pmap.put("endTime", endTime);
			map.put("endTime", endTime);
		}
		if (StringUtils.isNotBlank(agent)) {
			pmap.put("agent", agent);
			map.put("agent", agent);
		}
		if (StringUtils.isNotBlank(taskname)) {
			pmap.put("taskname", taskname);
			map.put("taskname", taskname);
		}
		if (StringUtils.isNotBlank(first)) {
			pmap.put("first", first);
			map.put("first", first);
		}
		if (StringUtils.isNotBlank(isvalid)) {
			pmap.put("isvalid", isvalid);
			map.put("isvalid", isvalid);
		}
		if (StringUtils.isNotBlank(acctType)) {
			pmap.put("acctType", acctType);
			map.put("acctType", acctType);
		}

		HSSFWorkbook wb = acctManager.exportExcel(pmap);

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

	@RequestMapping(value = "toEditAcct.act")
	public String toEditAcct(ModelMap map, HttpServletRequest request) {
		log.info("跳转到编辑账户页面：" + ServletUtil.herf(request));

		String uid = request.getParameter("uid");
		String brandId = request.getParameter("brandId");

		if (StringUtils.isBlank(uid) || StringUtils.isBlank(brandId)) {
			log.info("编辑账户，参数错误");
			return "error";
		}

		// map.put("list", agentManager.list());
		// map.put("goodslist", goodsManager.list());

		map.put("acct", acctManager.selectAcct(brandId, uid));

		return "acct/editAcct";
	}

	@RequestMapping(value = "editAcct.act")
	public void editAcct(HttpServletResponse response,
			HttpServletRequest request) {
		log.info("编辑保存帐号请求=" + ServletUtil.herf(request));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("保存帐号异常！");
		}

		String uid = request.getParameter("uid");
		String brandId = request.getParameter("brandId");
		String pwd = request.getParameter("pwd");
		String balance = request.getParameter("balance");
		String phone = request.getParameter("phone");
		String enableFlag = request.getParameter("enableFlag");
		String validDate = request.getParameter("validDate");
		String bindLimit = request.getParameter("bindLimit");

		if (StringUtils.isBlank(uid) || StringUtils.isBlank(brandId)
				|| StringUtils.isBlank(pwd) || StringUtils.isBlank(balance)
				|| StringUtils.isBlank(phone)
				|| StringUtils.isBlank(enableFlag)
				|| StringUtils.isBlank(validDate)
				|| StringUtils.isBlank(bindLimit)) {
			log.info("编辑账户，参数错误");
			out.print("编辑账户，参数错误，请联系管理员！");
			return;
		}

		try {
			int i = acctManager.editAcct(pwd, Long.parseLong(balance), phone,
					enableFlag, validDate, Integer.parseInt(bindLimit),
					brandId, uid);

			if (1 == i) {
				log.info("编辑账户成功");
				out.print("编辑账户成功！");
			} else {
				log.info("编辑账户失败");
				out.print("编辑账户失败！");
			}
		} catch (Exception e) {
			log.info("编辑账户失败", e);
			out.print("编辑账户失败，请联系管理员！");
		}

	}

}
