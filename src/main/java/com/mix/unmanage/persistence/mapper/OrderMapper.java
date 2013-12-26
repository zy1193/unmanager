package com.mix.unmanage.persistence.mapper;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.Order;

public interface OrderMapper {

	/**
	 * 获取充值卡充值相关列表
	 * 
	 * @param map
	 * @return
	 */
	List<Order> list4Card(Map<String, Object> map);
}
