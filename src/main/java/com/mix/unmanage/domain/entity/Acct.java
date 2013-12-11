package com.mix.unmanage.domain.entity;

public class Acct {

	long acctId, uid, balance, giftbalance, Reserve1;
	String brandId, pwd, md5pwd, phone, enableFlag, createTime, validDate,
			giftValidDate, Reserve2, agent, first, taskname, acctType, goodsId,
			bindLimit;

	public long getAcctId() {
		return acctId;
	}

	public void setAcctId(long acctId) {
		this.acctId = acctId;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public long getGiftbalance() {
		return giftbalance;
	}

	public void setGiftbalance(long giftbalance) {
		this.giftbalance = giftbalance;
	}

	public long getReserve1() {
		return Reserve1;
	}

	public void setReserve1(long reserve1) {
		Reserve1 = reserve1;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMd5pwd() {
		return md5pwd;
	}

	public void setMd5pwd(String md5pwd) {
		this.md5pwd = md5pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getGiftValidDate() {
		return giftValidDate;
	}

	public void setGiftValidDate(String giftValidDate) {
		this.giftValidDate = giftValidDate;
	}

	public String getReserve2() {
		return Reserve2;
	}

	public void setReserve2(String reserve2) {
		Reserve2 = reserve2;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getBindLimit() {
		return bindLimit;
	}

	public void setBindLimit(String bindLimit) {
		this.bindLimit = bindLimit;
	}
}
