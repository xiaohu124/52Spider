package com.spider.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;

public class HtmlTxtParser {
	/**
	 * ��ȡ���������
	 */
	public String getValuesOfTableContent(String content, String encode) throws Exception {
		List list = new ArrayList<Map<String, String>>();
		
		Parser parser = Parser.createParser(content, encode);
		
		NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { tableFilter });
		
		NodeList nodeList = parser.extractAllNodesThatMatch(lastFilter);
		StringBuffer text = new StringBuffer();
		System.out.println(nodeList.size());
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.elementAt(i) instanceof TableTag) {
				System.out.println(i);
				TableTag tag = (TableTag) nodeList.elementAt(i);
				System.out.println(tag.getIds().length);
			}
			
			Node node = nodeList.elementAt(i);
			//�ַ����Ĵ����Խڵ�:�ڵ������
			text.append(new String(node.toPlainTextString().getBytes(encode))
					+ "\r\n");
		}
		return text.toString();
	}
	
	/**
	 * ��ȡ���ӵ�ַ,�������
	 * @return
	 */
	public List<Map<Long, String>> getValuesOfLinkContent(String content, String url, String encode) {
		List list = new ArrayList<Map<Long, String>>();
		try {
			Parser parser = Parser.createParser(content, encode);
			
			//add Filter
			NodeFilter filter = new RegexFilter(regx);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
		}
		
		return list;
	}
	
	/**
     * ��ȡ���ı���Ϣ
     * @param inputHtml��html�ı�
     * @return
     * @throws Exception
     */
	public static String extractText(String inputHtml) throws Exception {
		StringBuffer text = new StringBuffer();
		Parser parser = Parser.createParser(new String(inputHtml.getBytes(),
				"GBK"), "GBK");
		// �������еĽڵ�
		NodeList nodes = parser.extractAllNodesThatMatch(new NodeFilter() {
			public boolean accept(Node node) {
				return true;
			}
		});

		System.out.println(nodes.size());
		for (int i = 0; i < nodes.size(); i++) {
			Node nodet = nodes.elementAt(i);
			//�ַ����Ĵ����Խڵ�:�ڵ������
			text.append(new String(nodet.toPlainTextString().getBytes("GBK"))
					+ "\r\n");
		}
		return text.toString();
	}

}
