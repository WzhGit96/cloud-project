/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.dao.provider;

import com.wzh.datacenter.util.SqlUtil;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-09-30
 */
public class BaseProvider {

	public String add(Map<String, Map<String, Object>> paraMap) {
		return SqlUtil.getInsertSql(paraMap);
	}

	public String update(Map<String, Map<String, Object>> paraMap) {
		return SqlUtil.getUpdateSql(paraMap);
	}

	public String delete(Map<String, Map<String, Object>> paraMap) {
		return SqlUtil.getDeleteSql(paraMap);
	}

}
