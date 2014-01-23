package com.spider.test;

import org.junit.Test;

import com.spider.crawler.SingleCrawler;
import com.spider.parse.HtmlTxtParser;

import junit.framework.TestCase;

public class ParseTestCase extends TestCase {
	@Test
	public void testParseHtml() throws Exception {
		String url = "http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=180200&keywordtype=2&lang=c&stype=1&fromType=1";
		
		SingleCrawler crawler = new SingleCrawler(url);
		String content = crawler.getContentOfPage();
		String encode = "GB2312";
		crawler.CloseCrawler();
		
		HtmlTxtParser htmlParser = new HtmlTxtParser();
		htmlParser.getValuesOfHtmlContent(content, encode);
//		System.out.println(htmlParser.getValuesOfHtmlContent(content, encode));
	}
	
}
