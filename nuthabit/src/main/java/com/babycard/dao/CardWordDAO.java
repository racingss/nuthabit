package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.babycard.util.ICBAUtil;

public class CardWordDAO extends SampleDAO {

	public CardWord addCardWord(long cardId, String meaning) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardWord cp = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_card_word(cardId,meaning)values(?,?) ");
			ps.setLong(1, cardId);
			ps.setString(2, meaning);
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("select * from baby_card_word where cardId=? and meaning=?");
			ps.setLong(1, cardId);
			ps.setString(2, meaning);
			rs = ps.executeQuery();
			if (rs.next()) {
				cp = new CardWord(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return cp;
	}

	public void addCardWord(CardWord c) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardWord cp = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into baby_card_word(cardId,meaning,topP,leftP,widthP,sizeP)values(?,?,?,?,?,?) ");
			ps.setLong(1, c.getCardId());
			ps.setString(2, c.getMeaning());
			ps.setLong(3, c.getTopP());
			ps.setLong(4, c.getLeftP());
			ps.setLong(5, c.getWidthP());
			ps.setDouble(6, c.getSizeP());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void deleteCardWord(long wordId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardWord cp = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from baby_card_word where wordId=?");
			ps.setLong(1, wordId);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void updateCardWord(CardWord c) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardWord cp = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update baby_card_word set topP=?,leftP=?,widthP=?,sizeP=? where wordId=?");
			ps.setLong(1, c.getTopP());
			ps.setLong(2, c.getLeftP());
			ps.setLong(3, c.getWidthP());
			ps.setDouble(4, c.getSizeP());
			ps.setLong(5, c.getWordId());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}
	
	public void updateCardWordSound(CardWord c) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardWord cp = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update baby_card_word set sound=? where wordId=?");
			ps.setString(1, c.getSound());
			ps.setLong(2, c.getWordId());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Collection getCardWordByCardId(long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_word where cardId=?");
			ps.setLong(1, cardId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardWord(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public CardWord getCardWordByWordId(long wordId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_word where wordId=?");
			ps.setLong(1, wordId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new CardWord(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public Collection getAllCardWord() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_word ");

			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardWord(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public static void main(String arg[]) {
		CardWordDAO dao = new CardWordDAO();
		
		Collection coll = dao.getAllCardWord();
		Iterator it = coll.iterator();
		
		while (it.hasNext()) {
			CardWord c = (CardWord) it.next();
			if (c.getSoundFlag() == -1 || c.getSound() != null)
				continue;
			System.out.println(c.toString());
			CardMeaning cm = new CardMeaning();
			cm.setMeaning(c.getMeaning());
			
			ICBAUtil.getUrl(cm);
			
			c.setSound(cm.getAmPhMp3());
			
			dao.updateCardWordSound(c);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
