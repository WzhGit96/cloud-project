/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.dao.provider;

import com.wzh.datacenter.util.MapUtils;

/**
 * @author Wzh
 * @since 2019-9-22
 */
public class UserSqlProvider extends BaseProvider {

	private MapUtils mapUtils = new MapUtils();

	public String findAllUserByPage() {
		String sql = "select * from users";
		return sql;
	}

}
