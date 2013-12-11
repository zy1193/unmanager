package com.mix.unmanage.persistence.mapper;

import java.util.List;

import com.mix.unmanage.domain.entity.Tuser;

public interface TuserMapper {

	Tuser select(Tuser user);

	List<Tuser> list();

	Tuser selectUser(String uid);

	void updateUser(Tuser user);
}
