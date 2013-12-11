package com.mix.unmanage.domain.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.unmanage.domain.entity.Goods;
import com.mix.unmanage.domain.manager.GoodsManager;
import com.mix.unmanage.persistence.mapper.GoodsMapper;

@Service("goodsManager")
public class GoodsManagerImpl implements GoodsManager {

	private static final Logger log = Logger.getLogger(GoodsManagerImpl.class);

	@Resource
	private GoodsMapper goodsMapper;

	@Transactional(readOnly = true)
	@Override
	public List<Goods> list() {

		log.info("查询商品信息：");

		return goodsMapper.list();
	}

}
