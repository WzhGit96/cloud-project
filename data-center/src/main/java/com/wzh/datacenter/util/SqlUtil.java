/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.util;

import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author Wzh
 * @since 2019-9-22
 */
public class SqlUtil {

	private static final Logger logger = LoggerFactory.getLogger(SqlUtil.class);

	/**
	 * 通用方法
	 * 获取插入的sql语句
	 * @param table
	 * @param paraMap
	 * @return
	 */
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

	/**
	 * 通用方法
	 * 获取更新的sql语句
	 * @param table
	 * @param paraMap
	 * @return
	 */
	public static String getUpdateSql(String table, Map<String, Map<String, Object>> paraMap) {
		SQL sql = initSQL(table, "update");
		String paraName = getParamName(paraMap);
		Map<String, Object> map = paraMap.get(paraName);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != null && disableField(entry.getKey())) {
				sql.SET(  entry.getKey() + "=" + "#{" +paraName+ "." + entry.getKey() + "}");
			}
		}
		sql.WHERE("id = #{" + paraName + ".id}");
		logger.debug(sql.toString());
		return sql.toString();
	}

	/**
	 * 通用方法
	 * 获取删除的sql语句
	 * 像这种不是很复杂的sql语句可以直接写在mapper中，这里只是先预留方法
	 * @param table
	 * @param paraMap
	 * @return
	 */
	public static String getDeleteSql(String table, Map<String, Map<String, Object>> paraMap) {
		SQL sql = initSQL(table, "delete");
		String paraName = getParamName(paraMap);
		Map<String, Object> map = paraMap.get(paraName);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != null) {
				sql.WHERE(  entry.getKey() + "=" + "#{" +paraName+ "." + entry.getKey() + "}");
			}
		}
		return sql.toString();
	}

	/**
	 * SQL类的初始化
	 * SELECT要包含列名（cloumns），所以要单独列出来
	 * @param table
	 * @param operation
	 * @return
	 */
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

	/**
	 * 获取mapper中传过来的参数名 仅限parameterType=java.util.Map
	 * @param paraMap
	 * @return
	 */
	@NotNull
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

	/**
	 * 预留的方法，不想修改的字段
	 * 针对特定的业务情况
	 * 比如id的不能修改只用于查询where的条件来使用
	 * 或者添加新的方法来过滤字段
	 * @param key
	 * @return
	 */
	public static boolean disableField(String key) {
		if ("id".equals(key)) {
			return false;
		}
		// TODO 新增不想修改的字段
		return true;
	}

}
