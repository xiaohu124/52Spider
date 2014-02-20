package com.spider.db;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDBClient {
	private static final String ip = "localhost";
	private static final int port = 27017;
	private MongoClient mongoClient = null;

	public MongoDBClient(String ip, int port) {
		try {
			this.mongoClient =  new MongoClient(ip, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		this.mongoClient.close();
	}

	public final MongoClient getMongoClient() {
		return mongoClient;
	}

	public final void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}
}
