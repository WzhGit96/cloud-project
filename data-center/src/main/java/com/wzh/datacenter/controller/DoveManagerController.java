/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.controller;

import com.wzh.datacenter.entity.DoveManager;
import com.wzh.datacenter.service.DoveManagerService;
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
@RequestMapping("/dove")
public class DoveManagerController {

	@Resource(name = "doveManagerServiceImpl")
	private DoveManagerService doveManagerService;

	private MapUtils<DoveManager> mapUtil = new MapUtils<>();

	/**
	 * 添加鸽子管理
	 * @param doveManager
	 * @return
	 */
	@PostMapping("/addDove")
	public int addDove(DoveManager doveManager) {
		return doveManagerService.addDove(mapUtil.toMap(doveManager));
	}

	/**
	 * 修改鸽子管理信息
	 * @param doveManager
	 * @return
	 */
	public int updateDove(DoveManager doveManager) {
		return doveManagerService.updateDove(mapUtil.toMap(doveManager));
	}

}
