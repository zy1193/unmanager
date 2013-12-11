package com.mix.unmanage.domain.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keicei.util.PaginationUtil;
import com.mix.unmanage.domain.entity.Agent;
import com.mix.unmanage.domain.manager.AgentManager;
import com.mix.unmanage.persistence.mapper.AgentMapper;

@Service("agentManager")
public class AgentManagerImpl implements AgentManager {

	// private static final Logger log =
	// Logger.getLogger(AgentManagerImpl.class);

	@Resource
	private AgentMapper agentMapper;

	@Transactional(readOnly = true)
	@Override
	public List<Agent> list(Map<String, Object> map, int page, int pageSize) {

		PaginationUtil.generatePageParameter(map, page, pageSize);
		return agentMapper.list(map);
	}

	@Transactional(readOnly = true)
	@Override
	public int count(Map<String, Object> map) {
		return agentMapper.count(map);
	}

	@Transactional
	@Override
	public int insert(Agent agent) {
		return agentMapper.insert(agent);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Agent> list() {
		Map<String, Object> map = new HashMap<String, Object>();
		return agentMapper.list(map);
	}

}
