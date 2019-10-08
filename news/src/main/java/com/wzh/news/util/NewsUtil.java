/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.news.util;

import com.wzh.news.entity.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @since 2019-10-02
 */
public class NewsUtil {

	private static final String newsUrl = "http://dota2.sgamer.com";

	private static Logger Log = LoggerFactory.getLogger(NewsUtil.class);

	private static String getHtml() throws IOException {
		StringBuffer news = new StringBuffer();
		try {
			URL url = new URL(newsUrl);
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");

			//为字符输入流添加缓冲
			BufferedReader buff = new BufferedReader(isr);
			String data;
			while((data = buff.readLine()) != null) {
				news.append(data);
			}
			buff.close();
			isr.close();
			is.close();
		} catch (MalformedURLException e) {
			Log.error("the url has error {}", e.getMessage());
		}
		return news.toString();
	}

	/**
	 * 解析获取到的html，提取新闻部分的图片、标题和简述以及时间
	 * @param html
	 * @return
	 */
	private static List<News> dealNews(String html) {
		// 解析网页内容
		Document document = (Document) Jsoup.parse(html);

		// 获取新闻所在的div节点
		Element div = document.getElementById("all");
		// 获取div中的所有新闻信息
		Elements newsItem = div.getElementsByClass("hot-news");
		List<News> news = new ArrayList<>();
		for (Element item : newsItem) {
			// 新闻链接
			String url = newsUrl + item.getElementsByTag("a").get(0).attr("href");
			// 新闻图片
			String img = item.getElementsByTag("img").get(0).attr("src");
			// 新闻标题
			String title = item.getElementsByTag("a").get(1).text();
			// 新闻简述
			String content = item.getElementsByTag("p").get(1).text();
			// 新闻时间
			String time = item.getElementsByTag("span").get(1).text();
			News itemNews = new News(img, url, title, content, time);
			news.add(itemNews);
		}
		return news;
	}

	/**
	 * 获取新闻
	 * @return
	 * @throws IOException
	 */
	public static List<News> getNews() throws IOException {
		return dealNews(getHtml());
	}

}
