package com.mix.unmanage.domain.rpc;


public interface CreateCardService {

	/***
	 * 生成充值卡
	 * 
	 * @param brandid
	 * @param goodsId
	 * @param agent
	 * @param endTime
	 * @param number
	 * @return
	 */
	boolean createCard(String brandid, String goodsId, String agent,
			String endTime, String number);
	
}
