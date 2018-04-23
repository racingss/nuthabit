package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.baidu.translate.demo.Main;
import com.baidu.translate.demo.TransApi;
import com.nuthabit.dao.SampleDAO;

import net.sf.json.JSONObject;

public class MenuDAO extends SampleDAO {

	public Collection getMenuColl() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_menu");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Menu(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public void addMenu(String menuType, String menuCont, long languageId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_menu(menuType,menuCont,languageId)values(?,?,?)");
			ps.setString(1, menuType);
			ps.setString(2, menuCont);
			ps.setLong(3, languageId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Collection getDefaultMenuColl() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_menu where languageId=0");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Menu(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Menu getMenu(long languageId, String menuType) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Menu m = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_menu where languageId=? and menuType=?");
			ps.setLong(1, languageId);
			ps.setString(2, menuType);
			rs = ps.executeQuery();
			if (rs.next()) {
				m = new Menu(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return m;
	}

	public static void main(String arg[]) {
		MenuDAO dao = new MenuDAO();
		Collection menuColl = dao.getDefaultMenuColl();
		Iterator menuIt = menuColl.iterator();

		Collection coll = new LanguageDAO().getAllLanguageColl();

		TransApi api = new TransApi(Main.APP_ID, Main.SECURITY_KEY);

		try {
			while (menuIt.hasNext()) {
				Menu m = (Menu) menuIt.next();
				System.out.println(m.toString());

				Iterator it = coll.iterator();
				while (it.hasNext()) {
					Language l = (Language) it.next();
					if (dao.getMenu(l.getLanguageId(), m.getMenuType()) != null)
						continue;

					String r = api.getTransResult(m.getMenuCont(), "zh", l.getSname());
					JSONObject json = JSONObject.fromObject(r.toString());
					String str1 = json.get("trans_result").toString();
					// 去掉[]
					str1 = str1.replace("[", "");
					str1 = str1.replace("]", "");
					JSONObject json1 = JSONObject.fromObject(str1);

					System.out.println("目标语言：" + json1.get("dst"));
					dao.addMenu(m.getMenuType(), json1.get("dst").toString(), l.getLanguageId());

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
