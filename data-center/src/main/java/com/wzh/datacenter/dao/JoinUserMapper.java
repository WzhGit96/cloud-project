/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.dao;

import com.wzh.datacenter.dao.provider.JoinUserSqlProvider;
import com.wzh.datacenter.dao.provider.TaskSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-01
 */
@Mapper
@Repository
public interface JoinUserMapper {

	/**
	 * 添加参与人员信息
	 * @param paraMap
	 * @return
	 */
	@InsertProvider(type = JoinUserSqlProvider.class, method = "insert")
	int addJoinUser(@Param("joinUser") Map<String, Object> paraMap);

	/**
	 * 更新参与人员信息
	 * @param paraMap
	 * @return
	 */
	@UpdateProvider(type = JoinUserSqlProvider.class, method = "update")
	int updateJoinUser(@Param("joinUser") Map<String, Object> paraMap);

	/**
	 * 删除参与人员信息
	 * @param paraMap
	 * @return
	 */
	@DeleteProvider(type = JoinUserSqlProvider.class, method = "delete")
	int deleteJoinUser(@Param("joinUser") Map<String, Object> paraMap);

}
