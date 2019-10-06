/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.dao;

import com.wzh.datacenter.dao.provider.DoveManagerSqlProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-01
 */
@Mapper
@Repository
public interface DoveManagerMapper {

	/**
	 * 添加鸽子管理
	 * @param paraMap
	 * @return
	 */
	@InsertProvider(type = DoveManagerSqlProvider.class, method = "insert")
	int addDove(Map<String, Object> paraMap);

	/**
	 * 修改鸽子管理信息
	 * @param paraMap
	 * @return
	 */
	@UpdateProvider(type = DoveManagerSqlProvider.class, method = "update")
	int updateDove(Map<String, Object> paraMap);

	/**
	 * 删除鸽子管理
	 * @param paraMap
	 * @return
	 */
	@DeleteProvider(type = DoveManagerSqlProvider.class, method = "delete")
	int deleteDove(Map<String, Object> paraMap);

}
