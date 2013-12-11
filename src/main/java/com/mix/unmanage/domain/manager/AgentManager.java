package com.mix.unmanage.domain.manager;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.Agent;

public interface AgentManager {

	int count(Map<String, Object> map);

	/**
	 * 查询代理商列表
	 * 
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<Agent> list(Map<String, Object> map, int page, int pageSize);

	/**
	 * 查询所有代理商
	 * 
	 * @return
	 */
	List<Agent> list();

	/**
	 * 增加代理商
	 * 
	 * @param agent
	 * @return
	 */
	int insert(Agent agent);
}
