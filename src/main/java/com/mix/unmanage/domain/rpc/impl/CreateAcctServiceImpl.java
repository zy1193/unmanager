package com.mix.unmanage.domain.rpc.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.keicei.util.HttpUtil;
import com.mix.unmanage.common.GlobalConfig;
import com.mix.unmanage.domain.entity.CreateAcctRes;
import com.mix.unmanage.domain.rpc.CreateAcctService;

@Service("createAcctService")
public class CreateAcctServiceImpl implements CreateAcctService {

	private static final Logger log = Logger
			.getLogger(CreateAcctServiceImpl.class);

	@Resource
	private GlobalConfig globalConfig;

	@Override
	public boolean createTimeAcct(String brandid, String number, String agent,
			String year, String month, String day, String acctType,
			String goodsId, String bindLimit, String remarks) {
		String url = globalConfig.get("YX_EXTRACT_TIME_ACCT_URL");

		String furl = "";
		try {
			furl = String.format(url, brandid, number, agent, year, month, day,
					acctType, goodsId, bindLimit,
					URLEncoder.encode(remarks, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			log.info("创建包月账户编码异常：", e1);
		}

		log.info("创建包月账户的url：" + furl);
		String rsp;
		try {
			rsp = HttpUtil.get(furl);
			log.info("创建包月账户返回：" + rsp);
		} catch (Exception e) {
			log.info("创建包月账户异常：", e);
			return false;
		}

		Gson gosn = new Gson();
		CreateAcctRes createAcctRes = gosn.fromJson(rsp, CreateAcctRes.class);

		if (0 == createAcctRes.getCode()) {
			log.info("创建包月账户成功。");
			return true;
		} else {
			log.info("创建包月账户失败。");
			return false;
		}

	}

	@Override
	public boolean createMoneyAcct(String brandid, String number, String money,
			String year, String month, String day, String agent, String remarks) {
		String url = globalConfig.get("YX_EXTRACT_MONEY_ACCT_URL");

		String furl = "";
		try {
			furl = String.format(url, brandid, number, money, year, month, day,
					agent, URLEncoder.encode(remarks, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			log.info("创建余额账户编码异常：", e1);
		}

		log.info("创建余额账户的url：" + furl);
		String rsp;
		try {
			rsp = HttpUtil.get(furl);
			log.info("创建余额账户返回：" + rsp);
		} catch (Exception e) {
			log.info("创建余额账户异常：", e);
			return false;
		}

		Gson gosn = new Gson();
		CreateAcctRes createAcctRes = gosn.fromJson(rsp, CreateAcctRes.class);

		if (0 == createAcctRes.getCode()) {
			log.info("创建余额账户成功。");
			return true;
		} else {
			log.info("创建余额账户失败。");
			return false;
		}

	}

}
