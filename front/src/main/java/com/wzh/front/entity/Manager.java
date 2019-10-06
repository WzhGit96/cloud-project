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
public class Manager implements Serializable{

	private static final long serialVersionUID = 4457430522381224102L;
	/**
	 * id
	 */
	private Integer id;

	/**
	 * 帐户
	 */
	private String account;

	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 邮箱
	 */
	private String email;

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

	/**
	 * 分页属性
	 */
	private Integer pageSize;
	private Integer pageNo;


}
