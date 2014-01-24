package com.spider.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.spider.crawler.SingleCrawler;
import com.spider.parse.HtmlTxtParser;

import junit.framework.TestCase;

public class ParseTestCase extends TestCase {
	@Test
	public void testParseHtml() throws Exception {
//		String url = "http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=180200&keywordtype=2&lang=c&stype=1&fromType=1";
//		
//		SingleCrawler crawler = new SingleCrawler(url);
//		String content = crawler.getContentOfPage();
//		String encode = "GB2312";
//		crawler.CloseCrawler();
//		String regxStr = "http://search\\.51job\\.com/job/";
//		System.out.println(regxStr);
//		
//		HtmlTxtParser htmlParser = new HtmlTxtParser();
//		htmlParser.getValuesOfLinkContent(content, regxStr, encode);
//		htmlParser.getValuesOfTableContent(content, encode);
//		System.out.println(htmlParser.getValuesOfHtmlContent(content, encode));
	}
	
	@Test
	public void testRexgStr() {
		String str = "http://search.51job.com/job/52835958,c.html,732@24.com";
//		String regx = "http://search\\.51job\\.com/job/[0-9|a-z|,]*\\.html";
		String regx = "[a-zA-Z0-9_-]+@\\w+\\.[a-z]+(\\.[a-z]+)?";
		Pattern pattern = Pattern.compile(regx);
		Matcher match = pattern.matcher(str);
		System.out.println(match.matches());
		System.out.println(match.group());
		
	}
	
}
