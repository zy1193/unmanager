package com.mix.unmanage.domain.repository;

import java.util.List;

import com.mix.unmanage.domain.entity.Tuser;

public interface TuserRepository {

	/**
	 * 根据uid获得用户信息
	 * 
	 * @param uid
	 * @return
	 */
	Tuser getTuser4Uid(String uid);

	/**
	 * 根据角色获得用户组
	 * 
	 * @param role
	 * @return
	 */
	List<Tuser> userList4role(String role);

	/****
	 * 检查用户存不存在
	 * 
	 * @param tuser
	 * @return
	 */
	boolean checkTuser(Tuser tuser);

	/**
	 * 重新加载数据库的用户
	 * 
	 * @return
	 */
	boolean reload();
}
