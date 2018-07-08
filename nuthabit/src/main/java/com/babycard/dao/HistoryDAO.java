package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.nuthabit.dao.SampleDAO;

public class HistoryDAO extends SampleDAO {

	public void addHistory(History h) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from baby_card_history where kId=? and cardId=?");
			ps.setLong(1, h.getKId());
			ps.setLong(2, h.getCardId());
			ps.executeUpdate();
			ps.close();
			
			ps = conn.prepareStatement("insert into baby_card_history(kId,cardId,readDate)values(?,?,?)");
			ps.setLong(1, h.getKId());
			ps.setLong(2, h.getCardId());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void finsihHistory(long cardId, long kId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select * from baby_card_history where kId=? and cardId=? order by historyId desc");
			ps.setLong(1, kId);
			ps.setLong(2, cardId);
			rs = ps.executeQuery();
			History h = null;
			if (rs.next()) {
				h = new History(rs);
			}
			rs.close();

			ps = conn.prepareStatement("update baby_card_history set finishFlag=1 where historyId=?");
			ps.setLong(1, h.getHistoryId());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public static void main(String arg[]) {
		History h = new History();
		h.setCardId(3);
		h.setKId(2);
//		new HistoryDAO().addHistory(h);
		new HistoryDAO().finsihHistory(1, 2);
	}

}
