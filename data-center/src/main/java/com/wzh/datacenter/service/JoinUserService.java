/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.service;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-01
 */
public interface JoinUserService {

	/**
	 * 添加参与人员
	 * @param paraMap
	 * @return
	 */
	int addJoinUser(Map<String, Object> paraMap);

	/**
	 * 更新参与人员信息
	 * @param paraMap
	 * @return
	 */
	int updateJoinUser(Map<String, Object> paraMap);

}
