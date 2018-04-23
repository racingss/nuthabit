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

public class TagDAO extends SampleDAO {

	public CardTag addTag(CardTag t) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_card_tag(tag,kehuId)values(?,?)");
			ps.setString(1, t.getTag());
			ps.setLong(2, t.getKehuId());
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("select * from baby_card_tag where kehuId=?");
			ps.setLong(1, t.getKehuId());

			rs = ps.executeQuery();

			if (rs.next()) {
				t = new CardTag(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return t;
	}

	public Collection getMainTagList() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from baby_card_tag where tagIndex>1 order by tagIndex desc");

			rs = ps.executeQuery();

			while (rs.next()) {
				coll.add(new CardTag(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllTagList() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from baby_card_tag ");

			rs = ps.executeQuery();

			while (rs.next()) {
				coll.add(new CardTag(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllTagLanguage() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from baby_card_tag_language ");

			rs = ps.executeQuery();

			while (rs.next()) {
				coll.add(new CardTagLanguage(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getDefaultTagLanguage() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from baby_card_tag_language where languageId=0");

			rs = ps.executeQuery();

			while (rs.next()) {
				coll.add(new CardTagLanguage(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public CardTagLanguage getTagLanguage(long tagId, long languageId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardTagLanguage ctl = null;

		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from baby_card_tag_language where tagId=? and languageId=? ");
			ps.setLong(1, tagId);
			ps.setLong(2, languageId);

			rs = ps.executeQuery();

			if (rs.next()) {
				ctl = new CardTagLanguage(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return ctl;
	}

	public void addTagLanguage(long tagId, String tag, long languageId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();

			ps = conn.prepareStatement("insert into baby_card_tag_language (tagId,tag,languageId)values(?,?,?) ");
			ps.setLong(1, tagId);
			ps.setString(2, tag);
			ps.setLong(3, languageId);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Collection getMyTagList(long kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from baby_card_tag where kehuId=?");
			ps.setLong(1, kehuId);

			rs = ps.executeQuery();

			while (rs.next()) {
				coll.add(new CardTag(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public void deleteTag(long tagId, long kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();

			ps = conn.prepareStatement("delete from baby_card_tag where kehuId=? and tagId=?");
			ps.setLong(1, kehuId);
			ps.setLong(2, tagId);

			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

	}

	public CardTag getTag(long tagId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardTag t = null;

		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from baby_card_tag where tagId=?");
			ps.setLong(1, tagId);

			rs = ps.executeQuery();

			if (rs.next()) {
				t = new CardTag(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return t;
	}

	public static void main(String arg[]) {
		TagDAO dao = new TagDAO();
		Collection tagColl = dao.getDefaultTagLanguage();
		Iterator tagId = tagColl.iterator();

		Collection coll = new LanguageDAO().getAllLanguageColl();

		TransApi api = new TransApi(Main.APP_ID, Main.SECURITY_KEY);

		while (tagId.hasNext()) {
			CardTagLanguage t = (CardTagLanguage) tagId.next();
			System.out.println(t.toString());

			Iterator it = coll.iterator();
			while (it.hasNext()) {
				Language l = (Language) it.next();
				if (dao.getTagLanguage(t.getTagId(), l.getLanguageId()) != null)
					continue;

				String r = api.getTransResult(t.getTag(), "zh", l.getSname());
				JSONObject json = JSONObject.fromObject(r.toString());
				String str1 = json.get("trans_result").toString();
				// 去掉[]
				str1 = str1.replace("[", "");
				str1 = str1.replace("]", "");
				JSONObject json1 = JSONObject.fromObject(str1);

				System.out.println("目标语言：" + json1.get("dst"));
				dao.addTagLanguage(t.getTagId(), json1.get("dst").toString(), l.getLanguageId());
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
