/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.controller;

import com.wzh.datacenter.entity.Manager;
import com.wzh.datacenter.service.ManagerService;
import com.wzh.datacenter.util.CollectionUtil;
import com.wzh.datacenter.util.Common;
import com.wzh.datacenter.util.MapUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wzh
 * @since 2019-10-01
 */
@RestController
@RequestMapping("manager")
public class ManagerController {

	@Resource(name = "managerServiceImpl")
	private ManagerService managerService;

	/**
	 * map工具类
	 */
	private static final MapUtils<Manager> mapUtils = new MapUtils<>();

	/**
	 * 添加管理员
	 * @param manager
	 * @return
	 */
	@PostMapping("/addManager")
	public int addManager(Manager manager) {
		return managerService.addManager(mapUtils.toMap(manager));
	}

	/**
	 * 修改管理员
	 * @param manager
	 * @return
	 */
	@PostMapping("/updateManager")
	public int updateManager(Manager manager) {
		return managerService.updateManager(mapUtils.toMap(manager));
	}

	/**
	 * 管理员登录
	 * @param manager
	 * @return
	 */
	@PostMapping("/login")
	public int login(Manager manager) {
		List<Manager> list = managerService.login(mapUtils.toMap(manager));
		return CollectionUtil.isNotEmpty(list) ? Common.SELECT_SUCCESS : Common.SELECT_FAIL;
	}



}
