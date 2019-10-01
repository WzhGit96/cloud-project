/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.service.impl;

import com.wzh.datacenter.dao.ManagerMapper;
import com.wzh.datacenter.entity.Manager;
import com.wzh.datacenter.service.ManagerService;
import com.wzh.datacenter.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-01
 */
@Service
public class ManagerServiceImpl implements ManagerService{

	@Autowired
	private ManagerMapper managerMapper;

	@Override
	public int addManager(Map<String, Object> map) {
		int result = managerMapper.addManager(map);
		return result;
	}

	@Override
	public int updateManager(Map<String, Object> map) {
		int result = managerMapper.updateManager(map);
		return result;
	}

	@Override
	public List<Manager> login(Map<String, Object> map) {
		List<Manager> list = managerMapper.login(map);
		return CollectionUtil.isNotEmpty(list) ? list : new ArrayList<>();
	}
}
