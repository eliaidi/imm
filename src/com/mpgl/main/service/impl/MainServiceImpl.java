package com.mpgl.main.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mpgl.main.dao.MainDao;
import com.mpgl.main.service.MainService;
import com.mpgl.util.CommonUtil;
import com.mpgl.util.Constant;
import com.mpgl.vo.Form;
import com.mpgl.vo.MenuVo;
import com.mpgl.vo.RoleVo;
import com.mpgl.vo.RssVo;
import com.mpgl.vo.UserVo;

public class MainServiceImpl implements MainService {

	private MainDao mainDao;

	public MainDao getMainDao() {
		return mainDao;
	}

	public void setMainDao(MainDao mainDao) {
		this.mainDao = mainDao;
	}

	@Override
	public UserVo login(Form form) {
		UserVo user = null;
		List<UserVo> list = mainDao.login(form);
		if (list != null && list.size() > 0) {
			user = list.get(0);
		}
		return user;
	}

	@Override
	public RoleVo getRoleByUser(UserVo user) {
		RoleVo vo = null;
		List<RoleVo> list = mainDao.queryRoleByUser(user);
		if (list != null && list.size() > 0) {
			vo = list.get(0);
		}
		return vo;
	}

	@Override
	public List<MenuVo> getMenuByRole(RoleVo role) {
		List<MenuVo> list = mainDao.queryMenuByRole(role);
		if (list != null && list.size() > 0) {
			for (MenuVo menu : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("role_id", role.getId());
				map.put("menu_id", menu.getId());
				menu.setList(mainDao.querySubMenuByMenu(map));
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> queryUserDataGrid(Form form) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = mainDao.queryUserList(form);
		int count = mainDao.queryUserListCount(form);
		map.put(Constant.ROW_KEY, list);
		map.put(Constant.ROW_TOTAL, count);
		return map;
	}

	@Override
	public Map<String, Object> queryUser(Form form) {
		Map<String, Object> map = null;
		List<Map<String, Object>> list = mainDao.queryUserListBySingle(form);
		if (list != null && list.size() > 0) {
			map = list.get(0);
		}
		return map;
	}

	@Override
	public void addUser(UserVo vo) {
		mainDao.addUser(vo);
	}

	@Override
	public void updateUser(UserVo vo) {
		mainDao.updateUser(vo);
	}

	@Override
	public void deleteUser(UserVo vo) {
		mainDao.deleteUser(vo);
	}

	@Override
	public List<Map<String, Object>> queryDictionaryByKey(Form form) {
		return mainDao.queryDictionaryByKey(form);
	}

	@Override
	public List<Map<String, Object>> queryRoleList() {
		return mainDao.queryRoleList();
	}

	@Override
	public void updateUserPassword(Map<String, Object> map) {
		mainDao.updateUserPassword(map);
	}

	@Override
	public boolean checkUserName(UserVo user) {
		boolean isCheck = true;
		int count = mainDao.checkUserName(user);
		if (count > 0) {
			isCheck = false;
		}
		return isCheck;
	}

	@Override
	public Map<String, Object> queryRssDataGrid(Form form) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = mainDao.queryRssList(form);
		int count = mainDao.queryRssListCount(form);
		map.put(Constant.ROW_KEY, list);
		map.put(Constant.ROW_TOTAL, count);
		return map;
	}

	@Override
	public Map<String, Object> queryRss(Form form) {
		Map<String, Object> map = null;
		List<Map<String, Object>> list = mainDao
				.queryRssListBySingle(form);
		if (list != null && list.size() > 0) {
			map = list.get(0);
		}
		return map;
	}

	@Override
	public void addRss(RssVo vo) {
		mainDao.addRss(vo);

	}

	@Override
	public void updateRss(RssVo vo) {
		mainDao.updateRss(vo);

	}

	@Override
	public void deleteRss(RssVo vo) {
		mainDao.deleteRss(vo);
	}

	@Override
	public List<Map<String, Object>> queryCombobx(Form form) {
		return mainDao.queryCombobx(form);
	}

	@Override
	public Map<String, Object> queryRoleDataGrid(Form form) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = mainDao.queryRoleLists(form);
		int count = mainDao.queryRoleListCount(form);
		map.put(Constant.ROW_KEY, list);
		map.put(Constant.ROW_TOTAL, count);
		return map;
	}

	@Override
	public Map<String, Object> queryRole(Form form) {
		Map<String, Object> map = null;
		List<Map<String, Object>> list = mainDao.queryRoleListBySingle(form);
		if (list != null && list.size() > 0) {
			map = list.get(0);
			if (form == null) {
				map.put("select", "");
			} else {
				map.put("select", mainDao.queryRoleRefPage(form));
			}
			map.put("pages", mainDao.queryPageList());
		}
		return map;
	}

	@Override
	public void addRole(RoleVo vo) {
		int id = mainDao.addRole(vo);
		if (vo.getIds() != null) {
			for (String submenu_id : vo.getIds()) {
				vo.setSubmenu_id(submenu_id);
				mainDao.addRoleRefPage(vo);
			}
		}
	}

	@Override
	public void updateRole(RoleVo vo) {
		mainDao.updateRole(vo);
		mainDao.deleteRoleRefPage(vo);
		if (vo.getIds() != null) {
			for (String submenu_id : vo.getIds()) {
				vo.setSubmenu_id(submenu_id);
				mainDao.addRoleRefPage(vo);
			}
		}
	}

	@Override
	public void deleteRole(RoleVo vo) {
		mainDao.deleteRole(vo);
		mainDao.deleteRoleRefPage(vo);
	}

	@Override
	public List<Map<String, Object>> queryRssList(Form form) {
		return mainDao.queryRssListBySingle(form);
	}

}
