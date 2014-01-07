package com.mix.unmanage.persistence.mapper;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.SysMsg;

public interface SysMsgMapper {

	int count(Map<String, Object> map);

	List<SysMsg> list(Map<String, Object> map);

	int updateSysMsg(SysMsg sysMsg);

	int addSysMsg(SysMsg sysMsg);

	int delSysMsg(int id);

	SysMsg getSysMsgInfo(int id);
}
