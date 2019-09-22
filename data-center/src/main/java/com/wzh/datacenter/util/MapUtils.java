/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wzh
 * @since 2019-9-22
 */
public class MapUtils<T> {

	private static final Map<String, String> tableNames = new HashMap<>();
	private static Logger Log = LoggerFactory.getLogger(MapUtils.class);


	static {
		tableNames.put("User", "users");
		tableNames.put("Manager", "manager");
		tableNames.put("Task", "task");
		tableNames.put("Joinuser", "joinuser");
		tableNames.put("Vote", "vote");
		tableNames.put("Dovemanager", "dovemanager");
	}

	/**
	 * 将实体类转换为map
	 * @param obj
	 * @return
	 */
	public Map<String, Object> toMap(Object obj) {
		if (obj == null) {
			return new HashMap<>();
		}
		Map<String, Object> map = new HashMap<>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (!"serialVersionUID".equals(field.getName())) {
				field.setAccessible(true);
				try {
					map.put(field.getName(), field.get(obj));
				} catch (IllegalAccessException e) {
					Log.error("field error {}", e.getMessage());
				}
			}
		}
		map.put("table", getTableName((T) obj));
		return this.isNotEmpty(map) ? map : new HashMap<>();
	}

	public String getTableName(T t) {
		String className = t.getClass().getSimpleName();
		return tableNames.get(className);
	}

	public static Map objectToMap(Object obj) {
		return obj == null ? null : new  org.apache.commons.beanutils.BeanMap(obj);
	}


	public boolean isNotEmpty(Map map) {
		return map != null && !map.isEmpty();
	}

	public boolean isEmpty(Map map) {
		return map == null || map.isEmpty();
	}

}
