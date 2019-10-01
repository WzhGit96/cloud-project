/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wzh
 * @since 2019-10-01
 */
@Data
public class Vote implements Serializable {

	private static final long serialVersionUID = -6892851615421561116L;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * tid
	 */
	private Integer tid;

	/**
	 * uid
	 */
	private Integer uid;

	/**
	 * 票数目
	 */
	private Integer votes;

	/**
	 * 状态 0.未开始 1.进行中 -1.结束
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
