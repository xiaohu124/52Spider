package com.spider.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.spider.bean.Position;

public class DBConnection {
	private MongoClient client = null;
	private DB mdc = null;
	private DBCollection dbc = null;
	
	public DBConnection() {
		//∂¡»°≈‰÷√Œƒº˛Config.properties
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Config.properties");   
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String ip = p.getProperty("ip");
		int port = Integer.parseInt(p.getProperty("port"));
		String dbname = p.getProperty("database");
		this.client = new MongoDBClient(ip, port).getMongoClient();
		this.mdc = this.client.getDB(dbname);
	}
	
	public void setCollection(String coll) {
		this.dbc = this.mdc.getCollection(coll);
	}
	
	public void insert(BasicDBObject o) {
		if (this.dbc != null) {
			this.dbc.insert(o);
		}
	}
	
	public void insertAll(List<BasicDBObject> list) {
		if (this.dbc == null) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			this.dbc.insert(list.get(i));
		}
	}
	
	public boolean isRepeat(long id) {
		this.setCollection("position");
		Position pos = new Position();
		pos.setPos_id(id);
		DBObject dbo = this.dbc.findOne((BasicDBObject)pos);
		if (dbo.containsField("posid")) {
			return true;
		}
		return true;
	}

	public void closeConnection() {
		this.client.close();
	}
	
	public final MongoClient getClient() {
		return client;
	}

	public final void setClient(MongoClient client) {
		this.client = client;
	}

	public final DB getMdc() {
		return mdc;
	}

	public final void setMdc(DB mdc) {
		this.mdc = mdc;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DBConnection dbc = new DBConnection();
		DBCollection coll = dbc.getMdc().getCollection("position");
		Position pos = new Position(1, "c", "http://www.baidu.com");
		Position pos1 = new Position(2, "c", "http://www.baidu.com");
		
		System.out.println(coll.findOne());
		dbc.closeConnection();
	}

}
