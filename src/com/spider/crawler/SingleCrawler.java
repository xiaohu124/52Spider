package com.spider.crawler;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class SingleCrawler {
	private static HttpClient httpclient = new DefaultHttpClient();
	
	private HttpGet httpGet = null;
	
	public SingleCrawler(String url){
		httpGet = new HttpGet(url);
	}
	
	public void CloseCrawler() {
		httpGet.releaseConnection();
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
				return EntityUtils.toString(entity, "GB2312");
	        }
		} else {
			System.out.println("Login form get: " + response.getStatusLine());
		}
        return "";
        
	}
}
