package com.spider.bean;

import com.mongodb.BasicDBObject;

public class Position extends BasicDBObject {
	private long pos_id;
	private String pos_city;
	private String pos_url;
	
	public Position() {
	}
	
	public Position(long pos_id, String pos_city, String pos_url) {
		this.pos_id = pos_id;
		this.pos_city = pos_city;
		this.pos_url = pos_url;
		
		put("posid", pos_id);
		put("city", pos_city);
		put("url", pos_url);
	}
	
	public long getPos_id() {
		return pos_id;
	}

	public void setPos_id(long pos_id) {
		put("posid", pos_id);
		this.pos_id = pos_id;
	}

	public String getPos_city() {
		return pos_city;
	}

	public void setPos_city(String pos_city) {
		put("city", pos_city);
		this.pos_city = pos_city;
	}

	public String getPos_url() {
		return pos_url;
	}

	public void setPos_url(String pos_url) {
		put("url", pos_url);
		this.pos_url = pos_url;
	}
}
