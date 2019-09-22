/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.service;

import com.wzh.datacenter.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author Wzh
 * @since 2019-9-22
 */
public interface UserService {

	/**
	 * 添加用户
	 * @param paraMap
	 * @return
	 */
	int addUser(Map<String, Object> paraMap);

	/**
	 * 分页查询用户
	 * @param paraMap
	 * @return
	 */
	List<User> findAllUserByPage(Map<String, Object> paraMap);

}
