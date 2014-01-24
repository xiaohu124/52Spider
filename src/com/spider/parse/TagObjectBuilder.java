package com.spider.parse;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Tag;
import org.htmlparser.tags.TableTag;

public class TagObjectBuilder {
	/**
	 * 从URL中获取id和city
	 * @param url
	 * @return
	 */
	public HashMap<String, String> getPosMapFromURL(String url, String regx) {
		HashMap<String, String> map = new HashMap<String, String>();
//		Pattern pattern = Pattern.compile("http://search\\.51job\\.com/job/([0-9]+),([a-zA-z]+)\\.html");
		Pattern pattern = Pattern.compile(regx);
		Matcher match = pattern.matcher(url);
		if (match.find() && !match.group(1).isEmpty() && !match.group(2).isEmpty()) {
			map.put("id", match.group(1));
			map.put("city", match.group(2));
			map.put("url", url);
		}
		return map;
	}	
	
	public boolean isRightTable(Tag tag, String attributeName, String attributeValue) {
		String value = tag.getAttribute(attributeName);
		return value.equalsIgnoreCase(attributeValue);
	}
	
	String[] attributes = {"sr_bt", ""};
	String[] names = {"position", ""};
}
