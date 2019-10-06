/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.controller;

import com.wzh.datacenter.entity.Vote;
import com.wzh.datacenter.service.VoteService;
import com.wzh.datacenter.util.MapUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Wzh
 * @since 2019-10-01
 */
@RestController
@RequestMapping("/vote")
public class VoteController {

	@Resource(name = "voteServiceImpl")
	private VoteService voteService;

	private MapUtils<Vote> mapUtils = new MapUtils<>();

	/**
	 * 添加投票
	 * @param vote
	 * @return
	 */
	@PostMapping("/addVote")
	public int addVote(Vote vote) {
		return voteService.addVote(mapUtils.toMap(vote));
	}

	/**
	 * 更新投票信息
	 * @param vote
	 * @return
	 */
	@PostMapping("/updateVote")
	public int updateVote(Vote vote) {
		return voteService.updateVote(mapUtils.toMap(vote));
	}

}
