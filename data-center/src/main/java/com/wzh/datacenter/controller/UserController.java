/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.datacenter.entity.User;
import com.wzh.datacenter.service.UserService;
import com.wzh.datacenter.util.CollectionUtil;
import com.wzh.datacenter.util.Common;
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
	private static final MapUtils<User> mapUtils = new MapUtils();


	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public int login(User user) {
		List<User> result = userService.login(mapUtils.toMap(user));
		return CollectionUtil.isNotEmpty(result) ? Common.SELECT_SUCCESS : Common.SELECT_FAIL;
	}

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@PostMapping("/addUser")
	public int addUser(User user) {
		// TODO 不能有重复的account和email
		return userService.addUser(mapUtils.toMap(user));
	}

	/**
	 * 分页查询用户
	 * @param user
	 * @return
	 */
	@PostMapping("/findAllUserByPage")
	public PageInfo<User> findAllUserByPage(User user) {
		PageHelper.startPage((user.getPageNo()-1)*user.getPageSize(), user.getPageSize());
		List<User> list = userService.findAllUserByPage(mapUtils.toMap(user));
		PageInfo<User> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	@PostMapping("/updateUser")
	public int updateUser(User user) {
		return userService.updateUser(mapUtils.toMap(user));
	}

	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	@PostMapping("/deleteUser")
	public int deleteUser(User user) {
		return userService.deleteUser(mapUtils.toMap(user));
	}




}
