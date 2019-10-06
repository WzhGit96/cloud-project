/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.controller;

import com.wzh.datacenter.entity.JoinUser;
import com.wzh.datacenter.service.JoinUserService;
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
@RequestMapping("/joinUser")
public class JoinUserController {

	@Resource(name = "joinUserServiceImpl")
	private JoinUserService joinUserService;

	private MapUtils<JoinUser> mapUtils = new MapUtils<>();

	/**
	 * 添加参与人员
	 * @param joinUser
	 * @return
	 */
	@PostMapping("/addJoinUser")
	public int addJoinUser(JoinUser joinUser) {
		return joinUserService.addJoinUser(mapUtils.toMap(joinUser));
	}

	/**
	 * 更新参与人员信息
	 * @param joinUser
	 * @return
	 */
	@PostMapping("/updateJoinUser")
	public int updateJoinUser(JoinUser joinUser) {
		return joinUserService.updateJoinUser(mapUtils.toMap(joinUser));
	}

}
