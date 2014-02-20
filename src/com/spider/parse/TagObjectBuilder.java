package com.spider.parse;

import com.mongodb.BasicDBObject;
import com.spider.bean.CompanyDetail;
import com.spider.bean.Position;
import com.spider.bean.PositionDetail;
import com.spider.util.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagObjectBuilder {
	/**
	 * 从URL中获取id和city
	 * @param url
	 * @return
	 */
	public static Position getPosMapFromURL(String url) {
		Pattern pattern = Pattern.compile("http://search\\.51job\\.com/job/([0-9]+),([a-zA-z]+)\\.html");
		Matcher match = pattern.matcher(url);
		if (match.find() && !match.group(1).isEmpty() && !match.group(2).isEmpty()) {
			return new Position(Integer.parseInt(match.group(1)), match.group(2), url);
		}
		return null;
	}
	
	/**
	 * 获取页面pos对象列表
	 * @param list
	 * @return
	 */
	public static List<BasicDBObject> getPosObjects(List<String> list) {
		List<BasicDBObject> pos = new ArrayList<BasicDBObject>();
		for (int i = 0; i < list.size(); i++) {
			if (getPosMapFromURL(list.get(i)) != null) {
				pos.add((BasicDBObject) getPosMapFromURL(list.get(i)));
			}
		}
		return pos;
	}
	
	public static CompanyDetail getCompanyDetailObj(HashMap<String, String> map) {
		CompanyDetail com = new CompanyDetail();
		if (map.containsKey("com_name")) {
			com.setCom_name(map.get("com_name"));
		}
		if (map.containsKey("industry")) {
			com.setIndustry(map.get("industry"));
		}
		if (map.containsKey("property")) {
			com.setProperty(map.get("property"));
		}
		if (map.containsKey("maxScale")) {
			com.setMaxScale(Integer.parseInt(map.get("maxScale")));
		}
		if (map.containsKey("minScale")) {
			com.setMinScale(Integer.parseInt(map.get("minScale")));
		}
		if (map.containsKey("webSite")) {
			com.setWebSite(map.get("webSite"));
		}
		if (map.containsKey("address")) {
			com.setAddress(map.get("address"));
		}
		if (map.containsKey("postcode")) {
			com.setPostcode(map.get("postcode"));
		}
		return com;
	}
	
	public static PositionDetail getPositionDetailObj(HashMap<String, String> map) {
		PositionDetail pos = new PositionDetail();
		if (map.containsKey("pos_id")) {
			pos.setPos_id(Long.parseLong(map.get("pos_id")));
		}
		if (map.containsKey("com_name")) {
			pos.setCom_name(map.get("com_name"));
		}
		if (map.containsKey("pub_time")) {
			pos.setPub_time(map.get("pub_time"));
		}
		if (map.containsKey("pos_address")) {
			pos.setAddress(map.get("pos_address"));
		}
		if (map.containsKey("pos_num")) {
			int num = Tool.isDigital(map.get("pos_num")) ? Integer.parseInt(map.get("pos_num")) : -1;
			pos.setPos_num(num);
		}
		if (map.containsKey("pos_exp")) {
			pos.setPos_exp(map.get("pos_exp"));
		}
		if (map.containsKey("degree")) {
			pos.setDegree(map.get("degree"));
		}
		if (map.containsKey("salary")) {
			pos.setSalary(map.get("salary"));
		}
		if (map.containsKey("pos_tags")) {
			pos.setPos_tags(map.get("pos_tags"));
		}
		if (map.containsKey("pos_fun")) {
			pos.setPos_fun(map.get("pos_fun"));
		}
		if (map.containsKey("pos_desc")) {
			pos.setPos_desc(map.get("pos_desc"));
		}
		
		return pos;
	}
}
