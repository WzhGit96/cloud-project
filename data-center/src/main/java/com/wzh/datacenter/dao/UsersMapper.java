/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.dao;

import com.wzh.datacenter.dao.provider.UserSqlProvider;
import com.wzh.datacenter.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface UsersMapper {

	/**
	 * 添加用户
	 * @param paraMap
	 * @return
	 */
	@InsertProvider(type = UserSqlProvider.class, method = "addUser")
	int addUser(@Param("user") Map<String, Object> paraMap);

	/**
	 * 查询用户
	 * @param paraMap
	 * @return
	 */
	@SelectProvider(type = UserSqlProvider.class, method = "findAllUserByPage")
	List<User> findAllUserByPage(@Param("user") Map<String, Object> paraMap);

	/**
	 * 更新用户信息
	 * @param paraMap
	 * @return
	 */
	@UpdateProvider(type = UserSqlProvider.class, method = "updateUser")
	int updateUser(@Param("user") Map<String, Object> paraMap);


}
