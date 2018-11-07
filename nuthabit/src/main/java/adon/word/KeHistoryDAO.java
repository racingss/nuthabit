package adon.word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.suyufuwu.dao.SampleDAO;

public class KeHistoryDAO extends SampleDAO {

	public void add(KeHistory k) {
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
			ps = conn.prepareStatement("delete from kehistory where keId=? and kehuId=?");
			ps.setLong(1, k.getKeId());
			ps.setString(2, k.getKehuId());
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("insert into kehistory(keId,kehuId,reviewDay)values(?,?,?) ");
			ps.setLong(1, k.getKeId());
			ps.setString(2, k.getKehuId());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(3, formatter.format(new Date()));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally

		{
			close(conn, ps, rs);
		}
	}

	public Collection get(String kehuId, long bookId) {
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

			ps = conn.prepareStatement(
					"select * from kehistory where kehuId=? and keId in (select keId from bookke where bookId=?)");
			ps.setString(1, kehuId);
			ps.setLong(2, bookId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new KeHistory(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally

		{
			close(conn, ps, rs);
		}
		return coll;
	}
	
	public Collection get(String kehuId) {
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

			ps = conn.prepareStatement(
					"select * from kehistory where kehuId=? ");
			ps.setString(1, kehuId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new KeHistory(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally

		{
			close(conn, ps, rs);
		}
		return coll;
	}

}
