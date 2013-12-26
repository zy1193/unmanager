package com.mix.unmanage.domain.entity;

public class PayRes {

	int code;
	String orderid, msg, pay_res;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPay_res() {
		return pay_res;
	}

	public void setPay_res(String pay_res) {
		this.pay_res = pay_res;
	}
}
