package com.mix.unmanage.persistence.mapper;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.Card;

public interface CardMapper {

	int count(Map<String, Object> map);

	List<Card> list(Map<String, Object> map);
	
	/***
	 * 删除充值卡
	 * 
	 * @param map
	 * @return
	 */
	int deleteCard(Map<String, Object> map);

}
