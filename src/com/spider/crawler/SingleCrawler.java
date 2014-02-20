package com.spider.crawler;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SingleCrawler {
	public static String getContentOfPage(String url) throws IOException {
		String res = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);
			// Execute HTTP request
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// Get hold of the response entity
				HttpEntity entity = response.getEntity();
				// If the response does not enclose an entity, there is no need
				// to bother about connection release
				if (entity != null) {
					res = EntityUtils.toString(entity, "GB2312");
			        entity.consumeContent();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return res;
	}
	
	
	/*
	private static HttpClient httpclient = new DefaultHttpClient();
	private HttpGet httpGet = null;
	
	public SingleCrawler(String url){
		httpGet = new HttpGet(url);
	}
	
	public void CloseCrawler() {
		httpGet.releaseConnection();
		httpclient.getConnectionManager().shutdown();
	}
	
	public String getContentOfPage() throws ClientProtocolException, IOException {
		if (this.httpGet == null) {
			return "";
		}
		HttpResponse response = httpclient.execute(this.httpGet);
		StatusLine statusLine = response.getStatusLine();
		
		if(statusLine.getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String res = EntityUtils.toString(entity, "GB2312");
		        entity.consumeContent();
				return res;
	        }
		} else {
			System.out.println("Login form get: " + response.getStatusLine());
		}
        return "";
        
	}
	*/
	
}
