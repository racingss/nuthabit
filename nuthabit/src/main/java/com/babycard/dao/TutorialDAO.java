package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.nuthabit.dao.SampleDAO;

public class TutorialDAO extends SampleDAO {

	public void add(Tutorial t) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into tutorial(kId,tutorialTime,tutorialType)values(?,?,?)");
			ps.setLong(1, t.getkId());
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setLong(3, t.getTutorialType());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public boolean isTutorial(long kId, long tType) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from tutorial where kId=? and tutorialType=?");
			ps.setLong(1, kId);
			ps.setLong(2, tType);
			rs = ps.executeQuery();
			if (rs.next())
				return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return false;
	}

	public static void main(String arg[]) {
		System.out.println(new TutorialDAO().isTutorial(2, 2));
	}

}
