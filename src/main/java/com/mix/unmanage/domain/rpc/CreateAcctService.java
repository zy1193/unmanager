package com.mix.unmanage.domain.rpc;

public interface CreateAcctService {

	/**
	 * 创建包月帐号
	 * 
	 * @param brandid
	 * @param pwd
	 * @param number
	 * @param calltime
	 * @param agent
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	boolean createTimeAcct(String brandid, String number, String agent,
			String year, String month, String day, String acctType,
			String goodsId, String bindLimit);

	/**
	 * 创建带余额的帐号
	 * 
	 * @param brandid
	 * @param pwd
	 * @param number
	 * @param money
	 * @param year
	 * @param month
	 * @param day
	 * @param agent
	 * @return
	 */
	boolean createMoneyAcct(String brandid, String number, String money,
			String year, String month, String day, String agent);
}
