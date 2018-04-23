package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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

	public CardMeaning getCardMeaning(long cardId, long languageId) {
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
			ps = conn.prepareStatement("select * from baby_card_meaning where cardId=? and languageId=? ");
			ps.setLong(1, cardId);
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

	public static void main(String arg[]) {
		CardMeaningDAO dao = new CardMeaningDAO();
		Collection meaningColl = dao.getDefaultCardMeaning();
		Iterator meaningIt = meaningColl.iterator();

		Collection coll = new LanguageDAO().getAllLanguageColl();

		TransApi api = new TransApi(Main.APP_ID, Main.SECURITY_KEY);

		while (meaningIt.hasNext()) {
			CardMeaning m = (CardMeaning) meaningIt.next();
			System.out.println(m.toString());

			Iterator it = coll.iterator();
			while (it.hasNext()) {
				Language l = (Language) it.next();
				if (dao.getCardMeaning(m.getCardId(), l.getLanguageId()) != null)
					continue;

				String r = api.getTransResult(m.getMeaning(), "zh", l.getSname());
				JSONObject json = JSONObject.fromObject(r.toString());
				String str1 = json.get("trans_result").toString();
				// 去掉[]
				str1 = str1.replace("[", "");
				str1 = str1.replace("]", "");
				JSONObject json1 = JSONObject.fromObject(str1);

				System.out.println("目标语言：" + json1.get("dst"));
				dao.addCardMeaning(json1.get("dst").toString(), l.getLanguageId(), m.getCardId());

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

}
