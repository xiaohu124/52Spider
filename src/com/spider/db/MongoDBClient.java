package com.spider.db;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDBClient {
	private static final String ip = "localhost";
	private static final int port = 27017;
	private MongoClient mongoClient = null;
	private DB db = null;

	public MongoDBClient(String dbname) {
		try {
			this.mongoClient =  new MongoClient(ip, port);
			this.db = this.mongoClient.getDB(dbname);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public final MongoClient getMongoClient() {
		return mongoClient;
	}

	public final void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	public final DB getDb() {
		return db;
	}

	public final void setDb(DB db) {
		this.db = db;
	}
}
