package com.mix.unmanage.domain.rpc.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.keicei.util.HttpUtil;
import com.mix.unmanage.common.GlobalConfig;
import com.mix.unmanage.domain.entity.PayRes;
import com.mix.unmanage.domain.rpc.PayService;

@Service("payService")
public class PayServiceImpl implements PayService {

	private static final Logger log = Logger.getLogger(PayServiceImpl.class);

	@Resource
	private GlobalConfig globalConfig;

	@Override
	public PayRes pay4Offline(String brandid, String uid, String cno,
			String cps, String goodsid, String paytype) {
		String url = globalConfig.get("YX_OFFLINE_PAY_URL");

		// brandid=%s&uid=%s&goods_id=%s&paytype=%s&cno=%s&cps=%s
		String furl = String.format(url, brandid, uid, goodsid, paytype, cno,
				cps);

		PayRes payRes = new PayRes();

		log.info("线下卡充值的url：" + furl);
		String rsp;
		try {
			rsp = HttpUtil.get(furl);
			log.info("线下卡充值返回：" + rsp);
		} catch (Exception e) {
			log.info("线下卡充值异常：", e);
			payRes.setCode(-1);
			payRes.setMsg("线下卡充值异常");
			return payRes;
		}

		/*** 组织返回结果 */
		Gson gosn = new Gson();
		payRes = gosn.fromJson(rsp, PayRes.class);

		return payRes;
	}

}
