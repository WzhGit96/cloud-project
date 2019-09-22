/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.dao.provider;

import com.wzh.datacenter.util.MapUtils;
import com.wzh.datacenter.util.SqlUtil;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-9-22
 */
public class UserSqlProvider {

	private MapUtils mapUtils = new MapUtils();

	public String addUser(Map<String, Map<String, Object>> paraMap) {
		return SqlUtil.getInsertSql("users", paraMap);
	}

	public String findAllUserByPage() {
		String sql = "select * from users";
		return sql;
	}

	public String updateUser(Map<String, Map<String, Object>> paraMap) {
		return SqlUtil.getUpdateSql("users", paraMap);
	}

	public String deleteUser(Map<String, Map<String, Object>> paraMap) {
		return SqlUtil.getDeleteSql("users", paraMap);
	}

}
