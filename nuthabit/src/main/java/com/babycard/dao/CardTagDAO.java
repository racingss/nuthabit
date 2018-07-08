package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nuthabit.dao.SampleDAO;

public class CardTagDAO extends SampleDAO {
	public CardTag getCardTagByCardId(long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardTag ct = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select * from baby_card_tag where tagId in (select tagId from baby_bind_card_tag where cardId=? )");
			ps.setLong(1, cardId);
			rs = ps.executeQuery();
			if (rs.next()) {
				ct = new CardTag(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return ct;
	}
	
	public static void main(String arg[]){
		System.out.println(new CardTagDAO().getCardTagByCardId(2021).toString());
	}

}
