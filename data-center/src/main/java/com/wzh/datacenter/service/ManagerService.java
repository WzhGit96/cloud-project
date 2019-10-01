/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.service;

import com.wzh.datacenter.entity.Manager;

import java.util.List;
import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-01
 */
public interface ManagerService {

	/**
	 * 添加管理员
	 * @param map
	 * @return
	 */
	int addManager(Map<String, Object> map);

	/**
	 * 修改管理员信息
	 * @param map
	 * @return
	 */
	int updateManager(Map<String, Object> map);

	/**
	 * 管理员登录
	 * @param map
	 * @return
	 */
	List<Manager> login(Map<String, Object> map);

}
