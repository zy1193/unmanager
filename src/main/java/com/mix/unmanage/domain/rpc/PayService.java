package com.mix.unmanage.domain.rpc;

import com.mix.unmanage.domain.entity.PayRes;

public interface PayService {

	PayRes pay4Offline(String brandid, String uid, String cno, String cps,
			String goodsid, String paytype);
}
