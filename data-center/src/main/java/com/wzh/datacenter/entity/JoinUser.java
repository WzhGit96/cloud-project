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
public class JoinUser implements Serializable {

	private static final long serialVersionUID = 2177092868393938110L;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * tid
	 */
	private Integer tid;

	/**
	 * 用户id
	 */
	private Integer uid;

	/**
	 * 状态 0.未确认参加 1.确认参加
	 */
	private Integer status;

	/**
	 * 5个备用字段
	 */
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String remark5;

}
