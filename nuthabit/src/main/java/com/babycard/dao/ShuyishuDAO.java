package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.nuthabit.dao.SampleDAO;

public class ShuyishuDAO extends SampleDAO {

	public Collection getShuyishuColl() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from shuyishu order by clickNum desc");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Shuyishu(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Shuyishu getShuyishuById(long id, boolean updateFlag) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Shuyishu s = null;
		;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from shuyishu where id=?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				s = new Shuyishu(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		if (updateFlag) {
			updateShuyishu(id);
		}

		return s;
	}

	public Collection updateShuyishu(long id) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("update shuyishu set clickNum=clickNum+1 where id=?");
			ps.setLong(1, id);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public static void main(String arg[]) {
		System.out.println(new ShuyishuDAO().getShuyishuColl().size());
		new ShuyishuDAO().getShuyishuById(3, false);
	}

}
