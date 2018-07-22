package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.nuthabit.dao.SampleDAO;

public class FavDAO extends SampleDAO {

	public void addFav(Fav fav) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_fav where kehuId=? and cardId=?");
			ps.setLong(1, fav.getKehuId());
			ps.setLong(2, fav.getCardId());
			rs = ps.executeQuery();
			if (rs.next()) {
				rs.close();
				ps.close();
				ps = conn.prepareStatement("update baby_card_fav set status=0,favDate=?  where kehuId=? and cardId=?");
				ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
				ps.setLong(2, fav.getKehuId());
				ps.setLong(3, fav.getCardId());
				ps.executeUpdate();
			} else {
				rs.close();
				ps.close();
				ps = conn.prepareStatement("insert into baby_card_fav(kehuId,cardId,favDate)values(?,?,?)");
				ps.setLong(1, fav.getKehuId());
				ps.setLong(2, fav.getCardId());
				ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
				ps.executeUpdate();

			}
			
			ps.close();
			ps = conn.prepareStatement("update baby_card set favCount=favCount+1 where cardId=?");
			ps.setLong(1, fav.getCardId());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

	}

	public void closeFav(long kehuId, long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("update baby_card_fav set status=1,favDate=?  where kehuId=? and cardId=?");
			ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			ps.setLong(2, kehuId);
			ps.setLong(3, cardId);
			ps.executeUpdate();

			ps.close();
			ps = conn.prepareStatement("update baby_card set favCount=favCount-1 where cardId=?");
			ps.setLong(1, cardId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public long isFav(long kehuId, long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_fav where status=0 and kehuId=? and cardId=?");
			ps.setLong(1, kehuId);
			ps.setLong(2, cardId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return 1;
	}

	public static void main(String arg[]) {
		Fav f = new Fav();
		f.setCardId(11);
		f.setKehuId(12);
		new FavDAO().addFav(f);
		// new FavDAO().closeFav(12, 11);
	}

}
