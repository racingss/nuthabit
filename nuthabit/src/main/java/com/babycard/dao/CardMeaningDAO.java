package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.babycard.util.ICBAUtil;
import com.baidu.translate.demo.Main;
import com.baidu.translate.demo.TransApi;
import com.nuthabit.dao.SampleDAO;

import net.sf.json.JSONObject;

public class CardMeaningDAO extends SampleDAO {

	public Collection getCardMeaning(long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_meaning where cardId=? ");
			ps.setLong(1, cardId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardMeaning(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getDefaultCardMeaning() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_meaning where languageId=0 ");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardMeaning(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllCardMeaning() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_meaning");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardMeaning(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getCardMeaningByCardId(long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_meaning where cardId=?");
			ps.setLong(1, cardId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardMeaning(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getCardMeaning(long cardId, long picId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_meaning where cardId=? and picId=?");
			ps.setLong(1, cardId);
			ps.setLong(2, picId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardMeaning(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public CardMeaning getCardMeaning(long cardId, long picId, long languageId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		CardMeaning cm = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_meaning where cardId=? and picId=? and languageId=? ");
			ps.setLong(1, cardId);
			ps.setLong(2, picId);
			ps.setLong(3, languageId);
			rs = ps.executeQuery();
			if (rs.next()) {
				cm = new CardMeaning(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return cm;
	}

	public CardMeaning getCardMeaningByPicId(long picId, long languageId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		CardMeaning cm = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_meaning where picId=? and languageId=? ");
			ps.setLong(1, picId);
			ps.setLong(2, languageId);
			rs = ps.executeQuery();
			if (rs.next()) {
				cm = new CardMeaning(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return cm;
	}

	public void addCardMeaning(String meaning, long languageId, long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		CardMeaning cm = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_card_meaning(meaning,languageId,cardId)values(?,?,?) ");
			ps.setString(1, meaning);
			ps.setLong(2, languageId);
			ps.setLong(3, cardId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void updateCardMeaning(long meaningId, String enPh, String amPh, String enPhMp3, String amPhMp3) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		CardMeaning cm = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"update baby_card_meaning set enPh=?,amPh=?,enPhMp3=?,amPhMp3=? where meaningId=?");
			ps.setString(1, enPh);
			ps.setString(2, amPh);
			ps.setString(3, enPhMp3);
			ps.setString(4, amPhMp3);
			ps.setLong(5, meaningId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("enPh:"+enPh+",amPh:"+amPh);
		} finally {
			close(conn, ps, rs);
		}
	}

	public void addCardMeaning(String meaning, long languageId, long cardId, long picId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		CardMeaning cm = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from baby_card_meaning where languageId=? and cardId=? and picId=?");
			ps.setLong(1, languageId);
			ps.setLong(2, cardId);
			ps.setLong(3, picId);
			ps.executeUpdate();
			ps.close();

			ps = conn
					.prepareStatement("insert into baby_card_meaning(meaning,languageId,cardId,picId)values(?,?,?,?) ");
			ps.setString(1, meaning);
			ps.setLong(2, languageId);
			ps.setLong(3, cardId);
			ps.setLong(4, picId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public String buildMeaning(long picId, long languageId) {

		CardMeaning m = getCardMeaningByPicId(picId, 0);
		if (m == null)
			return null;

		TransApi api = new TransApi(Main.APP_ID, Main.SECURITY_KEY);

		Language l = Language.getLanguageByid(languageId);

		String r = api.getTransResult(m.getMeaning(), "zh", l.getSname());

		JSONObject json = JSONObject.fromObject(r.toString());
		String str1 = json.get("trans_result").toString();
		// 去掉[]
		str1 = str1.replace("[", "");
		str1 = str1.replace("]", "");
		JSONObject json1 = JSONObject.fromObject(str1);

		System.out.println("目标语言：" + json1.get("dst"));
		addCardMeaning(json1.get("dst").toString(), l.getLanguageId(), m.getCardId(), m.getPicId());
		return json1.get("dst").toString();
	}

	public void buildMeaningByCardId(long cardId, long languageId) {

		CardMeaning m = getCardMeaning(cardId, 0, 0);
		if (m == null)
			return;

		TransApi api = new TransApi(Main.APP_ID, Main.SECURITY_KEY);

		Language l = Language.getLanguageByid(languageId);

		String r = api.getTransResult(m.getMeaning(), "zh", l.getSname());

		JSONObject json = JSONObject.fromObject(r.toString());
		String str1 = json.get("trans_result").toString();
		// 去掉[]
		str1 = str1.replace("[", "");
		str1 = str1.replace("]", "");
		JSONObject json1 = JSONObject.fromObject(str1);

		System.out.println("目标语言：" + json1.get("dst"));
		addCardMeaning(json1.get("dst").toString(), l.getLanguageId(), m.getCardId(), m.getPicId());
	}

	public Collection getAllEnglishCardMeaning() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select * from baby_card_meaning where languageId=1 and picId>0 and cardId in (select cardId from baby_card where status=0) order by meaningId desc");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardMeaning(rs));
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
		CardMeaningDAO dao = new CardMeaningDAO();
		Collection coll = dao.getAllEnglishCardMeaning();
		System.out.println(coll.size());
		Iterator it = coll.iterator();
		while (it.hasNext()) {
			CardMeaning cm = (CardMeaning) it.next();
			if(cm.getEnPh()!=null)
				continue;
			
			
			cm.setMeaning(cm.getMeaning().toLowerCase());
			System.out.println(cm.getMeaning());
			ICBAUtil.getUrl(cm);
			
			dao.updateCardMeaning(cm.getMeaningId(), cm.getEnPh(), cm.getAmPh(), cm.getEnPhMp3(), cm.getAmPhMp3());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//
	}

}
