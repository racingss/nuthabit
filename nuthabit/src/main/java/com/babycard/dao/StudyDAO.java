package com.babycard.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

import com.nuthabit.dao.SampleDAO;

public class StudyDAO extends SampleDAO {

	public void addStudy(Study s, Collection picColl) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study os = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement(
					"insert into baby_study(customerId,cardId,createDate,reviewDate,reviewFlag,reviewLevel,picId,languageId)values(?,?,?,?,?,?,?,?) ");
			Iterator it = picColl.iterator();
			while (it.hasNext()) {
				CardPic cp = (CardPic) it.next();
				ps.setLong(1, s.getCustomerId());
				ps.setLong(2, s.getCardId());
				ps.setTimestamp(3, s.getCreateDate());
				ps.setTimestamp(4, s.getReviewDate());
				ps.setLong(5, s.getReviewFlag());
				ps.setLong(6, s.getReviewLevel());
				ps.setLong(7, cp.getPicId());
				ps.setLong(8, s.getLanguageId());
				ps.addBatch();
			}

			ps.executeBatch();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("needdelete:" + ps.toString());
		} finally {
			close(conn, ps, rs);
		}

	}

	public Study getStudy(long customerId, long reviewFlag,long languageId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select * from baby_study where customerId=? and reviewFlag=? and reviewDate<? and reviewLevel<=6 and languageId=?");
			ps.setLong(1, customerId);
			ps.setLong(2, reviewFlag);
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setLong(4, languageId);
			rs = ps.executeQuery();
			if (rs.next()) {
				s = new Study(rs);
				rs.close();
				ps.close();
				ps = conn.prepareStatement("update baby_study set reviewFlag=? where id=?");
				ps.setLong(1, 1);
				ps.setLong(2, s.getId());
				ps.executeUpdate();
				s.setReviewFlag(1);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return s;
	}

	public String getStudyNextTime(long customerId,long languageId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select reviewDate from baby_study where customerId=? and reviewFlag=0 and reviewLevel<=6 and languageId=? order by reviewDate");
			ps.setLong(1, customerId);
			ps.setLong(2, languageId);

			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public Study getStudyByCustomerIdPicId(long customerId, long picId,long languageId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_study where customerId=? and picId=? and languageId=?");
			ps.setLong(1, customerId);
			ps.setLong(2, picId);
			ps.setLong(3, languageId);
			rs = ps.executeQuery();
			if (rs.next()) {
				s = new Study(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return s;
	}

	public void deleteStudyByCustomerIdPicId(long customerId, long picId,long languageId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update baby_study set reviewLevel=9  where customerId=? and picId=? and languageId=?");
			ps.setLong(1, customerId);
			ps.setLong(2, picId);
			ps.setLong(3, languageId);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void update(Study s) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"update baby_study set reviewDate=?,reviewFlag=?,reviewLevel=? where customerId=? and picId=? and languageId=?");
			ps.setTimestamp(1, s.getReviewDate());
			ps.setLong(2, s.getReviewFlag());
			ps.setLong(3, s.getReviewLevel());
			ps.setLong(4, s.getCustomerId());
			ps.setLong(5, s.getPicId());
			ps.setLong(6, s.getLanguageId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public static void main(String arg[]) {

		new StudyDAO().deleteStudyByCustomerIdPicId(2670, 600,0);

		String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		String nextTime = new StudyDAO().getStudyNextTime(2670,0);
		System.out.println(nextTime);
		if (nextTime.indexOf(temp) != -1) {
			nextTime = "今天 " + nextTime.substring(11, 16);
		} else {
			nextTime = nextTime.substring(0, 16);
		}
		System.out.println(nextTime);

	}

}
