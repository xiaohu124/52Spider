package com.spider.db;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DBConnn {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("test");
		
		Set<String> colls = db.getCollectionNames();
		for (String s : colls) {
			System.out.println(s);
		}
		
		DBCollection coll = db.getCollection("position");
		
		//insert
		BasicDBObject doc = new BasicDBObject("userid", 2).append("name", "aaa");
//		coll.insert(doc);
		
		//find
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);
		
		//adding multiple documents
		for (int i = 0; i < 100; i++) {
//			coll.insert(new BasicDBObject("userid", 3 + i).append("name", "aaa" + i));
		}
		
		System.out.println(coll.getCount());
		
		DBCursor cursor = coll.find();
		try {
			while(cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}
	}

}
