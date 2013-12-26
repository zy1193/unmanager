package com.mix.unmanage.domain.manager;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.Order;

public interface OrderManager {

	/**
	 * 获取充值卡充值相关列表
	 * 
	 * @param map
	 * @return
	 */
	List<Order> list4Card(Map<String, Object> map);
}
