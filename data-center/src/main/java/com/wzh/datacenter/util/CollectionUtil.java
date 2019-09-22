/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.util;

import java.util.Collection;

/**
 * @author Wzh
 * @since 2019-9-22
 */
public class CollectionUtil {

	public static boolean isEmpty(Collection collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean isNotEmpty(Collection collection) {
		return collection != null && !collection.isEmpty();
	}

}
