package com.mix.unmanage.domain.manager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keicei.util.PaginationUtil;
import com.mix.unmanage.domain.entity.SysMsg;
import com.mix.unmanage.domain.manager.SysMsgManager;
import com.mix.unmanage.persistence.mapper.SysMsgMapper;

@Service("sysMsgManager")
public class SysMsgManagerImpl implements SysMsgManager {

	@Resource
	private SysMsgMapper sysMsgMapper;

	@Transactional(readOnly = true)
	@Override
	public int count(Map<String, Object> map) {
		return sysMsgMapper.count(map);
	}

	@Transactional(readOnly = true)
	@Override
	public List<SysMsg> list(Map<String, Object> map, int page, int pageSize) {
		PaginationUtil.generatePageParameter(map, page, pageSize);
		return sysMsgMapper.list(map);
	}

	@Transactional
	@Override
	public int updateSysMsg(String brandId, String title, String msg,
			String ctime, int id) {
		SysMsg sysMsg = new SysMsg();
		sysMsg.setId(id);
		sysMsg.setBrandId(brandId);
		sysMsg.setTitle(title);
		sysMsg.setMsg(msg);
		sysMsg.setCtime(ctime);

		return sysMsgMapper.updateSysMsg(sysMsg);
	}

	@Transactional
	@Override
	public int addSysMsg(String brandId, String title, String msg, String ctime) {
		SysMsg sysMsg = new SysMsg();
		sysMsg.setBrandId(brandId);
		sysMsg.setTitle(title);
		sysMsg.setMsg(msg);
		sysMsg.setCtime(ctime);
		return sysMsgMapper.addSysMsg(sysMsg);
	}

	@Transactional
	@Override
	public int delSysMsg(int id) {
		return sysMsgMapper.delSysMsg(id);
	}

	@Transactional(readOnly = true)
	@Override
	public SysMsg getSysMsgInfo(int id) {
		return sysMsgMapper.getSysMsgInfo(id);
	}

}
