package com.mix.unmanage.domain.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keicei.util.PaginationUtil;
import com.mix.unmanage.domain.entity.Ad;
import com.mix.unmanage.domain.manager.AdManager;
import com.mix.unmanage.persistence.mapper.AdMapper;

@Service("adManager")
public class AdManagerImpl implements AdManager {

	// private static final Logger log = Logger.getLogger(AdManagerImpl.class);

	@Resource
	private AdMapper adMapper;

	@Transactional(readOnly = true)
	@Override
	public int count(Map<String, Object> map) {
		return adMapper.count(map);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Ad> list(Map<String, Object> map, int page, int pageSize) {
		PaginationUtil.generatePageParameter(map, page, pageSize);
		return adMapper.list(map);
	}

	@Transactional
	@Override
	public int deleteAd(Map<String, Object> map) {
		return adMapper.deleteAd(map);
	}

	@Transactional
	@Override
	public int editAd(int id, String brandid, String adpid, String name,
			String url, String img) {
		Ad ad = new Ad();
		ad.setId(id);
		ad.setAdpid(adpid);
		ad.setBrandid(brandid);
		ad.setImg(img);
		ad.setName(name);
		ad.setUrl(url);

		return adMapper.editAd(ad);
	}

	@Transactional(readOnly = true)
	@Override
	public Ad selectAd(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return adMapper.selectAd(map);
	}

	@Transactional
	@Override
	public int insert(Ad ad) {
		return adMapper.insert(ad);
	}

}
