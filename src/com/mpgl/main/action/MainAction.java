package com.mpgl.main.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.mpgl.base.BaseAction;
import com.mpgl.main.service.MainService;
import com.mpgl.poi.ExcelUtil;
import com.mpgl.util.CommonUtil;
import com.mpgl.util.Constant;
import com.mpgl.util.RssUtil;
import com.mpgl.vo.Form;
import com.mpgl.vo.MenuVo;
import com.mpgl.vo.RoleVo;
import com.mpgl.vo.RssVo;
import com.mpgl.vo.UserVo;

/**
 * 核心Action
 * 
 * @author user
 * 
 */
@SuppressWarnings({ "serial" })
public class MainAction extends BaseAction {

	/**
	 * 日志
	 */
	private static Logger log = Logger.getLogger(MainAction.class);

	/**
	 * 核心Service
	 */
	private MainService mainService;

	/* 用户相关属性 */

	/**
	 * UserVo
	 */
	private UserVo user;

	/**
	 * 单个用户数据
	 */
	private Map<String, Object> userMap;

	/**
	 * DataGrid用户数据
	 */
	private Map<String, Object> userGrid;

	/**
	 * RssVo
	 */
	private RssVo rss;

	/**
	 * DataGrid RSS数据
	 */
	private Map<String, Object> rssGrid;

	/**
	 * 单个RSS数据
	 */
	private Map<String, Object> rssMap;

	/**
	 * 前台展示数据数组
	 */
	private List<Map<String, Object>> rssList;

	/**
	 * 
	 */
	private RoleVo role;

	/**
	 * 
	 */
	private Map<String, Object> roleMap;

	/**
	 * 
	 */
	private Map<String, Object> roleGrid;

	/**
	 * 字典表数据
	 */
	private List<Map<String, Object>> dictionaryList;

	/**
	 * 下拉列表数据
	 */
	private List<Map<String, Object>> comboboxs;

	/**
	 * Combobox角色列表数据
	 */
	private List<Map<String, Object>> roleCombobox;

	/* 文件上传用到的相关属性 */

	private File excel;

	private String excelFileName;

	private String excelContentType;

	/* 导出文件相关属性 */
	/**
	 * 返回的文件输入流
	 */
	private InputStream ins;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 状态码
	 */
	private String code = Constant.CODE_EOF;

	/**
	 * 跳转到欢迎界面
	 * 
	 * @return
	 */
	public String goWelcome() {
		return SUCCESS;
	}

