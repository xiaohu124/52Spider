package com.spider.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.LinkRegexFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlSrcParser {
	/**
	 * 使用正则过滤，提取职位url
	 * 
	 * @param content
	 * @param encoding
	 * @param regx
	 * @return
	 */
	public static ArrayList<String> getUrlFromContent(String content, String regex,
			String encoding) {
		ArrayList<String> list = new ArrayList<String>();

		try {
			Parser parser = Parser.createParser(content, encoding);
			// OrFilter是结合几种过滤条件的‘或’过滤器
			NodeFilter filter = new LinkRegexFilter(regex);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			for (int i = 0; i < nodeList.size(); i++) {
				Node node = nodeList.elementAt(i);
				LinkTag linkTag = new LinkTag();
				linkTag.setText(node.toHtml());
				if (!linkTag.extractLink().isEmpty()) {
					list.add(linkTag.extractLink());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 按标签名获取标签
	 * @param content
	 * @param tagName
	 * @param encoding
	 * @return
	 */
	public static NodeList getTagFromContent(String content, String encoding, String tagName) {
		try {
			Parser parser = Parser.createParser(content, encoding);
			NodeFilter filter = new TagNameFilter(tagName);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			return nodeList;
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取tableTags
	 * @param list
	 * @return
	 */
	public static List<TableTag> getTableTagsFromList(NodeList list) {
		List<TableTag> tagList = new ArrayList<TableTag>();
		for (int i = 0; i < list.size(); i++) {
			Node node = list.elementAt(i);
			TableTag tableTag = new TableTag();
			tableTag.setText(node.toHtml());
			tagList.add(tableTag);
		}
		return tagList;
	}
	
	public static List<TableTag> getTableTagsFromContent(String content, String encoding) {
		String tabName = "table";
		NodeList nodelist = HtmlSrcParser.getTagFromContent(content, encoding, tabName);
		return HtmlSrcParser.getTableTagsFromList(nodelist);
	}
	
	public static void main(String[] args) {
//		HashMap<String, String> map = HtmlSrcParser.getPosMapFromURL("http://search.51job.com/job/59458335,c.html");
//		System.out.println("key : " + map.get("id") + ", " + "value : " + map.get("city"));
	}
}
