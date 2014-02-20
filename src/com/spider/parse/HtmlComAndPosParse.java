package com.spider.parse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.htmlparser.Tag;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;

import com.mongodb.DBCollection;
import com.spider.bean.Position;
import com.spider.crawler.SingleCrawler;
import com.spider.db.DBConnection;

public class HtmlComAndPosParse {
	public static List<Div> getComAndPosDiv(String content, String encode) {
		try {
			NodeList list = HtmlNodeParser.getTagsOfContent(content, encode, Div.class);
			return HtmlNodeParser.getDivTags(list, "class", "s_txt_jobs");
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static HashMap<String, String> getComyAndPosDetailByUrl(String url, String encode) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
	
		String content = SingleCrawler.getContentOfPage(url);
		
		map.put("pos_id", TagObjectBuilder.getPosMapFromURL(url).getPos_id() + "");
		map.putAll(getComyAndPosDetail(content, encode));
		return map;
	}
	
	public static HashMap<String, String> getComyAndPosDetail(String content, String encode) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		
		NodeList nodeList = HtmlNodeParser.getTagsOfContent(content, "GBK", TableTag.class);
		if (nodeList.size() >= 2) {
			map.putAll(parseCompanyDetail(HtmlNodeParser.getTableTags(nodeList, "class", "jobs_1").get(0)));
			map.putAll(parsePosDetail(HtmlNodeParser.getTableTags(nodeList, "class", "jobs_1").get(2)));
		}
		
		NodeList pnodeList = HtmlNodeParser.getTagsOfContent(content, "GBK", ParagraphTag.class);
		List<ParagraphTag> list = HtmlNodeParser.getParagraphTags(pnodeList, "class", "txt_font1");
		for (int i = 0; i < list.size(); i++) {
			map.putAll(parseDetail(list.get(i)));
		}
	
		return map;
	}
	
	public static HashMap<String, String> parseDetail(ParagraphTag tag) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		if (tag.toPlainTextString().startsWith("��˾��վ")) {
			map.put("webSite", tag.toPlainTextString().split("��")[1].trim());
		} 
		if (tag.toPlainTextString().startsWith("��&nbsp;")) {
			map.put("address", tag.toPlainTextString().split("��")[1].split("\\&")[0].trim());
		} 
		if (tag.toPlainTextString().startsWith("��������")) {
			map.put("postcode", tag.toPlainTextString().split("��")[1].trim());
		} 
		return map;
	}
	
	public static HashMap<String, String> parseCompanyDetail(TableTag tag) {
		HashMap<String, String> map = new HashMap<String, String>();
		TableRow[] rows = tag.getRows();
		for (int i = 0; i < rows.length; i++) {
			TableRow tr = (TableRow)rows[i];
			TableColumn[] td = tr.getColumns();
			if (td.length >= 1) {
				switch (i) {
				case 0:
					map.put("pos_name", td[0].toPlainTextString().trim());
					break;
				case 1:
					map.put("com_name", getComyName(td[0].toPlainTextString().trim()));
					break;
				case 2:
					map.putAll(getComyDepDetail(td[0].toHtml().trim()));
					break;
				default:
					break;
				}
			}
		}
		return map;
	}
	
	public static HashMap<String, String> parsePosDetail(TableTag tag) {
		HashMap<String, String> map = new HashMap<String, String>();
		TableRow[] rows = tag.getRows();
		for (int i = 0; i < rows.length - 2; i++) {
			TableRow tr = (TableRow)rows[i];
			TableColumn[] td = tr.getColumns();
			for (int j = 0; j < td.length; j++) {
				String elem = td[j].toPlainTextString().replaceAll("&nbsp;", "").trim();
				if (elem.equals("�������ڣ�")) {
					map.put("pub_time", td[j + 1].toPlainTextString().replaceAll("&nbsp;", "").trim());
					j++;
					continue;
				}
				if (elem.equals("�����ص㣺")) {
					map.put("pos_address", td[j + 1].toPlainTextString().replaceAll("&nbsp;", "").trim());
					j++;
					continue;
				}
				if (elem.equals("��Ƹ������")) {
					map.put("pos_num", td[j + 1].toPlainTextString().replaceAll("&nbsp;", "").trim());
					j++;
					continue;
				}
				if (elem.equals("�������ޣ�")) {
					map.put("pos_exp", td[j + 1].toPlainTextString().replaceAll("&nbsp;", "").trim());
					j++;
					continue;
				}
				if (elem.equals("ѧ����")) {
					map.put("degree", td[j + 1].toPlainTextString().replaceAll("&nbsp;", "").trim());
					j++;
					continue;
				}
				if (elem.startsWith("нˮ")) {
					map.put("salary", td[j + 1].toPlainTextString().replaceAll("&nbsp;", "").trim());
					j++;
					continue;
				}
				if (elem.startsWith("ְλְ��")) {
					map.put("pos_fun", td[j].toPlainTextString().replaceAll("&nbsp;", "").replaceAll("ְλְ��:", "").trim());
					continue;
				}
				if (elem.startsWith("ְλ��ǩ")) {
					map.put("pos_tags", td[j].toPlainTextString().replaceAll("&nbsp;", "").replaceAll("ְλ��ǩ:", "").trim());
					continue;
				}
				if (elem.startsWith("ְλ����")) {
					map.put("pos_desc", td[j].toPlainTextString().replaceAll("&nbsp;", "").replaceAll("ְλ����:", "").trim());
				}
			}
			
		}
		return map;
	}
	
	public static String getComyName(String str) {
		return str.split("&nbsp;")[0].trim();
	}
	
	public static HashMap<String, String> getComyDepDetail(String str) {
		HashMap<String, String> map = new HashMap<String, String>();
		str = str.replaceAll("<td>", " ").replaceAll("</td>", " ");
		str = str.replaceAll("<strong>", " ").replaceAll("</strong>", "|");
		str = str.replaceAll("<br><br>", "|").replaceAll("&nbsp;", " ");
		String[] arr = str.split("\\|");
		if (arr.length >= 2 && "��˾��ҵ��".equals(arr[0].trim())) {
			map.put("industry", arr[1].trim());
		} 
		if (arr.length >= 4 && "��˾���ʣ�".equals(arr[2].trim())) {
			map.put("property", arr[3].trim());
		}
		if (arr.length >= 6 && "��˾��ģ��".equals(arr[4].trim())) {
			if ("����50��".equals(arr[5].trim())) {
				map.put("minScale", "0");
				map.put("maxScale", "50");
			} else {
				String[] scales = arr[5].trim().split("-");
				map.put("minScale", scales[0]);
				map.put("maxScale", scales[1].substring(0, scales[1].length() - 1));
			}
		}
		return map;
	}
	
	public static void main(String[] args) throws Exception {
		String url = "http://search.51job.com/job/45395515,c.html";
		System.out.println(HtmlComAndPosParse.getComyAndPosDetailByUrl(url, "GBK").toString());
	}
}
