package com.mix.unmanage.persistence.mapper;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.Ad;

public interface AdMapper {
	
	int insert(Ad ad);

	/**
	 * 查询条数
	 * 
	 * @param map
	 * @return
	 */
	int count(Map<String, Object> map);

	/**
	 * 获取list
	 * 
	 * @param map
	 * @return
	 */
	List<Ad> list(Map<String, Object> map);

	/**
	 * 查找单个广告信息
	 * 
	 * @param id
	 * @return
	 */
	Ad selectAd(Map<String, Object> map);

	/***
	 * 删除
	 * 
	 * @param map
	 * @return
	 */
	int deleteAd(Map<String, Object> map);

	/***
	 * 编辑
	 * 
	 * @param Ad
	 * @return
	 */
	int editAd(Ad ad);
}