	/*-----------登录相关--------------*/
	/**
	 * 跳转到登录界面
	 * 
	 * @return
	 */
	public String goLogin() {
		session.remove(Constant.LOGIN);
		return SUCCESS;
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	public String goLoginOut() {
		session.remove(Constant.LOGIN);
		session.remove(Constant.USER);
		return SUCCESS;
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	public String login() {
		user = mainService.login(form);
		if (user == null) {
			session.put(Constant.LOGIN, "用户名或者密码错误!");
			return INPUT;
		}
		session.put(Constant.USER, user);
		RoleVo role = mainService.getRoleByUser(user);
		List<MenuVo> menus = mainService.getMenuByRole(role);
		role.setMenuList(menus);
		user.setRoleVo(role);
		return SUCCESS;
	}

	/**
	 * 跳转客户管理界面
	 * 
	 * @return
	 */
	public String goRssManager(){
		return SUCCESS;
	}

	/**
	 * 跳转网站展示首页
	 * 
	 * @return
	 */
	public String goIndex() throws Exception {
		rssList = mainService.queryRssList(this.getForm());
		if(rssList != null) {
			for(Map<String, Object> map : rssList) {
				String url = map.get("url") != null && !"".equals(map.get("url")) ? map.get("url").toString() : null;
				if(url != null) {
					map.put(Constant.RSS.KEY_ROWS, RssUtil.newInstance().getRssList(url));
					map.put(Constant.RSS.KEY_LINK, RssUtil.newInstance().getRssRootLink(url));
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * queryRss列表
	 * 
	 * @return
	 */
	public String queryRssDataGridByJson() {
		rssGrid = mainService.queryRssDataGrid(this.getForm());
		return SUCCESS;
	}

	/**
	 * 查询单个Rss信息
	 * 
	 * @return
	 */
	public String queryRssByJson() {
		rssMap = mainService.queryRss(this.form);
		return SUCCESS;
	}

	/**
	 * 新增Rss数据
	 * 
	 * @return
	 */
	public String doAddRss() {
		UserVo user = (UserVo) session.get(Constant.USER);
		rss.setUser_id(user.getId());
		mainService.addRss(rss);
		code = Constant.CODE_WIN;
		return SUCCESS;
	}

	/**
	 * 更新RSS数据
	 * 
	 * @return
	 */
	public String doUpdateRss() {
		UserVo user = (UserVo) session.get(Constant.USER);
		rss.setUser_id(user.getId());
		mainService.updateRss(rss);
		code = Constant.CODE_WIN;
		return SUCCESS;
	}

	/**
	 * 删除Rss数据
	 * 
	 * @return
	 */
	public String doDeleteRss() {
		mainService.deleteRss(rss);
		code = Constant.CODE_WIN;
		return SUCCESS;
	}

	/*-----------系统管理相关--------------*/
	/**
	 * 跳转用户管理界面
	 * 
	 * @return
	 */
	public String goUserManager() {
		return SUCCESS;
	}

	/**
	 * 新增用户数据
	 * 
	 * @return
	 */
	public String doAddUser() {
		if (mainService.checkUserName(user)) {
			user.setCreate_time(Constant.DEFAULT_DATE_FORMAT.format(new Date()));
			mainService.addUser(user);
			code = Constant.CODE_WIN;
		} else {
			code = "用户帐号已存在";
		}
		return SUCCESS;
	}

	/**
	 * 更新用户数据
	 * 
	 * @return
	 */
	public String doUpdateUser() {
		user.setUpdate_time(Constant.DEFAULT_DATE_FORMAT.format(new Date()));
		mainService.updateUser(user);
		code = Constant.CODE_WIN;
		return SUCCESS;
	}

	/**
	 * 删除用户数据
	 * 
	 * @return
	 */
	public String doDeleteUser() {
		mainService.deleteUser(user);
		code = Constant.CODE_WIN;
		return SUCCESS;
	}

	/**
	 * 查询单个用户信息[JSON]
	 * 
	 * @return
	 */
	public String queryUserByJson() {
		userMap = mainService.queryUser(this.form);
		return SUCCESS;
	}

	/**
	 * 查询用户列表[JSON]
	 * 
	 * @return
	 */
	public String queryUserDataGridByJson() {
		userGrid = mainService.queryUserDataGrid(this.getForm());
		return SUCCESS;
	}

	/**
	 * 查询角色列表[JSON]
	 * 
	 * @return
	 */
	public String queryRoleListByJson() {
		roleCombobox = mainService.queryRoleList();
		return SUCCESS;
	}

	/**
	 * 跳转角色管理界面
	 * 
	 * @return
	 */
	public String goRoleManager() {
		return SUCCESS;
	}

	/**
	 * 查询角色列表
	 * 
	 * @return
	 */
	public String queryRoleDataGridByJson() {
		roleGrid = mainService.queryRoleDataGrid(this.getForm());
		return SUCCESS;
	}

	/**
	 * 查询单个角色信息
	 * 
	 * @return
	 */
	public String queryRoleByJson() {
		roleMap = mainService.queryRole(this.form);
		return SUCCESS;
	}

	/**
	 * 新增角色数据
	 * 
	 * @return
	 */
	public String doAddRole() {
		mainService.addRole(role);
		code = Constant.CODE_WIN;
		return SUCCESS;
	}

	/**
	 * 更新角色数据
	 * 
	 * @return
	 */
	public String doUpdateRole() {
		mainService.updateRole(role);
		code = Constant.CODE_WIN;
		return SUCCESS;
	}

	/**
	 * 删除角色数据
	 * 
	 * @return
	 */
	public String doDeleteRole() {
		mainService.deleteRole(role);
		code = Constant.CODE_WIN;
		return SUCCESS;
	}

	/**
	 * 查询字典表数据[JSON]
	 * 
	 * @return
	 */
	public String queryDictionaryByJson() {
		dictionaryList = mainService.queryDictionaryByKey(form);
		return SUCCESS;
	}

	/**
	 * 查询SELECT控件数据
	 * 
	 * @return
	 */
	public String querySelectByJson() {
		comboboxs = mainService.queryCombobx(form);
		return SUCCESS;
	}

	/**
	 * 查询用户密码
	 * 
	 * @return
	 */
	public String doQueryPwd() {
		user = (UserVo) session.get(Constant.USER);
		return SUCCESS;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String doUpdatePassword() {
		try {
			UserVo user = (UserVo) session.get(Constant.USER);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("password", this.getForm().getPassword());
			mainService.updateUserPassword(map);
			user.setPassword(this.getForm().getPassword());
			code = Constant.CODE_WIN;
		} catch (Exception e) {
			log.error("修改密码错误:", e);
			code = Constant.CODE_EOF;
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public MainService getMainService() {
		return mainService;
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getExcelContentType() {
		return excelContentType;
	}

	public void setExcelContentType(String excelContentType) {
		this.excelContentType = excelContentType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, Object> getUserGrid() {
		return userGrid;
	}

	public void setUserGrid(Map<String, Object> userGrid) {
		this.userGrid = userGrid;
	}

	public List<Map<String, Object>> getRoleCombobox() {
		return roleCombobox;
	}

	public void setRoleCombobox(List<Map<String, Object>> roleCombobox) {
		this.roleCombobox = roleCombobox;
	}

	public Map<String, Object> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, Object> userMap) {
		this.userMap = userMap;
	}

	public List<Map<String, Object>> getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(List<Map<String, Object>> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		MainAction.log = log;
	}

	public InputStream getIns() {
		return ins;
	}

	public void setIns(InputStream ins) {
		this.ins = ins;
	}

	public String getFileName() {
		try {
			fileName = new String(fileName.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			log.error("导出文件名转码错误", e);
		}
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public RoleVo getRole() {
		return role;
	}

	public void setRole(RoleVo role) {
		this.role = role;
	}

	public Map<String, Object> getRoleMap() {
		return roleMap;
	}

	public void setRoleMap(Map<String, Object> roleMap) {
		this.roleMap = roleMap;
	}

	public Map<String, Object> getRoleGrid() {
		return roleGrid;
	}

	public void setRoleGrid(Map<String, Object> roleGrid) {
		this.roleGrid = roleGrid;
	}

	public List<Map<String, Object>> getComboboxs() {
		return comboboxs;
	}

	public void setComboboxs(List<Map<String, Object>> comboboxs) {
		this.comboboxs = comboboxs;
	}

	public RssVo getRss() {
		return rss;
	}

	public void setRss(RssVo rss) {
		this.rss = rss;
	}

	public Map<String, Object> getRssGrid() {
		return rssGrid;
	}

	public void setRssGrid(Map<String, Object> rssGrid) {
		this.rssGrid = rssGrid;
	}

	public Map<String, Object> getRssMap() {
		return rssMap;
	}

	public void setRssMap(Map<String, Object> rssMap) {
		this.rssMap = rssMap;
	}

	public List<Map<String, Object>> getRssList() {
		return rssList;
	}

	public void setRssList(List<Map<String, Object>> rssList) {
		this.rssList = rssList;
	}

}
