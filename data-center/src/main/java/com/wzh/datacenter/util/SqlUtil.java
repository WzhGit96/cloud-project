/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.util;

import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-9-22
 */
public class SqlUtil {

	private static final Logger logger = LoggerFactory.getLogger(SqlUtil.class);

	public static String getInsertSql(String table, Map<String, Map<String, Object>> paraMap) {
		SQL sql = initSQL(table, "insert");
		String paraName = getParamName(paraMap);
		Map<String, Object> map = paraMap.get(paraName);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != null) {
				sql.VALUES(entry.getKey(), "#{" +paraName+ "." + entry.getKey() + "}");
			}
		}
		logger.debug(sql.toString());
		return sql.toString();
	}

	public static String getUpdateSql(String table, Map<String, Map<String, Object>> paraMap) {
		SQL sql = initSQL(table, "update");
		String paraName = getParamName(paraMap);
		Map<String, Object> map = paraMap.get(paraName);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != null) {
				sql.SET(  entry.getKey() + "=" + "#{" +paraName+ "." + entry.getKey() + "}");
			}
		}
		sql.WHERE("id = #{" + paraName + ".id}");
		logger.debug(sql.toString());
		return sql.toString();
	}

	public static SQL initSQL(String table, String operation) {
		SQL sql = new SQL();
		if ("insert".equals(operation)) {
			sql.INSERT_INTO(table);
		}

		if ("update".equals(operation)) {
			sql.UPDATE(table);
		}

		if ("delete".equals(operation)) {
			sql.DELETE_FROM(table);
		}

		return sql;
	}

	public static String getParamName(Map<String, Map<String, Object>> paraMap) {
		String paraName = null;
		for (Map.Entry<String, Map<String, Object>> entry : paraMap.entrySet()) {
			if (!entry.getKey().contains("param")) {
				paraName = entry.getKey();
				break;
			}
		}
		if (paraName == null) {
			logger.error("the paraName is null");
			throw new NullPointerException();
		}
		return paraName;
	}

}
