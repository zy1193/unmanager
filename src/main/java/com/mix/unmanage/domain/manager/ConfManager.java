package com.mix.unmanage.domain.manager;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.Conf;

public interface ConfManager {

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
	List<Conf> list(Map<String, Object> map, int page, int pageSize);

	/**
	 * 查找单个广告信息
	 * 
	 * @param id
	 * @return
	 */
	Conf selectConf(int id);

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
	int editConf(int id, String value);

}
