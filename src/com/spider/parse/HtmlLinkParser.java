package com.spider.parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.LinkRegexFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableColumn;

import com.spider.crawler.SingleCrawler;
import com.spider.util.Queue;

public class HtmlLinkParser {
	public static List<String> getValuesOfLink(List<TableTag> list, String regex) {
		List<String> urlList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			TableTag tag = list.get(i);
			//过滤<a> 正则匹配
			try {
				Parser parser = Parser.createParser(tag.toHtml(), "GBK");
				NodeFilter filter = new LinkRegexFilter(regex);
	            NodeList node = parser.extractAllNodesThatMatch(filter);
	            for (int j = 0; j < node.size(); j++) {
	            	Node nodet = node.elementAt(j);
	            	LinkTag linkTag = new LinkTag();
	            	linkTag.setText(nodet.toHtml());
	            	urlList.add(linkTag.extractLink());
	            }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return urlList;
	}
	
	public static List<String> getLinkListOfContent(String content, String encode, String regx) throws Exception {
		NodeList nodelist = HtmlNodeParser.getTagsOfContent(content, encode, TableTag.class);
		List<TableTag> table = HtmlNodeParser.getTableTags(nodelist, "id", "resultList");
		return getValuesOfLink(table, regx);
	}
	
	public static List<String> getLinkListByUrl(String url, String encode) throws Exception {
		String regex = "http://search\\.51job\\.com/job/[0-9|a-z|,]*\\.html";
		String content = SingleCrawler.getContentOfPage(url);
		return getLinkListOfContent(content, encode, regex);
	}
	
	public static void main(String[] args) {
		//获取Position信息
		String listUrl = "http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=180200%2C00&keywordtype=2&lang=c&stype=1&fromType=1";
		String regex = "http://search\\.51job\\.com/job/[0-9|a-z|,]*\\.html";
//		SingleCrawler crawler = new SingleCrawler(listUrl);
//		try {
//			String content = crawler.getContentOfPage();
//			List<String> list = HtmlLinkParser.getLinkListOfContent(content, "GBK", regex);
//			System.out.println(list);
//		} catch (Exception e) { 
//			e.printStackTrace();
//		}
//		crawler.CloseCrawler();
	}
}
