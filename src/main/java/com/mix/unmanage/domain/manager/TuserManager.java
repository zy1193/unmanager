package com.mix.unmanage.domain.manager;

import javax.servlet.http.HttpServletRequest;

import com.mix.unmanage.domain.entity.Tuser;

public interface TuserManager {

	boolean checkUser(Tuser user,HttpServletRequest request);
	
	boolean reloadUser();
	
	Tuser selectUser(String uid);
	
	void updateUser(Tuser user);
}
