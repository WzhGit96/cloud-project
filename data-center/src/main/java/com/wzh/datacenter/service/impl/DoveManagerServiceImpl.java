/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.service.impl;

import com.wzh.datacenter.dao.DoveManagerMapper;
import com.wzh.datacenter.service.DoveManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-01
 */
@Service
public class DoveManagerServiceImpl implements DoveManagerService {

	@Autowired
	private DoveManagerMapper doveManagerMapper;

	@Override
	public int addDove(Map<String, Object> paraMap) {
		return doveManagerMapper.addDove(paraMap);
	}

	@Override
	public int updateDove(Map<String, Object> paraMap) {
		return doveManagerMapper.updateDove(paraMap);
	}
}
