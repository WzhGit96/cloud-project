/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wzh
 * @since 2019-9-22
 */
public class MapUtils {

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
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(obj));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return this.isNotEmpty(map) ? map : new HashMap<>();
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
