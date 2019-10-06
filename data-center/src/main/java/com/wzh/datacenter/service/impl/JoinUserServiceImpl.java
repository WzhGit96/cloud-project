/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.service.impl;

import com.wzh.datacenter.dao.JoinUserMapper;
import com.wzh.datacenter.service.JoinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-01
 */
@Service
public class JoinUserServiceImpl implements JoinUserService {

	@Autowired
	private JoinUserMapper joinUserMapper;

	@Override
	public int addJoinUser(Map<String, Object> paraMap) {
		return joinUserMapper.addJoinUser(paraMap);
	}

	@Override
	public int updateJoinUser(Map<String, Object> paraMap) {
		return joinUserMapper.updateJoinUser(paraMap);
	}
}
