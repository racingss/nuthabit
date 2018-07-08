package com.babycard.dao;

import java.sql.Connection;
import java.io.*;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import com.nuthabit.dao.Kehu;
import com.nuthabit.dao.SampleDAO;

public class CardPicDAO extends SampleDAO {

	public CardPic addCardPic(long cardId, String cardPic) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardPic cp = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_card_pic(cardId,cardPic)values(?,?) ");
			ps.setLong(1, cardId);
			ps.setString(2, cardPic);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement("select * from baby_card_pic where cardId=? and cardPic=?");
			ps.setLong(1, cardId);
			ps.setString(2, cardPic);
			rs = ps.executeQuery();
			if (rs.next()) {
				cp = new CardPic(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return cp;
	}

	public void addOntherCardPic(long mainPicId, String cardPic) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardPic cp = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_card_pic(mainPicId,cardPic)values(?,?) ");
			ps.setLong(1, mainPicId);
			ps.setString(2, cardPic);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void replaceCardPic(long picId, String cardPic) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardPic cp = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update  baby_card_pic set cardPic=? where picId=?");
			ps.setString(1, cardPic);
			ps.setLong(2, picId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void addCardPic(long cardId, String displayurl, String weburl) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_card_pic(cardId,displayurl,weburl)values(?,?,?) ");
			ps.setLong(1, cardId);
			ps.setString(2, displayurl);
			ps.setString(3, weburl);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement("update baby_card set picCount=picCount+1 where cardId=? ");
			ps.setLong(1, cardId);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public CardPic getCardPic(long picId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardPic p = null;

		try {
			conn = getConnection();
			if (picId == -1) {
				ps = conn.prepareStatement("select * from baby_card_pic  order by picId desc");
			} else {
				ps = conn.prepareStatement("select * from baby_card_pic  where picId>?");
				ps.setLong(1, picId);
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				p = new CardPic(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return p;
	}

	public Collection getCardPicByCardId(long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardPic p = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from baby_card_pic  where cardId=? order by cardId ");
			ps.setLong(1, cardId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardPic(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}
	
	public long getCardPicCountByCardId(long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		long count =0;
		try {
			conn = getConnection();

			ps = conn.prepareStatement("select count(*) from baby_card_pic  where cardId=?");
			ps.setLong(1, cardId);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getLong(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return count;
	}

	public Collection getCardPicByMainCardId(long mainCardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardPic p = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_pic  where mainPicId=? or picId=?");
			ps.setLong(1, mainCardId);
			ps.setLong(2, mainCardId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardPic(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getCardPicCollection() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardPic p = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from baby_card_pic ");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardPic(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public void deleteCardPic(long picId, long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardPic p = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_pic where picId=?");
			ps.setLong(1, picId);
			rs = ps.executeQuery();
			CardPic temp = null;
			if (rs.next()) {
				temp = new CardPic(rs);
			}
			rs.close();
			ps.close();

			// 删除的是幅图 ，直接删除后退出
			if (temp.getMainPicId() != 0) {
				ps = conn.prepareStatement("delete from baby_card_pic where picId=?");
				ps.setLong(1, picId);
				ps.executeUpdate();
				ps.close();
				return;
			}

			// 处理幅图转主图
			// 1、找一张幅图
			ps = conn.prepareStatement("select * from baby_card_pic where mainPicId=?");
			ps.setLong(1, picId);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = new CardPic(rs);
				rs.close();
				ps.close();
			} else {
				rs.close();
				ps.close();
				// 没有幅图,直接删除后退出
				ps = conn.prepareStatement("delete from baby_card_pic where picId=?");
				ps.setLong(1, picId);
				ps.executeUpdate();
				ps.close();
				return;
			}

			// 2、重置主图图片地址为幅图地址
			ps = conn.prepareStatement("update baby_card_pic set cardpic=? where picId=?");
			ps.setString(1, temp.getCardpic());
			ps.setLong(2, picId);
			ps.executeUpdate();
			ps.close();

			// 3、删除该幅图
			ps = conn.prepareStatement("delete from baby_card_pic where picId=?");
			ps.setLong(1, temp.getPicId());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}
	
	public void mainCardPic(long picId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_pic where picId=?");
			ps.setLong(1, picId);
			rs = ps.executeQuery();
			CardPic temp = null;
			if (rs.next()) {
				temp = new CardPic(rs);
			}
			rs.close();
			ps.close();
			
			if(picId==temp.getMainPicId() || temp.getMainPicId()==0){
				System.out.println("不需要更改主图");
				return;
			}
			
			
			ps = conn.prepareStatement("select * from baby_card_pic where picId=?");
			ps.setLong(1, temp.getMainPicId());
			rs = ps.executeQuery();
			CardPic main = null;
			if (rs.next()) {
				main = new CardPic(rs);
			}
			rs.close();
			ps.close();
			
			// 2、重置主图图片地址为幅图地址
			ps = conn.prepareStatement("update baby_card_pic set cardpic=? where picId=?");
			ps.setString(1, main.getCardpic());
			ps.setLong(2, picId);
			ps.executeUpdate();
			ps.close();
			
			
			ps = conn.prepareStatement("update baby_card_pic set cardpic=? where picId=?");
			ps.setString(1, temp.getCardpic());
			ps.setLong(2, temp.getMainPicId());
			ps.executeUpdate();
			ps.close();
			
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void favCardPic(long picId, long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardPic p = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("update  baby_card_pic set favCount=favCount+1  where picId=?");
			ps.setLong(1, picId);
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("update  baby_card set favCount=favCount+1  where cardId=?");
			ps.setLong(1, cardId);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Collection pic() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardPic p = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from baby_card_pic  where cardpic is null and displayurl is not null");
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public static void main(String arg[]) {

	}

}
