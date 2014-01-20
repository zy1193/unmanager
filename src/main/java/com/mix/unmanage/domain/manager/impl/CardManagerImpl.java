package com.mix.unmanage.domain.manager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keicei.util.PaginationUtil;
import com.keicei.util.SequenceUtil;
import com.mix.unmanage.domain.entity.Card;
import com.mix.unmanage.domain.manager.CardManager;
import com.mix.unmanage.persistence.mapper.CardMapper;

@Service("cardManager")
public class CardManagerImpl implements CardManager {

	@Resource
	private CardMapper cardMapper;

	@Transactional(readOnly = true)
	@Override
	public int count(Map<String, Object> map) {

		return cardMapper.count(map);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Card> list(Map<String, Object> map, int page, int pageSize) {
		PaginationUtil.generatePageParameter(map, page, pageSize);
		return cardMapper.list(map);
	}

	@Transactional(readOnly = true)
	@Override
	public HSSFWorkbook exportExcel(Map<String, Object> map) {
		List<Card> list = cardMapper.list(map);

		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFRow row = null;
		HSSFSheet sheet = null;
		for (int i = 0, j = 0; i < list.size(); i++, j++) {
			if (0 == j || j % 50001 == 0) {
				j = 1;
				sheet = getSheet(wb);
			}
			row = sheet.createRow(j);
			Card card = list.get(i);
			row.createCell(0).setCellValue(card.getCardno());
			row.createCell(1).setCellValue(card.getCardpwd());
			row.createCell(2).setCellValue(card.getGoodsName());
			row.createCell(3).setCellValue(card.getAgent());
			String cardStatus = card.getStatus();
			if ("n".equals(cardStatus)) {
				row.createCell(4).setCellValue("已使用");
			} else {
				row.createCell(4).setCellValue("未使用");
			}
			row.createCell(5).setCellValue(card.getCtime());
			row.createCell(6).setCellValue(card.getMtime());
			row.createCell(7).setCellValue(card.getEndTime());
			row.createCell(8).setCellValue(card.getTaskid());
		}

		return wb;
	}

	public HSSFSheet getSheet(HSSFWorkbook wb) {
		HSSFSheet sheet = wb.createSheet("充值卡信息" + SequenceUtil.id());
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("卡号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("密码");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("商品");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("代理商");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("卡状态");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("生成日期");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("使用日期");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("到期日期");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("批次");
		cell.setCellStyle(style);

		return sheet;
	}

	@Transactional
	@Override
	public int deleteCard(Map<String, Object> map) {
		return cardMapper.deleteCard(map);
	}

}
