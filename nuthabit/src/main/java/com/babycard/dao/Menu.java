package com.babycard.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Menu {
	private long menuId = 0;
	private String menuType = null;
	private long languageId = 0;
	private String menuCont = null;

	public String getMenuCont() {
		return menuCont;
	}

	public void setMenuCont(String menuCont) {
		this.menuCont = menuCont;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}

	public Menu() {

	}

	private static Collection menuColl = null;

	public static String getMenu(String menuType, long languageId) {
		if (menuColl == null)
			menuColl = new MenuDAO().getMenuColl();
		Iterator it = menuColl.iterator();
		while (it.hasNext()) {
			Menu m = (Menu) it.next();
			if (m.getLanguageId() == languageId && m.getMenuType().equals(menuType))
				return m.getMenuCont();
		}

		Menu tm = new MenuDAO().getMenu(languageId, menuType);
		if (tm != null) {
			menuColl.add(tm);
			return tm.getMenuCont();
		} else {
			it = menuColl.iterator();
			while (it.hasNext()) {
				Menu m = (Menu) it.next();
				if (m.getLanguageId() == 1 && m.getMenuType().equals(menuType))
					return m.getMenuCont();
			}
		}
		return "没找到";
	}

	public Menu(java.sql.ResultSet rs) {
		try {
			this.setLanguageId(rs.getLong("languageId"));
			this.setMenuId(rs.getLong("menuId"));
			this.setMenuType(rs.getString("menuType"));
			this.setMenuCont(rs.getString("menuCont"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuType=" + menuType + ", languageId=" + languageId + ", menuCont="
				+ menuCont + "]";
	}

}
