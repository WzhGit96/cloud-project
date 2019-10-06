/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.service.impl;

import com.wzh.datacenter.dao.TaskMapper;
import com.wzh.datacenter.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-01
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskMapper taskMapper;

	@Override
	public int addTask(Map<String, Object> map) {
		return taskMapper.addTask(map);
	}

	@Override
	public int updateTask(Map<String, Object> map) {
		return taskMapper.updateTask(map);
	}
}
