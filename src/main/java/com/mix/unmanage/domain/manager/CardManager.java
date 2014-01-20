package com.mix.unmanage.domain.manager;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mix.unmanage.domain.entity.Card;

public interface CardManager {

	int count(Map<String, Object> map);

	List<Card> list(Map<String, Object> map, int page, int pageSize);

	HSSFWorkbook exportExcel(Map<String, Object> map);
	
	int deleteCard(Map<String, Object> map);
}
