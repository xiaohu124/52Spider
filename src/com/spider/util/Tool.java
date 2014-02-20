package com.spider.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tool {
	public static String nextPage(String url) {
		String[] params = url.split("&");
		for (int i = 0; i < params.length; i++) {
			if (params[i].startsWith("curr_page")) {
				int page = Integer.parseInt(params[i].split("=")[1].trim()) + 1;
				return url.replaceAll(params[i], "curr_page="+page);
			}
		}
		return "";
	}
	
	public static boolean isDigital(String num) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence)num);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		String url = "http://search.51job.com/jobsearch/search_result.php?" +
				"fromJs=1&jobarea=000000%2C00&district=000000&funtype=0000&industrytype=00&issuedate=9&providesalary=99&keyword=%E8%AE%A1%E7%AE%97%E6%9C%BA&keywordtype=2" +
				"&curr_page=1&lang=c&stype=1&postchannel=0000&workyear=99&cotype=99" +
				"&degreefrom=99&jobterm=01&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14";
		
		System.out.println(url);
		System.out.println(Tool.nextPage(url));
		
		System.out.println(isDigital("123"));
		System.out.println(isDigital("1"));
		System.out.println(isDigital("123a"));
		System.out.println(isDigital("Èô¸É"));
	}
}
