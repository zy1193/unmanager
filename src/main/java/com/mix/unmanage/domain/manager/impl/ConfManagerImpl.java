package com.mix.unmanage.domain.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keicei.util.PaginationUtil;
import com.mix.unmanage.domain.entity.Conf;
import com.mix.unmanage.domain.manager.ConfManager;
import com.mix.unmanage.persistence.mapper.ConfMapper;

@Service("confManager")
public class ConfManagerImpl implements ConfManager {

	@Resource
	private ConfMapper confMapper;

	@Transactional(readOnly = true)
	@Override
	public int count(Map<String, Object> map) {
		return confMapper.count(map);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Conf> list(Map<String, Object> map, int page, int pageSize) {
		PaginationUtil.generatePageParameter(map, page, pageSize);
		return confMapper.list(map);
	}

	@Transactional(readOnly = true)
	@Override
	public Conf selectConf(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return confMapper.selectConf(map);
	}

	@Transactional
	@Override
	public int editConf(int id, String value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("value", value);
		return confMapper.editConf(map);
	}

}
