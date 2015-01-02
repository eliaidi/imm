package com.mpgl.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.mpgl.main.service.MainService;
import com.mpgl.vo.Form;

/**
 * 工具类
 * 
 * @author Administrator
 * 
 */
public class CommonUtil {

	/**
	 * 上下文
	 */
	private static WebApplicationContext wc = ContextLoader
			.getCurrentWebApplicationContext();

	/**
	 * 核心service
	 */
	private static MainService service = (MainService) wc
			.getBean("MainService");

	/**
	 * JSON处理对象
	 */
	private static ObjectMapper objectMapper;

	/**
	 * @Title: getObjectMapper
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return ObjectMapper 返回类型
	 * @throws
	 */
	public static ObjectMapper getObjectMapper() {
		if (null == objectMapper) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper;
	}

	/**
	 * 转换为JSON格式的字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String JSONString(Object value) {
		ObjectMapper obje = CommonUtil.getObjectMapper();
		Writer wr = new StringWriter();
		String json = "";
		try {
			obje.writeValue(wr, value);
			json = wr.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wr != null) {
				try {
					wr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}

	/**
	 * 获取字典表数据
	 * 
	 * @return
	 */
	public static Object getDict() {
		Form form = new Form();
		return service.queryDictionaryByKey(form);
	}

	/**
	 * 获取指定表记录
	 * 
	 * @param table
	 * @param key
	 * @param value
	 * @return
	 */
	public static Object getTable(String table, String key, String value) {
		Form form = new Form();
		form.setComboboxTable(table);
		form.setComboboxKey(key);
		form.setComboboxValue(value);
		return service.queryCombobx(form);
	}

	/**
	 * 获取系统当前日期
	 * 
	 * @param format
	 *            指定格式
	 * @return
	 */
	public static String systemDate(String format) {
		String time = "";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		time = formatter.format(new Date());
		return time;
	}

	/**
	 * 获取系统当前日期
	 * 
	 * @return
	 */
	public static String systemDate() {
		String time = "";
		time = Constant.DEFAULT_DATE_FORMAT.format(new Date());
		return time;
	}

}
