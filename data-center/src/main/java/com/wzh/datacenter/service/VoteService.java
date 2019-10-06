/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.service;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-01
 */
public interface VoteService {

	/**
	 * 添加投票
	 * @param paraMap
	 * @return
	 */
	int addVote(Map<String, Object> paraMap);

	/**
	 * 修改投票信息
	 * @param paraMap
	 * @return
	 */
	int updateVote(Map<String, Object> paraMap);

}
