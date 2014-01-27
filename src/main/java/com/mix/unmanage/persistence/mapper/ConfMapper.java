package com.mix.unmanage.persistence.mapper;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.Conf;

public interface ConfMapper {
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
	List<Conf> list(Map<String, Object> map);

	/**
	 * 查找单个广告信息
	 * 
	 * @param id
	 * @return
	 */
	Conf selectConf(Map<String, Object> map);

	/***
	 * 编辑
	 * 
	 * @param Ad
	 * @return
	 */
	int editConf(Map<String, Object> map);
}
