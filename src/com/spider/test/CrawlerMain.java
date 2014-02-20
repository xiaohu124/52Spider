package com.spider.test;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.spider.bean.Position;
import com.spider.crawler.SingleCrawler;
import com.spider.db.DBConnection;
import com.spider.db.DBOperator;
import com.spider.parse.HtmlLinkParser;
import com.spider.parse.TagObjectBuilder;

public class CrawlerMain {

	public static void main(String[] args) {
		//获取Position信息
		String listUrl = "http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=180200%2C00&keywordtype=2&lang=c&stype=1&fromType=1";

		String regex = "http://search\\.51job\\.com/job/[0-9|a-z|,]*\\.html";

		try {
			String content = SingleCrawler.getContentOfPage(listUrl);
			List<String> list = HtmlLinkParser.getLinkListOfContent(content, "GBK", regex);
			System.out.println(list);
			List<BasicDBObject> listPos = TagObjectBuilder.getPosObjects(list);
//			DBConnection dbc = new DBConnection();
//			dbc.initConnection();
//			DBOperator dbo = new DBOperator("position");
//			dbo.insertAll(listPos);
//			dbc.closeConnection();
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}

}
