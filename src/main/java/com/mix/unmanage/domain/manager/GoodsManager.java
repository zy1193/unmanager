package com.mix.unmanage.domain.manager;

import java.util.List;

import com.mix.unmanage.domain.entity.Goods;

public interface GoodsManager {

	/**
	 * 查询商品列表
	 * 
	 * @param category
	 * @return
	 */
	List<Goods> list();
}
