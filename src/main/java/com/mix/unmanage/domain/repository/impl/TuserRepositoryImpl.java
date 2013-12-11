package com.mix.unmanage.domain.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mix.unmanage.domain.entity.Tuser;
import com.mix.unmanage.domain.repository.TuserRepository;
import com.mix.unmanage.persistence.mapper.TuserMapper;

@Repository("tuserRepository")
public class TuserRepositoryImpl implements TuserRepository {

	@Resource
	private TuserMapper tuserMapper;

	private List<Tuser> userList = new ArrayList<Tuser>();

	private Map<String, Tuser> usermap4uid = new HashMap<String, Tuser>();

	@PostConstruct
	@Transactional(readOnly = true)
	public void loadTuser() {

		userList = tuserMapper.list();
		for (Tuser user : userList) {
			usermap4uid.put(user.getUid(), user);
		}
	}

	@Override
	public Tuser getTuser4Uid(String uid) {
		return usermap4uid.get(uid);
	}

	@Override
	public List<Tuser> userList4role(String role) {
		return null;
	}

	@Override
	public boolean checkTuser(Tuser tuser) {
		return false;
	}

	@Override
	public boolean reload() {
		try {
			loadTuser();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
