package com.spider.db;

import java.util.HashMap;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.spider.bean.Position;
import com.spider.parse.HtmlComAndPosParse;
import com.spider.parse.TagObjectBuilder;

public class DBOperator {
	private DBConnection dbc = null;
	public DBOperator() {
		dbc = new DBConnection();
	}
	public void shutdown() {
		dbc.closeConnection();
	}
 	public void insertPos(Position pos) {
		this.dbc.setCollection("position");
		this.dbc.insert((BasicDBObject)pos);
	}
 	
 	public void insertPosList(List<String> list) {
 		this.dbc.setCollection("position");
		this.dbc.insertAll(TagObjectBuilder.getPosObjects(list));
 	}
 	
 	public boolean isRepeatPos(long id) {
 		return this.dbc.isRepeat(id);
 	}
 	
 	public void insertCompany(HashMap<String, String> map) {
 		this.dbc.setCollection("company");
		this.dbc.insert((BasicDBObject)TagObjectBuilder.getCompanyDetailObj(map));
 	}
 	
 	public void insertPosDetail(HashMap<String, String> map) {
 		this.dbc.setCollection("position_detail");
		this.dbc.insert((BasicDBObject)TagObjectBuilder.getPositionDetailObj(map));
 	}
 	
 	public void insertComAndPosDetail(String url, String encode) {
 		try {
			HashMap<String, String> map = HtmlComAndPosParse.getComyAndPosDetailByUrl(url, encode);
			this.insertCompany(map);
			this.insertPosDetail(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 	}
}
