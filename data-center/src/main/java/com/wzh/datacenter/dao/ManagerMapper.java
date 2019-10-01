/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.dao;

import com.wzh.datacenter.dao.provider.ManagerSqlProvider;
import com.wzh.datacenter.entity.Manager;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ManagerMapper {

	/**
	 * 添加管理员
	 * @param paraMap
	 * @return
	 */
	@InsertProvider(type = ManagerSqlProvider.class, method = "add")
	int addManager(@Param("manager") Map<String, Object> paraMap);

	/**
	 * 分页查询管理员
	 * @param paraMap
	 * @return
	 */
	@SelectProvider(type = ManagerSqlProvider.class, method = "findAllManagerByPage")
	List<Manager> findAllManagerByPage(@Param("manager") Map<String, Object> paraMap);

	/**
	 * 管理员登录
	 * @param paraMap
	 * @return
	 */
	@Select("select * from manager where account = #{manager.account} or email = #{manager.email} and pwd = #{manager.pwd}")
	List<Manager> login(@Param("manager") Map<String, Object> paraMap);

	/**
	 * 更新用户信息
	 * @param paraMap
	 * @return
	 */
	@UpdateProvider(type = ManagerSqlProvider.class, method = "update")
	int updateManager(@Param("manager") Map<String, Object> paraMap);

	/**
	 * 删除用户信息
	 * @param paraMap
	 * @return
	 */
	@DeleteProvider(type = ManagerSqlProvider.class, method = "delete")
	int deleteUser(@Param("manager") Map<String, Object> paraMap);


}
