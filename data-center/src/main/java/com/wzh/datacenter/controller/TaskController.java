/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.controller;

import com.wzh.datacenter.entity.Task;
import com.wzh.datacenter.service.TaskService;
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
@RequestMapping("/task")
public class TaskController {


	@Resource(name = "taskServiceImpl")
	private TaskService taskService;

	private MapUtils<Task> mapUtils = new MapUtils<>();

	/**
	 * 添加协议
	 * @param task
	 * @return
	 */
	@PostMapping("/addTask")
	public int addTask(Task task) {
		return taskService.addTask(mapUtils.toMap(task));
	}

	/**
	 * 更新协议信息
	 * @param task
	 * @return
	 */
	@PostMapping("/updateTask")
	public int updateTask(Task task) {
		return taskService.updateTask(mapUtils.toMap(task));
	}

}
