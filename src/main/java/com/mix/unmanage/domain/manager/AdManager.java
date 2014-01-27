package com.mix.unmanage.domain.manager;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.Ad;

public interface AdManager {

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
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<Ad> list(Map<String, Object> map, int page, int pageSize);

	/**
	 * 查找单个广告信息
	 * 
	 * @param id
	 * @return
	 */
	Ad selectAd(int id);

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
	 * @param id
	 * @param adpid
	 * @param name
	 * @param url
	 * @param img
	 * @return
	 */
	int editAd(int id, String brandid, String adpid, String name, String url,
			String img);

}
