/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.news.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wzh
 * @since 2019-10-02
 */
@Data
public class News implements Serializable {

	private static final long serialVersionUID = -1038543171226232393L;

	public News(){
		super();
	}

	public News(String img, String url, String title, String content, String time) {
		this.img = img;
		this.url = url;
		this.title = title;
		this.content = content;
		this.time = time;
	}

	/**
	 * 新闻图片
	 */
	private String img;

	/**
	 * 新闻链接
	 */
	private String url;

	/**
	 * 新闻标题
	 */
	private String title;

	/**
	 * 新闻简述
	 */
	private String content;

	/**
	 * 新闻时间
	 */
	private String time;
}
