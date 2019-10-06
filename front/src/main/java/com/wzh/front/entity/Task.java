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
public class Task implements Serializable {
	private static final long serialVersionUID = 423072068710368282L;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 用户id
	 */
	private Integer uid;

	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 开始时间
	 */
	private Long startTime;

	/**
	 * 持续时间
	 */
	private Long endTime;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 描述
	 */
	private String describe;

	/**
	 * 状态
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
