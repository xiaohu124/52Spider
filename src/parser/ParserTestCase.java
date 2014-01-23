package parser;

import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import junit.framework.TestCase;

public class ParserTestCase extends TestCase {
	private static final Logger logger = Logger.getLogger(ParserTestCase.class);

	public ParserTestCase(String name) {
		super(name);
	}

	/**
	 * 测试对<table>
	 * <tr>
	 * <td></td>
	 * </tr>
	 * </table>的解析
	 */
	public void testTable() {
		Parser myParser;
		NodeList nodeList = null;
		myParser = Parser
				.createParser(
						"<body> "
								+ "<table id=’table1′ >"
								+ "<tr id='tro1'><td>1-11</td><td>1-12</td><td>1-13</td></tr>"
								+ "<tr id='tro2'><td>1-21</td><td>1-22</td><td>1-23</td></tr>"
								+ "<tr id='tro3'><td>1-31</td><td>1-32</td><td>1-33</td></tr></table>"
								+ "<table id=’table2′ >"
								+ "<tr id='tro4'><td>2-11</td><td>2-12</td><td>2-13</td></tr>"
								+ "<tr id='tro5'><td>2-21</td><td>2-22</td><td>2-23</td></tr>"
								+ "<tr id='tro6'><td>2-31</td><td>2-32</td><td>2-33</td></tr></table>"
								+ "</body>", "GBK");
		NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { tableFilter });
		try {
			nodeList = myParser.parse(lastFilter);
			for (int i = 0; i <= nodeList.size(); i++) {
				if (nodeList.elementAt(i) instanceof TableTag) {
					TableTag tag = (TableTag) nodeList.elementAt(i);
					TableRow[] rows = tag.getRows();

					for (int j = 0; j < rows.length; j++) {
						TableRow tr = (TableRow) rows[j];
						System.out.println(tr.getAttribute("id"));
						if (tr.getAttribute("id").equalsIgnoreCase("tro1")) {
							TableColumn[] td = tr.getColumns();
							for (int k = 0; k < td.length; k++) {

								// logger.fatal("<td>" +
								// td[k].toPlainTextString());
								System.out.println("<td>"
										+ td[k].toPlainTextString());
							}
						}

					}

				}
			}

		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到目标数据
	 * 
	 * @param url:目标url
	 * @throws Exception
	 */
	public static void getDatabyUrl(String url) throws Exception {
		Parser myParser = new Parser(url);
		NodeList nodeList = null;
		myParser.setEncoding("gb2312");
		NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { tableFilter });
		try {
			nodeList = myParser.parse(lastFilter);
			// 可以从数据table的size:19-21开始到结束
			for (int i = 15; i <= nodeList.size(); i++) {
				if (nodeList.elementAt(i) instanceof TableTag) {
					TableTag tag = (TableTag) nodeList.elementAt(i);
					TableRow[] rows = tag.getRows();
					for (int j = 0; j < rows.length; j++) {
						TableRow tr = (TableRow) rows[j];
						if (tr.getAttribute("id") != null
								&& tr.getAttribute("id").equalsIgnoreCase(
										"tr02")) {
							TableColumn[] td = tr.getColumns();
							// 对不起，没有你要查询的记录！
							if (td.length == 1) {
								System.out.println("对不起，没有你要查询的记录");
							} else {
								for (int k = 0; k < td.length; k++) {
									System.out.println("<td>内容："
											+ td[k].toPlainTextString().trim());
								}
							}

						}

					}

				}
			}

		} catch (ParserException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 测试已经得出有数据时table:22个，没有数据时table:19个
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			// getDatabyUrl("http://gd.12530.com/user/querytonebytype.do?field=tonecode&condition=619505000000008942&type=1006&pkValue=619505000000008942");
			getDatabyUrl("http://gd.12530.com/user/querytonebytype.do?field=tonecode&condition=619272000000001712&type=1006&pkValue=619272000000001712");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
