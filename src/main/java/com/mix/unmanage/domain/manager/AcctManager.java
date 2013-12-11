package com.mix.unmanage.domain.manager;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mix.unmanage.domain.entity.Acct;

public interface AcctManager {

	int count(Map<String, Object> map);

	List<Acct> list(Map<String, Object> map, int page, int pageSize);

	HSSFWorkbook exportExcel(Map<String, Object> map);

	int deleteAcct(Map<String, Object> map);

	/**
	 * 查找帐号
	 * 
	 * @param map
	 * @return
	 */
	Acct selectAcct(String brandId, String uid);

	/**
	 * 编辑帐号
	 * 
	 * @param map
	 * @return
	 */
	int editAcct(String pwd, long balance, String phone, String enableFlag,
			String validDate, int bindLimit, String brandId, String uid);

}
