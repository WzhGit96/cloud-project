/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.service.impl;

import com.wzh.datacenter.dao.VoteMapper;
import com.wzh.datacenter.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteMapper voteMapper;

	@Override
	public int addVote(Map<String, Object> paraMap) {
		return voteMapper.addVote(paraMap);
	}

	@Override
	public int updateVote(Map<String, Object> paraMap) {
		return voteMapper.updateVote(paraMap);
	}
}
