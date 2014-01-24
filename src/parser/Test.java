package parser;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.LinkRegexFilter;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class Test {
	/**
     * ���ı���ͨ���������ƥ��
     * 
     * @param url        �������url
     * @param encoding    �ַ�����
     * @param regex        ��ƥ���������ʽ
     */
    public static void regexStringFilter(String url,String encoding,String regex){
        try {
            Parser parser = new Parser();
            parser.setURL(url);
            if(null==encoding){
                parser.setEncoding(parser.getEncoding());
            }else{
                parser.setEncoding(encoding);
            }
            //OrFilter�ǽ�ϼ��ֹ��������ġ��򡯹�����
            NodeFilter filter = new RegexFilter(regex);
            NodeList list = parser.extractAllNodesThatMatch(filter);
            for(int i=0; i<list.size();i++){
                Node node = (Node)list.elementAt(i);
                System.out.println("link is :" + node.toHtml());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * �����ӵ�ַ�н�������ƥ��,���ص���Link���
     * 
     * @param url        ����url
     * @param encoding    �ַ�����
     * @param regex        ��ƥ���������ʽ
     */
    public static void linkTagRegexFilter(String url,String encoding,String regex){
        try {
            Parser parser = new Parser();
            parser.setURL(url);
            if(null==encoding){
                parser.setEncoding(parser.getEncoding());
            }else{
                parser.setEncoding(encoding);
            }
            //OrFilter�ǽ�ϼ��ֹ��������ġ��򡯹�����
            NodeFilter filter = new LinkRegexFilter(regex);
            NodeList list = parser.extractAllNodesThatMatch(filter);
            for(int i=0; i<list.size();i++){
            	Node node = list.elementAt(i);
            	LinkTag linkTag = new LinkTag();
            	linkTag.setText(node.toHtml());
            	System.out.println(linkTag.extractLink());
                System.out.println("link is :" + node.toHtml());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        
        String url =  "D:/email.txt";
//
//        String emailRegex = "[a-zA-Z0-9_-]+@\\w+\\.[a-z]+(\\.[a-z]+)?";  
//        regexStringFilter(url, "GB2312", emailRegex);
//        System.out.println("-------------------------------------------");
//        String htmlregx = "http";
//        regexStringFilter(url, "GB2312", htmlregx);
        
        System.out.println("-------------------------------------------");
        String regx = "http://search\\.51job\\.com/job/[0-9|a-z|,]*\\.html";
        linkTagRegexFilter(url, "GB2312", regx);
        
    }
}
