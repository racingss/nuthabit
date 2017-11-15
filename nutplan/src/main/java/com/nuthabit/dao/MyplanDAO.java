package com.nuthabit.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class MyplanDAO extends SampleDAO {

	public void add(Myplan m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into myplan (kehuId,title,times,discription,pic,planId,kehuNick,beginDate,endDate,privacyFlag,serviceFlag)values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, m.getKehuId());
			ps.setString(2, m.getTitle());
			ps.setString(3, m.getTimes());
			ps.setString(4, m.getDiscription());
			ps.setString(5, m.getPic());
			ps.setString(6, m.getPlanId());
			ps.setString(7, m.getKehuNick());
			ps.setString(8, m.getBeginDate());
			ps.setString(9, m.getEndDate());
			ps.setLong(10, m.getPrivacyFlag());
			ps.setLong(11, m.getServiceFlag());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void process(Myplan m, MyplanHistory h) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into myplan_history (planId,planDate,review,status,pic1,pic2,pic3,privacyFlag)values(?,?,?,?,?,?,?,?)");
			ps.setLong(1, m.getId());
			ps.setString(2, new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			ps.setString(3, h.getReview());
			ps.setLong(4, h.getStatus());
			ps.setString(5, h.getPic1());
			ps.setString(6, h.getPic2());
			ps.setString(7, h.getPic3());
			ps.setLong(8, h.getPrivacyFlag());

			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("update myplan set lastDate=?,continued=? where id=? and kehuId=?");
			ps.setString(1, new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(new Date()));
			ps.setLong(2, m.getContinued() + 1);
			ps.setLong(3, m.getId());
			ps.setString(4, m.getKehuId());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("MyplanDAO:" + e.toString());
		} finally {
			close(conn, ps, rs);
		}
	}

	public Myplan get(long id) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from myplan where id=?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Myplan(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public Collection<Myplan> getAll(String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection<Myplan> coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from myplan where kehuId=? order by lastDate");
			ps.setString(1, kehuId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Myplan(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection<MyplanHistory> getAllPlanHistyory(long planId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection<MyplanHistory> coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from myplan_history where planId=? order by id desc limit 0,10");
			ps.setLong(1, planId);

			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new MyplanHistory(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection<MyplanHistory> getDiscorveryHistyory(String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection<MyplanHistory> coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select * from myplan_history where length(review)>0 order by id desc limit 0,10");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new MyplanHistory(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public void delete(long id, String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from myplan where id=? and kehuId=?");
			ps.setLong(1, id);
			ps.setString(2, kehuId);
			ps.executeUpdate();

			ps.close();
			ps = conn.prepareStatement("delete from myplan_history where planId=?");
			ps.setLong(1, id);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void update(Myplan m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"update myplan set title=?,lastDate=?,times=?,continued=?,discription=?,pic=? where id=? and kehuId=?");
			ps.setString(1, m.getTitle());
			ps.setString(2, m.getLastDate());
			ps.setString(3, m.getTimes());
			ps.setLong(4, m.getContinued());
			ps.setString(5, m.getDiscription());
			ps.setString(6, m.getPic());
			ps.setLong(7, m.getId());
			ps.setString(8, m.getKehuId());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

}
