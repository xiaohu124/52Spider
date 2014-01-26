package com.spider.db;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class MongoBean {
	private MongoDBClient mdc = null;
	private DBCollection dbc = null;
	
	public MongoBean(String dbname, String collectionName, BasicDBObject o) {
		mdc = new MongoDBClient(dbname);
		dbc = mdc.getDb().getCollection(collectionName);
	}
	
	public void insert(DBObject object) {
		dbc.insert(object);
	}
	
	public void findOne() {
	}
	
	public void update() {
		
	}
	
	public void delte() {
		
	}
}
