package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nuthabit.dao.SampleDAO;

public class StudyDAO extends SampleDAO {

	public void addStudy(Study s) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study os = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_study where customerId=? and cardId=?");
			ps.setLong(1, s.getCustomerId());
			ps.setLong(2, s.getCardId());
			rs = ps.executeQuery();
			if (rs.next()) {
				os = new Study(rs);
				rs.close();
				ps.close();

				// ps = conn.prepareStatement(
				// "update baby_study set
				// reviewDate=?,reviewFlag=?,reviewLevel=? where customerId=?
				// and cardId=?");
				// ps.setTimestamp(1, s.getReviewDate());
				// ps.setLong(2, s.getReviewFlag());
				// ps.setLong(3, s.getReviewLevel());
				// ps.setLong(4, os.getCustomerId());
				// ps.setLong(5, os.getCardId());
				// ps.executeUpdate();

			} else {
				rs.close();
				ps.close();
				ps = conn.prepareStatement(
						"insert into baby_study(customerId,cardId,createDate,reviewDate,reviewFlag,reviewLevel)values(?,?,?,?,?,?) ");
				ps.setLong(1, s.getCustomerId());
				ps.setLong(2, s.getCardId());
				ps.setTimestamp(3, s.getCreateDate());
				ps.setTimestamp(4, s.getReviewDate());
				ps.setLong(5, s.getReviewFlag());
				ps.setLong(6, s.getReviewLevel());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

	}

	public Study getStudy(long customerId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_study where customerId=? and reviewFlag=0 order by reviewDate,id ");
			ps.setLong(1, customerId);
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
	
	
	public Study getStudy(long customerId,long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_study where customerId=? and cardId=? ");
			ps.setLong(1, customerId);
			ps.setLong(2, cardId);
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
	
	public void update(Study s) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update baby_study set reviewDate=?,reviewFlag=?,reviewLevel=? where customerId=? and cardId=?");
			ps.setTimestamp(1, s.getReviewDate());
			ps.setLong(2, s.getReviewFlag());
			ps.setLong(3, s.getReviewLevel());
			ps.setLong(4, s.getCustomerId());
			ps.setLong(5, s.getCardId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}
	
}
