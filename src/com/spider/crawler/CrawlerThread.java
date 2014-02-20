package com.spider.crawler;

import java.util.HashMap;

import javax.swing.plaf.SliderUI;

import com.mongodb.BasicDBObject;
import com.spider.bean.CompanyDetail;
import com.spider.bean.PositionDetail;
import com.spider.db.DBConnection;
import com.spider.parse.HtmlComAndPosParse;
import com.spider.parse.TagObjectBuilder;

public class CrawlerThread implements Runnable {
	private String url = null;
	
	public CrawlerThread(String url) {
		this.url = url;
	}
	
	@Override
	public void run() {
		try {
			HashMap<String, String> map = HtmlComAndPosParse.getComyAndPosDetailByUrl(this.url, "GBK");
			CompanyDetail company = TagObjectBuilder.getCompanyDetailObj(map);
			PositionDetail position = TagObjectBuilder.getPositionDetailObj(map);
			
			DBConnection dbc = new DBConnection();
			dbc.setCollection("position_detail");
			dbc.insert((BasicDBObject)position);
			dbc.setCollection("company");
			dbc.insert((BasicDBObject)company);
			
			dbc.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url1 = "http://search.51job.com/job/45395515,c.html";
		CrawlerThread crawler1 = new CrawlerThread(url1);
		
		String url2 = "http://search.51job.com/job/59946264,c.html";
		CrawlerThread crawler2 = new CrawlerThread(url2);
		
		Thread thread1 = new Thread(crawler1);
		Thread thread2 = new Thread(crawler2);
		thread1.start();
		thread2.start();
	}

}
