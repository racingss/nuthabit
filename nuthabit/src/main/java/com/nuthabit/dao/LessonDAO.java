package com.nuthabit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class LessonDAO extends SampleDAO {
	public void add(MyplanLesson m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into myplan_lesson (lessonId,kehuId,kehuNick,headimgurl,title,discription,joinNumber,pic,price,createDate,score,status)values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, m.getLessonId());
			ps.setString(2, m.getKehuId());
			ps.setString(3, m.getKehuNick());
			ps.setString(4, m.getHeadimgurl());
			ps.setString(5, m.getTitle());
			ps.setString(6, m.getDiscription());
			ps.setLong(7, m.getJoinNumber());
			ps.setString(8, m.getPic());
			ps.setLong(9, m.getPrice());
			ps.setString(10, m.getCreateDate());
			ps.setLong(11, m.getScore());
			ps.setLong(12, m.getStatus());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}
	
	public void update(MyplanLesson m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"update myplan_lesson set title=?,discription=?,pic=?,price=?,status=? where lessonId=?");
			ps.setString(1, m.getLessonId());
			ps.setString(1, m.getTitle());
			ps.setString(2, m.getDiscription());
			ps.setString(3, m.getPic());
			ps.setLong(4, m.getPrice());
			ps.setLong(5, m.getStatus());
			ps.setString(6, m.getLessonId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void add(MyplanLessonDay m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into myplan_lesson_day (lessonId,title,discription,status,dayIndex)values(?,?,?,?,?)");
			ps.setString(1, m.getLessonId());
			ps.setString(2, m.getTitle());
			ps.setString(3, m.getDiscription());
			ps.setLong(4, m.getStatus());
			ps.setLong(5, m.getDayIndex());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}
	
	public void updateMyplanLessonDay(MyplanLessonDay m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"update myplan_lesson_day set title=?,discription=?,status=?,dayIndex=? where id=?");
			ps.setString(1, m.getTitle());
			ps.setString(2, m.getDiscription());
			ps.setLong(3, m.getStatus());
			ps.setLong(4, m.getDayIndex());
			ps.setLong(5, m.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}
	
	public MyplanLesson getMyplanLesson(String lessonId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from  myplan_lesson where lessonId=?");
			ps.setString(1, lessonId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new MyplanLesson(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}
	
	public MyplanLessonDay getMyplanLessonDay(long id) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from  myplan_lesson_day where id=?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new MyplanLessonDay(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public Collection<MyplanLesson> getMyplanLessonColl(String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection<MyplanLesson> coll = new ArrayList<MyplanLesson>();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from  myplan_lesson where kehuId=?");
			ps.setString(1, kehuId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new MyplanLesson(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}
	
	public Collection<MyplanLessonDay> getMyplanLessonDayColl(String lessonId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection<MyplanLessonDay> coll = new ArrayList<MyplanLessonDay>();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from  myplan_lesson_day where lessonId=? order by dayIndex");
			ps.setString(1, lessonId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new MyplanLessonDay(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

}
