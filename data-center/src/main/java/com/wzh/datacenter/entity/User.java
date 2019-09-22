/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.entity;

import lombok.Data;

/**
 * @author Wzh
 * @since 2019-9-22
 */
@Data
public class User {

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
	 * 昵称
	 */
	private String name;

	/**
	 * 图片路径
	 */
	private String pic;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 用户等级
	 */
	private Integer ulevel;

	/**
	 * yc次数
	 */
	private Integer yc;

	/**
	 * 鸽子数
	 */
	private Integer dove;

	/**
	 * 状态 -1.冻结 1.正常
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
