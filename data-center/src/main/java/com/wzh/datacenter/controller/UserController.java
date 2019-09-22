/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.datacenter.entity.User;
import com.wzh.datacenter.service.UserService;
import com.wzh.datacenter.util.MapUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wzh
 * @since 2019-9-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource(name = "userServiceImpl")
	private UserService userService;

	/**
	 * map工具类
	 */
	private MapUtils mapUtils = new MapUtils();

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@PostMapping("/addUser")
	public int addUser(User user) {
		Map<String, Object> map = mapUtils.toMap(user);
		// TODO 不能有重复的account和email
		return userService.addUser(map);
	}

	/**
	 * 分页查询用户
	 * @param user
	 * @return
	 */
	@PostMapping("/findAllUserByPage")
	public PageInfo<User> findAllUserByPage(User user) {
		Map<String, Object> map = mapUtils.toMap(user);
		PageHelper.startPage((user.getPageNo()-1)*user.getPageSize(), user.getPageSize());
		List<User> list = userService.findAllUserByPage(map);
		PageInfo<User> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}


}
