package com.spider.bean;

import com.mongodb.BasicDBObject;

public class PositionDetail extends BasicDBObject {
	private long pos_id;
	private String pos_name;
	private String com_name;
	private String pub_time;
	private String address;
	private int pos_num;
	private String pos_exp;
	private String degree;
	private String salary;
	private String pos_tags;
	private String pos_fun;
	private String pos_desc;
	
	public PositionDetail() {
	}
	
	public PositionDetail(long pos_id, String pos_name, String com_name, String pub_time, 
			String address, int pos_num, String pos_exp, String degree, String salary, String pos_tags, String pos_fun, String pos_desc) {
		this.pos_id = pos_id;
		this.pos_name = pos_name;
		this.com_name = com_name;
		this.pub_time = pub_time;
		this.address = address;
		this.pos_num = pos_num;
		this.pos_exp = pos_exp;
		this.degree = degree;
		this.salary = salary;
		this.pos_tags = pos_tags;
		this.pos_fun = pos_fun;
		this.pos_desc = pos_desc;
		this.salary = salary;
		
		put("pos_id", pos_id);
		put("pos_name", pos_name);
		put("com_name", com_name);
		put("pub_time", pub_time);
		put("address", address);
		put("pos_num", pos_num);
		put("pos_exp", pos_exp);
		put("degress", degree);
		put("salary", salary);
		put("pos_tags", pos_tags);
		put("pos_fun", pos_fun);
		put("pos_desc", pos_desc);
	}

	public final long getPos_id() {
		return pos_id;
	}

	public final void setPos_id(long pos_id) {
		put("pos_id", pos_id);
		this.pos_id = pos_id;
	}

	public final String getPos_name() {
		return pos_name;
	}

	public final void setPos_name(String pos_name) {
		put("pos_name", pos_name);
		this.pos_name = pos_name;
	}

	public final String getCom_name() {
		return com_name;
	}

	public final void setCom_name(String com_name) {
		put("com_name", com_name);
		this.com_name = com_name;
	}

	public final String getPub_time() {
		put("pub_time", pub_time);
		return pub_time;
	}

	public final void setPub_time(String pub_time) {
		this.pub_time = pub_time;
	}

	public final String getAddress() {
		return address;
	}

	public final void setAddress(String address) {
		put("pos_address", address);
		this.address = address;
	}

	public final int getPos_num() {
		return pos_num;
	}

	public final void setPos_num(int pos_num) {
		put("pos_num", pos_num);
		this.pos_num = pos_num;
	}

	public final String getDegree() {
		return degree;
	}

	public final void setDegree(String degree) {
		put("degress", degree);
		this.degree = degree;
	}

	public final String getPos_fun() {
		return pos_fun;
	}

	public final void setPos_fun(String pos_fun) {
		put("pos_fun", pos_fun);
		this.pos_fun = pos_fun;
	}

	public final String getPos_desc() {
		return pos_desc;
	}

	public final void setPos_desc(String pos_desc) {
		put("pos_desc", pos_desc);
		this.pos_desc = pos_desc;
	}

	public final String getPos_exp() {
		return pos_exp;
	}

	public final void setPos_exp(String pos_exp) {
		this.pos_exp = pos_exp;
	}
	
	public final String getPos_tags() {
		return pos_tags;
	}

	public final void setPos_tags(String pos_tags) {
		put("pos_tags", pos_tags);
		this.pos_tags = pos_tags;
	}

	public final String getSalary() {
		return salary;
	}

	public final void setSalary(String salary) {
		put("salary", salary);
		this.salary = salary;
	}

	
}
