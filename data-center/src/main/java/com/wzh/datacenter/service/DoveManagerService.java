/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.service;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-01
 */
public interface DoveManagerService {

	/**
	 * 添加鸽子管理
	 * @param paraMap
	 * @return
	 */
	int addDove(Map<String, Object> paraMap);

	/**
	 * 修改鸽子管理信息
	 * @param paraMap
	 * @return
	 */
	int updateDove(Map<String, Object> paraMap);

}
