/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.news.service;

import com.wzh.news.entity.News;
import com.wzh.news.util.NewsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Wzh
 * @since 2019-10-02
 */
@Service
public class NewsService {

	private static final Logger Log = LoggerFactory.getLogger(NewsService.class);

	/**
	 * 获取新闻
	 * @return
	 */
	public List<News> getNews() {
		try {
			return NewsUtil.getNews();
		} catch (IOException e) {
			Log.error("io execption {}", e.getMessage());
		}
		return null;
	}

}
