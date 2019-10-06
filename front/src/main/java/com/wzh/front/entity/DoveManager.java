/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.front.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wzh
 * @since 2019-10-01
 */
@Data
public class DoveManager implements Serializable {

	private static final long serialVersionUID = 3054937001119766910L;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * uid
	 */
	private Integer uid;

	/**
	 * 获得鸽子的时间
	 */
	private Long startTime;

	/**
	 * 状态 0.结束 1.正常
	 */
	private Integer status;

	/**
	 * 五个备用字段
	 */
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String remark5;
}
