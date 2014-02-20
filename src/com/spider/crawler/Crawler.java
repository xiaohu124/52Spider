package com.spider.crawler;

import java.util.List;

import com.spider.db.DBOperator;
import com.spider.parse.HtmlLinkParser;
import com.spider.parse.TagObjectBuilder;
import com.spider.util.Tool;

public class Crawler {
	public static boolean run(String url) {
		try {
			List<String> urlList = HtmlLinkParser.getLinkListByUrl(url, "GBK");
			
			System.out.println(urlList.size());
			if (urlList.size() == 0) {
				return false;
			}
			DBOperator dbo = new DBOperator();
			dbo.insertPosList(urlList);
			
			for (int i = 0; i < urlList.size(); i++) {
				if (TagObjectBuilder.getPosMapFromURL(urlList.get(i)) != null) {
					dbo.insertComAndPosDetail(urlList.get(i), "GBK");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://search.51job.com/jobsearch/search_result.php?" +
				"fromJs=1&jobarea=000000%2C00&district=000000&funtype=0000&industrytype=00&issuedate=9&providesalary=99&keyword=%E8%AE%A1%E7%AE%97%E6%9C%BA&keywordtype=2" +
				"&curr_page=1&lang=c&stype=1&postchannel=0000&workyear=99&cotype=99" +
				"&degreefrom=99&jobterm=01&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14";
		int i = 0;
		while (i++ < 2) {
			System.out.println(url);
			
			Crawler.run(url);
			
			url = Tool.nextPage(url);
		}
	}

}
