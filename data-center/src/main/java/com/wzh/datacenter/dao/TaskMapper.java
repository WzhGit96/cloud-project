/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.dao;

import com.wzh.datacenter.dao.provider.TaskSqlProvider;
import com.wzh.datacenter.dao.provider.UserSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-01
 */
@Mapper
@Repository
public interface TaskMapper {


	@InsertProvider(type = TaskSqlProvider.class, method = "insert")
	int addTask(@Param("task") Map<String, Object> paraMap);

	/**
	 * 更新协议信息
	 * @param paraMap
	 * @return
	 */
	@UpdateProvider(type = TaskSqlProvider.class, method = "update")
	int updateTask(@Param("task") Map<String, Object> paraMap);

	/**
	 * 删除协议
	 * @param paraMap
	 * @return
	 */
	@DeleteProvider(type = TaskSqlProvider.class, method = "delete")
	int deleteTask(@Param("task") Map<String, Object> paraMap);

}
