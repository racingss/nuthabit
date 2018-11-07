package adon.word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.suyufuwu.dao.SampleDAO;

public class PlanDAO extends SampleDAO {

	public Plan getPlan(String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from plan where kehuId=? ");
			ps.setString(1, kehuId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Plan(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public void addPlan(Plan p) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from plan where kehuId=? and bookId=?");
			ps.setString(1, p.getKehuId());
			ps.setLong(2, p.getBookId());
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("insert into plan(kehuId,bookId,planCount)values(?,?,?)");
			ps.setString(1, p.getKehuId());
			ps.setLong(2, p.getBookId());
			ps.setInt(3, p.getPlanCount());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}
	
	public void deletePlan(long bookId ,String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from plan where kehuId=? and bookId=?");
			ps.setString(1, kehuId);
			ps.setLong(2, bookId);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void updatePlan(String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		Plan p = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from plan where kehuId=?");
			ps.setString(1, kehuId);
			rs = ps.executeQuery();
			if (rs.next()) {
				p = new Plan(rs);
			}
			rs.close();
			ps.close();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String today = formatter.format(new Date());

			if (p.getCurrentDay() != null) {
				if (!today.equals(p.getCurrentDay())) {
					ps = conn.prepareStatement("update plan set todayIndex=0 ,currentDay=? where kehuId=?");
					ps.setString(1, today);
					ps.setString(2, kehuId);
					ps.executeUpdate();
					ps.close();
				}
			} else {
				ps = conn.prepareStatement("update plan set todayIndex=0 ,currentDay=? where kehuId=?");
				ps.setString(1, today);
				ps.setString(2, kehuId);
				ps.executeUpdate();
				ps.close();
			}

			ps = conn.prepareStatement("update plan set bookIndex=bookIndex+1,todayIndex=todayindex+1 where kehuId=?");
			ps.setString(1, p.getKehuId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void modifyPlan(Plan p) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update plan set planCount = ? where kehuId=? and bookId=?");
			ps.setInt(1, p.getPlanCount());
			ps.setString(2, p.getKehuId());
			ps.setLong(3, p.getBookId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

}
