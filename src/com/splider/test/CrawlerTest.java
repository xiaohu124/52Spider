package com.splider.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class CrawlerTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		HttpGet httpget = new HttpGet("http://search.51job.com/jobsearch/index.php?lang=c&stype=1");
		HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        
        if (entity != null) {
//        	InputStream instream = entity.getContent();
//        	System.out.println(entity.getContentType()); 
//        	System.out.println(entity.getContentLength());
        	System.out.println(EntityUtils.toString(entity));
        }
        
        System.out.println("Login form get: " + response.getStatusLine());
        if (entity != null) {
            entity.consumeContent();
        }
        System.out.println("Initial set of cookies:");
        List<Cookie> cookies = httpclient.getCookieStore().getCookies();
        if (cookies.isEmpty()) {
            System.out.println("None");
        } else {
            for (int i = 0; i < cookies.size(); i++) {
                System.out.println("- " + cookies.get(i).toString());
            }
        }

        httpget.releaseConnection();

//        HttpPost httpost = new HttpPost("https://portal.sun.com/amserver/UI/Login?" +
//                "org=self_registered_users&" +
//                "goto=/portal/dt&" +
//                "gotoOnFail=/portal/dt?error=true");
//
//        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
//        nvps.add(new BasicNameValuePair("IDToken1", "username"));
//        nvps.add(new BasicNameValuePair("IDToken2", "password"));
//
//        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
//
//        response = httpclient.execute(httpost);
//        entity = response.getEntity();
//
//        System.out.println("Login form get: " + response.getStatusLine());
//        if (entity != null) {
//            entity.consumeContent();
//        }
//
//        System.out.println("Post logon cookies:");
//        cookies = httpclient.getCookieStore().getCookies();
//        if (cookies.isEmpty()) {
//            System.out.println("None");
//        } else {
//            for (int i = 0; i < cookies.size(); i++) {
//                System.out.println("- " + cookies.get(i).toString());
//            }
//        }
	}

}
