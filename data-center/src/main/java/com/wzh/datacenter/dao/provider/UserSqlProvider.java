/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.dao.provider;

import com.wzh.datacenter.util.MapUtils;
import com.wzh.datacenter.util.SqlUtil;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-9-22
 */
public class UserSqlProvider<T> {

	private MapUtils mapUtils = new MapUtils();

	public String findAllUserByPage() {
		String sql = "select * from users";
		return sql;
	}

}
