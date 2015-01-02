package com.mpgl.main.service;

import java.util.List;
import java.util.Map;

import com.mpgl.vo.Form;
import com.mpgl.vo.MenuVo;
import com.mpgl.vo.RoleVo;
import com.mpgl.vo.RssVo;
import com.mpgl.vo.UserVo;

/**
 * 核心Service接口
 * 
 * @author user
 * 
 */
public interface MainService {

	/**
	 * 用户登录
	 * 
	 * @param form
	 * @return UserVo 用户信息
	 */
	UserVo login(Form form);

	/**
	 * 检测用户账户是否存在
	 * 
	 * @param user
	 * @return
	 */
	boolean checkUserName(UserVo user);

	/**
	 * 修改用户密码
	 * 
	 * @param map
	 */
	void updateUserPassword(Map<String, Object> map);

	/**
	 * 获取用户的角色
	 * 
	 * @param user
	 * @return
	 */
	RoleVo getRoleByUser(UserVo user);

	/**
	 * 获取角色的菜单权限
	 * 
	 * @param role
	 * @return
	 */
	List<MenuVo> getMenuByRole(RoleVo role);

	/**
	 * 根据KEY查询字典表数据
	 * 
	 * @param form
	 * @return
	 */
	List<Map<String, Object>> queryDictionaryByKey(Form form);

	/**
	 * 查询单个用户信息
	 * 
	 * @param form
	 * @return
	 */
	Map<String, Object> queryUser(Form form);

	/**
	 * 查询用户列表
	 * 
	 * @param form
	 * @return
	 */
	Map<String, Object> queryUserDataGrid(Form form);

	/**
	 * 查询RSS列表
	 * 
	 * @param form
	 * @return
	 */
	Map<String, Object> queryRssDataGrid(Form form);
	
	List<Map<String, Object>> queryRssList(Form form);

	/**
	 * 查询单个RSS
	 * 
	 * @param form
	 * @return
	 */
	Map<String, Object> queryRss(Form form);

	void addRss(RssVo vo);

	void updateRss(RssVo vo);

	void deleteRss(RssVo vo);

	/**
	 * 查询角色列表
	 * 
	 * @param form
	 * @return
	 */
	Map<String, Object> queryRoleDataGrid(Form form);

	/**
	 * 查询单个角色信息
	 * 
	 * @param form
	 * @return
	 */
	Map<String, Object> queryRole(Form form);

	void addRole(RoleVo vo);

	void updateRole(RoleVo vo);

	void deleteRole(RoleVo vo);

	/**
	 * 查询角色列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> queryRoleList();

	/**
	 * 新增用户
	 * 
	 * @param vo
	 */
	void addUser(UserVo vo);

	/**
	 * 修改用户
	 * 
	 * @param vo
	 */
	void updateUser(UserVo vo);

	/**
	 * 删除用户
	 * 
	 * @param vo
	 */
	void deleteUser(UserVo vo);

	/**
	 * 查询下拉框数据
	 * 
	 * @param form
	 * @return
	 */
	List<Map<String, Object>> queryCombobx(Form form);

}
