package com.babycard.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.Collection;

public class DizhiDAO extends SampleDAO {

	public DizhiDAO() {
	}

	public Collection getDizhiListByKehuId(String kehuId) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from dizhi where kehuId=? order by status  ");
			ps.setString(1, kehuId);
			for (rs = ps.executeQuery(); rs.next(); coll.add(new Dizhi(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Dizhi getMainDizhiByKehuId(String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Dizhi result;
		conn = null;
		ps = null;
		rs = null;
		result = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from dizhi where kehuId=? and status=0 ");
			ps.setString(1, kehuId);
			for (rs = ps.executeQuery(); rs.next();)
				result = new Dizhi(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public Dizhi getDizhiId(long id) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Dizhi result;
		conn = null;
		ps = null;
		rs = null;
		result = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from dizhi where id=? ");
			ps.setLong(1, id);
			for (rs = ps.executeQuery(); rs.next();)
				result = new Dizhi(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public Dizhi getDizhiByKehuId(String kehuId, long dizhiId) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Dizhi result;
		conn = null;
		ps = null;
		rs = null;
		result = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from dizhi where kehuId=? and id=? ");
			ps.setString(1, kehuId);
			ps.setLong(2, dizhiId);
			for (rs = ps.executeQuery(); rs.next();)
				result = new Dizhi(rs);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public void addDizhi(Dizhi d) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from dizhi where kehuId=? ");
			ps.setString(1, d.getKehuId());
			rs = ps.executeQuery();
			if (rs.next())
				d.setStatus(1L);
			else
				d.setStatus(0L);
			rs.close();
			ps.close();
			ps = conn.prepareStatement(
					"insert into dizhi(kehuId,province,city,qu,xiangxi,dianhua,shoujianren,status)values(?,?,?,?,?,?,?,?) ");
			ps.setString(1, d.getKehuId());
			ps.setString(2, d.getProvince());
			ps.setString(3, d.getCity());
			ps.setString(4, d.getQu());
			ps.setString(5, d.getXiangxi());
			ps.setString(6, d.getDianhua());
			ps.setString(7, d.getShoujianren());
			ps.setLong(8, d.getStatus());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return;
	}

	public void updateDizhi(Dizhi d) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"update dizhi set province=?,xiangxi=?,dianhua=?,shoujianren=?,qu=? where kehuId=? and id=?");
			ps.setString(1, d.getProvince());
			ps.setString(2, d.getXiangxi());
			ps.setString(3, d.getDianhua());
			ps.setString(4, d.getShoujianren());
			ps.setString(5, d.getQu());
			ps.setString(6, d.getKehuId());
			ps.setLong(7, d.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return;
	}

	public void updateDizhiStatusDefault(long id, String kehuId) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update dizhi set status=? where kehuId=? ");
			ps.setLong(1, 1L);
			ps.setString(2, kehuId);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement("update dizhi set status=? where kehuId=? and id=?");
			ps.setLong(1, 0L);
			ps.setString(2, kehuId);
			ps.setLong(3, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return;
	}

	public void deleteDizhiStatusDefault(long id, String kehuId) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from dizhi where kehuId=? and id=? and status=?");
			ps.setString(1, kehuId);
			ps.setLong(2, id);
			ps.setLong(3, 0L);
			rs = ps.executeQuery();
			boolean flag = false;
			if (rs.next())
				flag = true;
			rs.close();
			ps.close();
			ps = conn.prepareStatement("delete from dizhi  where kehuId=? and id=?");
			ps.setString(1, kehuId);
			ps.setLong(2, id);
			ps.executeUpdate();
			if (flag) {
				ps = conn.prepareStatement("select * from dizhi where kehuId=? order by id ");
				ps.setString(1, kehuId);
				rs = ps.executeQuery();
				Dizhi d = null;
				if (rs.next()) {
					d = new Dizhi(rs);
					rs.close();
					ps.close();
					ps = conn.prepareStatement("update  dizhi set status=?  where id=?");
					ps.setLong(1, 0L);
					ps.setLong(2, d.getId());
					ps.executeUpdate();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return;
	}
}
