package com.spider.bean;

import com.mongodb.BasicDBObject;

public class CompanyDetail extends BasicDBObject {
	private String com_name;
	private String industry;
	private String property;
	private int maxScale;
	private int minScale;
	private String com_desc;
	private String webSite;
	private String address;
	private String postcode ;

	public CompanyDetail() {
	}
	
	public CompanyDetail(String name, String industry, String property, 
			int maxScale, int minScale, String com_desc, String webSite, String address, String postcode) {
		this.com_name = name;
		this.industry = industry;
		this.property = property;
		this.maxScale = maxScale;
		this.minScale = minScale;
		this.com_desc = com_desc;
		this.webSite = webSite;
		this.address = address;
		this.postcode = postcode;
		
		put("com_name", name);
		put("industry", industry);
		put("property", property);
		put("maxScale", maxScale);
		put("minScale", minScale);
		put("com_desc", com_desc);
		put("webSite", webSite);
		put("address", address);
		put("postcode", postcode);
	}

	public final String getCom_name() {
		return com_name;
	}

	public final void setCom_name(String com_name) {
		put("com_name", com_name);
		this.com_name = com_name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		put("industry", industry);
		this.industry = industry;
	}

	public String getProperty() {
		put("property", property);
		return property;
	}

	public int getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(int maxScale) {
		put("maxScale", maxScale);
		this.maxScale = maxScale;
	}

	public int getMinScale() {
		return minScale;
	}

	public void setMinScale(int minScale) {
		put("minScale", minScale);
		this.minScale = minScale;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getCom_desc() {
		return com_desc;
	}

	public void setCom_desc(String com_desc) {
		put("com_desc", com_desc);
		this.com_desc = com_desc;
	}

	public final String getWebSite() {
		return webSite;
	}

	public final void setWebSite(String webSite) {
		put("webSite", webSite);
		this.webSite = webSite;
	}

	public final String getAddress() {
		return address;
	}

	public final void setAddress(String address) {
		put("address", address);
		this.address = address;
	}
	
	public final String getPostcode() {
		return postcode;
	}

	public final void setPostcode(String postcode) {
		put("postcode", postcode);
		this.postcode = postcode;
	}

}
