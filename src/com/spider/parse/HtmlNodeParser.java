package com.spider.parse;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.LinkRegexFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;

public class HtmlNodeParser {
	/**
	 * 获取表格中数据
	 */
	public static NodeList getTagsOfContent(String content, String encode, Class tagClass) throws Exception {
		Parser parser = Parser.createParser(content, encode);
		
		NodeFilter tableFilter = new NodeClassFilter(tagClass);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { tableFilter });

		return parser.extractAllNodesThatMatch(lastFilter);
	}
	
	public static List<TableTag> getTableTags(NodeList nodeList, String attributeName, String attributeValue) {
		List<TableTag> tag = new ArrayList<TableTag>();
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.elementAt(i) instanceof TableTag) {
				TableTag tmp = (TableTag) nodeList.elementAt(i);
				if (isRightElement(tmp, attributeName, attributeValue)) {
					tag.add(tmp);
				}
			}
		}
		return tag;
	}
	
	public static List<ParagraphTag> getParagraphTags(NodeList nodeList, String attributeName, String attributeValue) {
		List<ParagraphTag> tag = new ArrayList<ParagraphTag>();
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.elementAt(i) instanceof ParagraphTag) {
				ParagraphTag tmp = (ParagraphTag) nodeList.elementAt(i);
				if (isRightElement(tmp, attributeName, attributeValue)) {
					tag.add(tmp);
				}
			}
		}
		return tag;
	}
	
	public static List<Div> getDivTags(NodeList nodeList, String attributeName, String attributeValue) {
		List<Div> tag = new ArrayList<Div>();
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.elementAt(i) instanceof Div) {
				Div tmp = (Div) nodeList.elementAt(i);
				if (isRightElement(tmp, attributeName, attributeValue)) {
					tag.add(tmp);
				}
			}
		}
		return tag;
	}
	
	public static boolean isRightElement(Tag tag, String attributeName, String attributeValue) {
		String value = tag.getAttribute(attributeName);
		return null != value && value.equalsIgnoreCase(attributeValue);
	}
	
	/**
     * 在链接地址中进行正则匹配,返回的是Link结点
     * 
     * @param url        请求url
     * @param encoding    字符编码
     * @param regex        待匹配的正则表达式
     */
    public static List<String> linkTagRegexFilter(String url, String encoding, String regex){
    	List<String> urlList = new ArrayList<String>();
        try {
            Parser parser = new Parser();
            parser.setURL(url);
            if(null == encoding) {
                parser.setEncoding(parser.getEncoding());
            }else{
                parser.setEncoding(encoding);
            }
            //OrFilter是结合几种过滤条件的‘或’过滤器
            NodeFilter filter = new LinkRegexFilter(regex);
            NodeList list = parser.extractAllNodesThatMatch(filter);
            for (int i = 0; i < list.size(); i++) {
            	Node node = list.elementAt(i);
            	LinkTag linkTag = new LinkTag();
            	linkTag.setText(node.toHtml());
            	urlList.add(linkTag.extractLink());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlList;
    }
    
}
