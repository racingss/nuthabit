package com.nuthabit.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
					"insert into myplan (kehuId,title,times,discription,pic,planId,kehuNick,beginDate,endDate,privacyFlag,serviceFlag,createDate,headimgurl,defaultFlag)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
			ps.setString(12, m.getCreateDate());
			ps.setString(13, m.getHeadimgurl());
			ps.setLong(14, m.getDefaultFlag());
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

	public List<String> getDakaHistory(Long planId, String from, String to) {
		List<String> dakaDateHistory = null;
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select planDate from myplan_history where planId=? and planDate between ? and ? order by planDate");
			ps.setLong(1, planId);
			ps.setString(2, from);
			ps.setString(3, to);

			rs = ps.executeQuery();
			while (rs.next()) {
				if (dakaDateHistory == null) {
					dakaDateHistory = new ArrayList<String>();
				}
				dakaDateHistory.add(rs.getString("planDate"));
			}
		} catch (Exception e) {
			System.out.println("MyplanDAO:" + e.toString());
		} finally {
			close(conn, ps, rs);
		}

		return dakaDateHistory;
	}

	public void experience(MyplanExperience h) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into myplan_experience (planId,planDate,review,mainId,pic1,pic2,pic3,privacyFlag,kehuId,nickname,headimgurl)values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, h.getPlanId());
			ps.setString(2, new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(new Date()));
			ps.setString(3, h.getReview());
			ps.setLong(4, h.getMainId());
			ps.setString(5, h.getPic1());
			ps.setString(6, h.getPic2());
			ps.setString(7, h.getPic3());
			ps.setLong(8, h.getPrivacyFlag());
			ps.setString(9, h.getKehuId());
			ps.setString(10, h.getNickname());
			ps.setString(11, h.getHeadimgurl());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("MyplanDAO:" + e.toString());
		} finally {
			close(conn, ps, rs);
		}
	}

	/**
	 * 打卡处理 1.新增打卡记录 2.更新计划表
	 * 
	 * @param m
	 * @return
	 */
	public long signin(Myplan m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into myplan_history (planId,planDate)values(?,?)");
			ps.setLong(1, m.getId());
			ps.setString(2, new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("update myplan set lastDate=?,continued=? where id=? and kehuId=?");
			ps.setString(1, new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(new Date()));
			ps.setLong(2, m.getContinued() + 1);
			ps.setLong(3, m.getId());
			ps.setString(4, m.getKehuId());
			ps.executeUpdate();
			return m.getContinued() + 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("MyplanDAO:" + e.toString());
		} finally {
			close(conn, ps, rs);
		}
		return -1;
	}

	/**
	 * 判定是否今日已经打卡
	 * 
	 * @param m
	 * @return true 已打卡, false 未打卡
	 */
	public boolean hasSignedin(Myplan m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		int dakaCount = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select count(*) from myplan_history where planId=? and planDate=?");
			ps.setLong(1, m.getId());
			ps.setString(2, new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			rs = ps.executeQuery();
			if (rs.next()) {
				dakaCount = rs.getInt(1);
			}
		} catch (Exception e) {

		} finally {
			close(conn, ps, rs);
		}
		return dakaCount > 0;
	}

	public void thumbup(MyplanThumbup m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into myplan_thumbup(planId,planDate,kehuId,nickname,headimgurl,experienceId)values(?,?,?,?,?,?)");
			ps.setString(1, m.getPlanId());
			ps.setString(2, new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			ps.setString(3, m.getKehuId());
			ps.setString(4, m.getNickname());
			ps.setString(5, m.getHeadimgurl());
			ps.setLong(6, m.getExperienceId());
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("update myplan set thumbup=thumbup+1 where planId=? ");
			ps.setString(1, m.getPlanId());
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("update myplan_experience set thumbup=thumbup+1 where id=? ");
			ps.setLong(1, m.getExperienceId());
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public Myplan get(String planId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from myplan where planId=?");
			ps.setString(1, planId);
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

	public Myplan getDefault(String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Myplan m = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from myplan where kehuId=? and defaultFlag=9");
			ps.setString(1, kehuId);
			rs = ps.executeQuery();
			if (rs.next()) {
				m = new Myplan(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return m;
	}

	public Collection<MyplanHistory> getAllPlanHistyory(String planId) {
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
			ps.setString(1, planId);

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

	public Collection<MyplanThumbup> getPlanThumbup(String planId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection<MyplanThumbup> coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from myplan_thumbup where planId=? order by id desc limit 0,10");
			ps.setString(1, planId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new MyplanThumbup(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection<MyplanThumbup> getExperienceThumbup(long experienceId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection<MyplanThumbup> coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from myplan_thumbup where experienceId=? order by id desc limit 0,10");
			ps.setLong(1, experienceId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new MyplanThumbup(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection<MyplanExperience> getPlanExperience(String planId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection<MyplanExperience> coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select * from myplan_experience where planId=? and mainId=0 order by id desc limit 0,10");
			ps.setString(1, planId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new MyplanExperience(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection<MyplanExperience> getPlanExperienceReply(long mainId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection<MyplanExperience> coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from myplan_experience where mainId=? order by id desc limit 0,10");
			ps.setLong(1, mainId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new MyplanExperience(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection<MyplanExperience> getDiscorveryExperience() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection<MyplanExperience> coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from myplan_experience where recommand>0 order by id desc limit 0,10");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new MyplanExperience(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public void delete(String planId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from myplan where planId=?");
			ps.setString(1, planId);

			ps.executeUpdate();

			ps.close();
			ps = conn.prepareStatement("delete from myplan_history where planId=?");
			ps.setString(1, planId);
			ps.executeUpdate();

			ps.close();
			ps = conn.prepareStatement("delete from myplan_experience where planId=?");
			ps.setString(1, planId);
			ps.executeUpdate();

			ps.close();
			ps = conn.prepareStatement("delete from myplan_thumbup where planId=?");
			ps.setString(1, planId);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void deleteExperience(long exId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from myplan_experience where id=? or mainId=?");
			ps.setLong(1, exId);
			ps.setLong(2, exId);
			ps.executeUpdate();

			ps.close();
			ps = conn.prepareStatement("delete from myplan_thumbup where experienceId=?");
			ps.setLong(1, exId);
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
