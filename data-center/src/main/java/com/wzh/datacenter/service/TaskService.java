/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.service;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-1
 */
public interface TaskService {

	/**
	 * 添加协议
	 *
	 * @param map
	 * @return
	 */
	int addTask(Map<String, Object> map);

	/**
	 * 修改协议信息
	 *
	 * @param map
	 * @return
	 */
	int updateTask(Map<String, Object> map);

}
