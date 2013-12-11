package com.mix.unmanage.persistence.mapper;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.Agent;

public interface AgentMapper {

	int count(Map<String, Object> map);

	List<Agent> list(Map<String, Object> map);

	/**
	 * 增加代理商
	 * 
	 * @param agent
	 * @return
	 */
	int insert(Agent agent);
}
