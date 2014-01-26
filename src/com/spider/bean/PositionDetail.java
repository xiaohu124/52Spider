package com.spider.bean;

import com.mongodb.BasicDBObject;

public class PositionDetail extends BasicDBObject {
	private long pos_id;
	private String pos_name;
	private long com_id;
	private String pub_time;
	private String address;
	private int pos_num;
	private String degree;
	private String pos_fun;
	private String pos_desc;
	
	public PositionDetail() {
	}
	
	public PositionDetail(long pos_id, String pos_name, long com_id, String pub_time, 
			String address, int pos_num, String degress, String pos_fun, String pos_desc) {
		
	}

	public final long getPos_id() {
		return pos_id;
	}

	public final void setPos_id(long pos_id) {
		this.pos_id = pos_id;
	}

	public final String getPos_name() {
		return pos_name;
	}

	public final void setPos_name(String pos_name) {
		this.pos_name = pos_name;
	}

	public final long getCom_id() {
		return com_id;
	}

	public final void setCom_id(long com_id) {
		this.com_id = com_id;
	}

	public final String getPub_time() {
		return pub_time;
	}

	public final void setPub_time(String pub_time) {
		this.pub_time = pub_time;
	}

	public final String getAddress() {
		return address;
	}

	public final void setAddress(String address) {
		this.address = address;
	}

	public final int getPos_num() {
		return pos_num;
	}

	public final void setPos_num(int pos_num) {
		this.pos_num = pos_num;
	}

	public final String getDegree() {
		return degree;
	}

	public final void setDegree(String degree) {
		this.degree = degree;
	}

	public final String getPos_fun() {
		return pos_fun;
	}

	public final void setPos_fun(String pos_fun) {
		this.pos_fun = pos_fun;
	}

	public final String getPos_desc() {
		return pos_desc;
	}

	public final void setPos_desc(String pos_desc) {
		this.pos_desc = pos_desc;
	}
	
}
