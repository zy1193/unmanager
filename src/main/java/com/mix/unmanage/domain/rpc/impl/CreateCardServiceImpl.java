package com.mix.unmanage.domain.rpc.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.keicei.util.HttpUtil;
import com.mix.unmanage.common.GlobalConfig;
import com.mix.unmanage.domain.entity.CreateCardRes;
import com.mix.unmanage.domain.rpc.CreateCardService;

@Service("createCardService")
public class CreateCardServiceImpl implements CreateCardService {

	private static final Logger log = Logger
			.getLogger(CreateCardServiceImpl.class);

	@Resource
	private GlobalConfig globalConfig;

	@Override
	public boolean createCard(String brandid, String goodsId, String agent,
			String endTime, String number) {
		String url = globalConfig.get("YX_EXTRACT_CARD_URL");

		String furl;
		try {
			furl = String.format(url, brandid, goodsId, agent,
					URLEncoder.encode(endTime, "UTF-8"), number);
		} catch (UnsupportedEncodingException e1) {
			log.info("提取充值卡异常：", e1);
			return false;
		}

		log.info("提取充值卡的url：" + furl);
		String rsp;
		try {
			rsp = HttpUtil.get(furl);
			log.info("提取充值卡返回：" + rsp);
		} catch (Exception e) {
			log.info("提取充值卡异常：", e);
			return false;
		}

		/*** 验证结果 */
		Gson gosn = new Gson();
		CreateCardRes card = gosn.fromJson(rsp, CreateCardRes.class);

		if (0 == card.getResult()) {
			log.info("提取充值卡成功。");
			return true;
		} else {
			log.info("提取充值卡失败。");
			return false;
		}
	}
}
