package com.mpgl.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class RssUtil {

	private final static RssUtil rssUtil = new RssUtil();

	private RssUtil() {

	}

	public static RssUtil newInstance() {
		return rssUtil;
	}

	/**
	 * 解析URL返回整个文档的DOM对象
	 * 
	 * @param url
	 * @return
	 * @throws DocumentException
	 */
	public Document parse(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		return document;
	}

	/**
	 * 获取XML中节点信息,
	 * 
	 * @param url
	 *            URL(RSS订阅地址)
	 * @param tagPath
	 *            (节点名字或路径
	 * @return
	 */
	public List getInfo(URL url, String tagPath) {
		List info = new ArrayList();
		try {
			Document document = parse(url);
			info = document.selectNodes(tagPath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return info;
	}

	public Map<String, Object> getRssMap(String urlStr) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		URL url = new URL(urlStr);
		List titlList = this.getInfo(url, Constant.RSS.RSS_DOM_ROOT_TITLE);
		for (int i = 0; i < titlList.size(); i++) {
			Element titleElement = (Element) titlList.get(i);
			String title = titleElement.getText();
			returnMap.put(Constant.RSS.KEY_TITLE, title);
		}
		List items = this.getInfo(url, Constant.RSS.RSS_DOM_CHILRDEN_ROOT);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (items != null) {
			for (int i = 0; i < items.size(); i++) {
				Element element = (Element) items.get(i);
				Node titleNode = element
						.selectSingleNode(Constant.RSS.RSS_DOM_CHILRDEN_ROOT_TITLE);
				Node linkNode = element
						.selectSingleNode(Constant.RSS.RSS_DOM_CHILRDEN_ROOT_LINK);
				Map<String, Object> item = new HashMap<String, Object>();
				item.put(Constant.RSS.KEY_NODE_TITLE, titleNode.getText());
				item.put(Constant.RSS.KEY_NODE_LINK, linkNode.getText());
				list.add(item);
			}
		}
		returnMap.put(Constant.RSS.KEY_ROWS, list);
		return returnMap;

	}

	public List<Map<String, Object>> getRssList(String urlStr) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		URL url = new URL(urlStr);
		List items = this.getInfo(url, Constant.RSS.RSS_DOM_CHILRDEN_ROOT);
		if (items != null) {
			for (int i = 0; i < items.size(); i++) {
				Element element = (Element) items.get(i);
				Node titleNode = element
						.selectSingleNode(Constant.RSS.RSS_DOM_CHILRDEN_ROOT_TITLE);
				Node linkNode = element
						.selectSingleNode(Constant.RSS.RSS_DOM_CHILRDEN_ROOT_LINK);
				Map<String, Object> item = new HashMap<String, Object>();
				item.put(Constant.RSS.KEY_NODE_TITLE, titleNode.getText());
				item.put(Constant.RSS.KEY_NODE_LINK, linkNode.getText());
				list.add(item);
			}
		}
		return list;
	}
	
	public String getRssRootLink(String urlStr) throws Exception {
		String link = "#";
		List items = this.getInfo(new URL(urlStr), Constant.RSS.RSS_DOM_ROOT_LINK);
		if (items != null) {
			for (int i = 0; i < items.size(); i++) {
				Element element = (Element) items.get(i);
				link = element.getText();
			}
		}
		return link;
	}

	// public Element getFirstNodeTitle(String path, URL url) {
	// List list = getInfo(url, path);
	// Element element = (Element) list.get(0);
	// return element;
	// }

	public static void main(String[] args) throws Exception {
		RssUtil xu = new RssUtil();
		URL url;
		url = new URL("http://news.163.com/special/00011K6L/rss_newstop.xml");
		xu.getRssMap("http://news.163.com/special/00011K6L/rss_newstop.xml");
		// List list = xu.getXmlInfo(RSS_DOM_CHILRDEN_ROOT, url);
		// for (Iterator iter = list.iterator(); iter.hasNext();) {
		// Element element = (Element) iter.next();
		// Node node = element
		// .selectSingleNode(RSS_DOM_CHILRDEN_ROOT_TITLE);
		// Node link = element
		// .selectSingleNode(RSS_DOM_CHILRDEN_ROOT_LINK);
		// System.out.println(node.getText() + link.getText());
		// }

	}
}
