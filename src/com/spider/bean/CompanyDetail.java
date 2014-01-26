package com.spider.bean;

import com.mongodb.BasicDBObject;

public class CompanyDetail extends BasicDBObject {
	private long com_id;
	private String name;
	private String industry;
	private String property;
	private int maxScale;
	private int minScale;
	private String com_desc;
	
	public CompanyDetail() {
	}
	
	public CompanyDetail(long com_id, String name, String industry, String property, int maxScale, int minScale, String com_desc) {
		this.com_id = com_id;
		this.name = name;
		this.industry = industry;
		this.property = property;
		this.maxScale = maxScale;
		this.minScale = minScale;
		this.com_desc = com_desc;
		put("cid", com_id);
		put("name", name);
		put("industry", industry);
		put("property", property);
		put("maxScale", maxScale);
		put("minScale", minScale);
		put("desc", com_desc);
	}

	public long getCom_id() {
		return com_id;
	}

	public void setCom_id(long com_id) {
		this.com_id = com_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getProperty() {
		return property;
	}

	public int getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(int maxScale) {
		this.maxScale = maxScale;
	}

	public int getMinScale() {
		return minScale;
	}

	public void setMinScale(int minScale) {
		this.minScale = minScale;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
