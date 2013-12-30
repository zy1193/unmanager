package com.mix.unmanage.domain.manager.impl;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keicei.util.PaginationUtil;
import com.keicei.util.SequenceUtil;
import com.mix.unmanage.common.Const;
import com.mix.unmanage.domain.entity.Acct;
import com.mix.unmanage.domain.manager.AcctManager;
import com.mix.unmanage.persistence.mapper.AcctMapper;

@Service("acctManager")
public class AcctManagerImpl implements AcctManager {

	private static final Logger log = Logger.getLogger(AcctManagerImpl.class);

	@Resource
	private AcctMapper acctMapper;

	@Transactional(readOnly = true)
	@Override
	public int count(Map<String, Object> map) {

		return acctMapper.count(map);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Acct> list(Map<String, Object> map, int page, int pageSize) {
		PaginationUtil.generatePageParameter(map, page, pageSize);
		return acctMapper.list(map);
	}

	@Transactional(readOnly = true)
	@Override
	public HSSFWorkbook exportExcel(Map<String, Object> map) {
		List<Acct> list = acctMapper.list(map);
		HSSFWorkbook wb = new HSSFWorkbook();
		DecimalFormat df = new DecimalFormat("#.##");
		HSSFRow row = null;
		HSSFSheet sheet = null;
		for (int i = 0, j = 0; i < list.size(); i++, j++) {
			if (0 == j || j % 50001 == 0) {
				j = 1;
				sheet = getSheet(wb);
			}
			row = sheet.createRow(j);
			row.createCell(0).setCellValue(list.get(i).getUid());
			row.createCell(1).setCellValue(list.get(i).getPwd());
			row.createCell(2).setCellValue(list.get(i).getPhone());
			row.createCell(3)
					.setCellValue(
							df.format(Double.parseDouble(list.get(i)
									.getBalance() + "") / 1000000));
			row.createCell(4).setCellValue(list.get(i).getCreateTime());
			row.createCell(5).setCellValue(list.get(i).getValidDate());
			row.createCell(6).setCellValue(list.get(i).getAgent());
			row.createCell(7).setCellValue(list.get(i).getTaskname());
		}

		return wb;
	}

	public HSSFSheet getSheet(HSSFWorkbook wb) {
		HSSFSheet sheet = wb.createSheet("帐号信息" + SequenceUtil.id());
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("帐号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("密码");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("手机");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("余额");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("创建日期");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("余额有效期");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("代理商");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("提号批次");
		cell.setCellStyle(style);

		return sheet;
	}

	@Transactional
	@Override
	public int deleteAcct(Map<String, Object> map) {
		int i = acctMapper.transferAcct(map);
		if (1 == i) {
			log.info("删除帐号，转移帐号成功，将执行删除！");
			return acctMapper.deleteAcct(map);
		} else {
			log.info("删除帐号，转移帐号失败，不执行删除！");
			return 0;
		}

	}

	@Transactional(readOnly = true)
	@Override
	public Acct selectAcct(String brandId, String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandId", brandId);
		map.put("uid", Long.parseLong(uid));
		return acctMapper.selectAcct(map);
	}

	@Transactional
	@Override
	public int editAcct(String pwd, long balance, String phone,
			String enableFlag, String validDate, int bindLimit, String brandId,
			String uid, String expTime, String remarks) {
		String md5pwd = "";
		try {
			md5pwd = DigestUtils.md5Hex(pwd.getBytes(Const.CHARSET));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandId", brandId);
		map.put("uid", Long.parseLong(uid));
		map.put("pwd", pwd);
		map.put("md5pwd", md5pwd);
		map.put("phone", Long.parseLong(phone));
		map.put("enableFlag", enableFlag);
		map.put("validDate", validDate);
		map.put("bindLimit", bindLimit);
		map.put("balance", balance * 10000);
		map.put("expTime", expTime);
		map.put("remarks", remarks);

		/** 更新账户信息 **/
		acctMapper.editAcct(map);

		if (StringUtils.isNotBlank(expTime)) {
			/** 更新账户包月信息 **/
			acctMapper.editTimeAcct(map);
		}

		return 1;
	}

}
