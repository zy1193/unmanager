package com.mix.unmanage.domain.manager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mix.unmanage.domain.entity.Order;
import com.mix.unmanage.domain.manager.OrderManager;
import com.mix.unmanage.persistence.mapper.OrderMapper;

@Service("orderManager")
public class OrderManagerImpl implements OrderManager {

	//	private static final Logger log = Logger.getLogger(OrderManagerImpl.class);

	@Resource
	private OrderMapper orderMapper;

	@Override
	public List<Order> list4Card(Map<String, Object> map) {
		return orderMapper.list4Card(map);
	}

}
