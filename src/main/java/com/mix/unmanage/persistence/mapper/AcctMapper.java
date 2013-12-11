package com.mix.unmanage.persistence.mapper;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.Acct;

public interface AcctMapper {

	int count(Map<String, Object> map);

	List<Acct> list(Map<String, Object> map);

	/**
	 * 转移帐号
	 * 
	 * @param map
	 * @return
	 */
	int transferAcct(Map<String, Object> map);

	/***
	 * 删除帐号信息
	 * 
	 * @param map
	 * @return
	 */
	int deleteAcct(Map<String, Object> map);

	/**
	 * 查找帐号
	 * 
	 * @param map
	 * @return
	 */
	Acct selectAcct(Map<String, Object> map);

	/**
	 * 编辑帐号
	 * 
	 * @param map
	 * @return
	 */
	int editAcct(Map<String, Object> map);
}
