package com.mix.unmanage.domain.manager.impl;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.unmanage.common.Const;
import com.mix.unmanage.domain.entity.Tuser;
import com.mix.unmanage.domain.manager.TuserManager;
import com.mix.unmanage.domain.repository.TuserRepository;
import com.mix.unmanage.persistence.mapper.TuserMapper;

@Service("tuserManager")
public class TuserManagerImpl implements TuserManager {

	private static final Logger log = Logger.getLogger(TuserManagerImpl.class);

	@Resource
	private TuserRepository tuserRepository;

	@Resource
	private TuserMapper tuserMapper;

	@Transactional(readOnly = true)
	@Override
	public boolean checkUser(Tuser user, HttpServletRequest request) {

		try {

			Tuser t = tuserRepository.getTuser4Uid(user.getUid());
			if (t == null) {
				return false;
			}
			String sign = DigestUtils.md5Hex(user.getPwd().getBytes(
					Const.CHARSET));

			if (!sign.equals(t.getPwd())) {
				return false;
			}
			HttpSession session = request.getSession();
			session.setAttribute(Const.USER_SESSION_NAME, t);

			return true;
		} catch (UnsupportedEncodingException e) {
			log.error("验证登录用户异常！");
			return false;
		}
	}

	/**
	 * 刷新缓存用户，避免重启服务
	 */
	@Override
	public boolean reloadUser() {
		return tuserRepository.reload();
	}

	@Transactional(readOnly = true)
	@Override
	public Tuser selectUser(String uid) {
		return tuserMapper.selectUser(uid);
	}

	@Transactional
	@Override
	public void updateUser(Tuser user) {
		tuserMapper.updateUser(user);
	}

}
