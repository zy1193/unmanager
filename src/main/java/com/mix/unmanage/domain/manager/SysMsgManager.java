package com.mix.unmanage.domain.manager;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.SysMsg;

public interface SysMsgManager {

	int count(Map<String, Object> map);

	List<SysMsg> list(Map<String, Object> map, int page, int pageSize);

	int updateSysMsg(String brandId, String title, String msg, String ctime,
			int id);

	int addSysMsg(String brandId, String title, String msg, String ctime);

	int delSysMsg(int id);

	SysMsg getSysMsgInfo(int id);
}
