package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.nuthabit.dao.SampleDAO;

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
	
	public void deleteTag(long tagId,long kehuId) {
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
//		System.out.println(new TagDAO().getTag(7).toString());
		new TagDAO().deleteTag(29, 1);
		
//		Iterator it = new TagDAO().getMyTagList(1).iterator();
//		while (it.hasNext()) {
//			CardTag ct = (CardTag) it.next();
//			System.out.println(ct.toString());
//		}
	}

}
