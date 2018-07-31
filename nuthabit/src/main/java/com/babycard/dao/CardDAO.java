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

public class CardDAO extends SampleDAO {
	static long NUMS = 9;

	public Card addCard(String meaning) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Card c = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card where meaning=? ");
			ps.setString(1, meaning);
			rs = ps.executeQuery();

			if (rs.next()) {
				c = new Card(rs);
			} else {
				rs.close();
				ps.close();
				ps = conn.prepareStatement("insert into baby_card(meaning)values(?) ");
				ps.setString(1, meaning);
				ps.executeUpdate();
				ps.close();
				return addCard(meaning);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return c;
	}

	public void updateCardCount(long cardId, long count) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Card c = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("update  baby_card set picCount=? where cardId=? ");
			ps.setLong(1, count);
			ps.setLong(2, cardId);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void updateCardAge(long cardId, long age) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Card c = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("update  baby_card set age=? where cardId=? ");
			ps.setLong(1, age);
			ps.setLong(2, cardId);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void updateCardTag(long cardId, long tagId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Card c = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from baby_bind_card_tag where cardId=? ");
			ps.setLong(1, cardId);
			ps.executeUpdate();

			ps.close();
			ps = conn.prepareStatement("insert into baby_bind_card_tag(cardId,tagId)values(?,?)");
			ps.setLong(1, cardId);
			ps.setLong(2, tagId);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void updateCardDefaultPic(long cardId, String defaultPic) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Card c = null;

		try {
			conn = getConnection();

			ps = conn.prepareStatement("update baby_card set defaultPic =? where cardId=?");
			ps.setString(1, defaultPic);
			ps.setLong(2, cardId);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Card deleteCard(long cardId, long kId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Card c = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from baby_card where cardId=? and kId=? ");
			ps.setLong(1, cardId);
			ps.setLong(2, kId);
			ps.executeUpdate();

			ps.close();
			ps = conn.prepareStatement("delete from baby_card_history where cardId=?");
			ps.setLong(1, cardId);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return c;
	}

	public Card addCard(String meaning, long kId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Card c = null;

		try {
			conn = getConnection();

			ps = conn.prepareStatement("insert into baby_card(meaning,kId)values(?,?) ");
			ps.setString(1, meaning);
			ps.setLong(2, kId);
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("select * from baby_card where kId=? order by cardId desc");
			ps.setLong(1, kId);
			rs = ps.executeQuery();
			if (rs.next()) {
				c = new Card(rs);
			}

			ps = conn
					.prepareStatement("insert into baby_card_meaning(meaning,languageId,cardId,picId)values(?,0,?,0) ");
			ps.setString(1, meaning);
			ps.setLong(2, c.getCardId());
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return c;
	}

	public Card addCard(String meaning, String picUrl, long tagId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Card c = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select count(*) from baby_bind_card_tag where tagId=?");
			ps.setLong(1, tagId);
			rs = ps.executeQuery();
			long cardNums = 0;
			if (rs.next()) {
				cardNums = rs.getLong(1);
			}
			rs.close();
			ps.close();

			ps = conn.prepareStatement("insert into baby_card (meaning,picCount,cardIndex) values(?,1,?)");
			ps.setString(1, meaning);
			ps.setLong(2, cardNums + 1);
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("select * from baby_card where meaning=? order by cardId desc");
			ps.setString(1, meaning);
			rs = ps.executeQuery();
			if (rs.next()) {
				c = new Card(rs);
			} else {
				return null;
			}
			rs.close();
			ps.close();

			ps = conn.prepareStatement("insert into baby_bind_card_tag(cardId,tagId)values(?,?)");
			ps.setLong(1, c.getCardId());
			ps.setLong(2, tagId);
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("insert into baby_card_meaning(meaning,cardId)values(?,?)");
			ps.setString(1, meaning);
			ps.setLong(2, c.getCardId());
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("insert into baby_card_pic(cardId,cardpic)values(?,?)");
			ps.setLong(1, c.getCardId());
			ps.setString(2, picUrl);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return c;
	}

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
			ps = conn.prepareStatement("update baby_card set picCount=picCount+1 where cardId=? ");
			ps.setLong(1, cardId);
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

	// public void BindMeaningPic(CardMeaning m, CardPic p) {
	// Connection conn;
	// PreparedStatement ps;
	// ResultSet rs;
	// conn = null;
	// ps = null;
	// rs = null;
	// try {
	// conn = getConnection();
	// ps = conn.prepareStatement("insert into
	// baby_bind_meaning_pic(meaningId,picId)values(?,?) ");
	// ps.setLong(1, m.getMeaningId());
	// ps.setLong(2, p.getPicId());
	// ps.executeUpdate();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } finally {
	// close(conn, ps, rs);
	// }
	// }

	public void addCardMeaning(CardMeaning m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_meaning where cardId=? and languageId=? ");
			ps.setLong(1, m.getCardId());
			ps.setLong(2, m.getLanguageId());
			rs = ps.executeQuery();

			if (rs.next()) {
				// update
				rs.close();
				ps.close();
				ps = conn.prepareStatement("update baby_card_meaning set meaning=? where languageId=? and cardId=?");
				ps.setString(1, m.getMeaning());
				ps.setLong(2, m.getLanguageId());
				ps.setLong(3, m.getCardId());
				ps.executeUpdate();
				if (m.getLanguageId() == 0) {
					rs.close();
					ps.close();
					ps = conn.prepareStatement("update baby_card set meaning=? where cardId=?");
					ps.setString(1, m.getMeaning());
					ps.setLong(2, m.getCardId());
					ps.executeUpdate();
				}

			} else {
				// insert
				rs.close();
				ps.close();
				ps = conn.prepareStatement("insert into baby_card_meaning(meaning,languageId,cardId)values(?,?,?) ");
				ps.setString(1, m.getMeaning());
				ps.setLong(2, m.getLanguageId());
				ps.setLong(3, m.getCardId());
				ps.executeUpdate();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void deleteCardSound(long soundId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from baby_card_sound where soundId=?");
			ps.setLong(1, soundId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Collection getCardSoundList(long cardId, long kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_sound where cardId=? and kehuId=? ");
			ps.setLong(1, cardId);
			ps.setLong(2, kehuId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardSound(rs));
			}
			rs.close();
			ps.close();

			ps = conn.prepareStatement("select * from baby_card_sound where cardId=? and kehuId<>? and status>0 ");
			ps.setLong(1, cardId);
			ps.setLong(2, kehuId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new CardSound(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	// public void cardMeaningSound(long meaningId, String sound) {
	// Connection conn;
	// PreparedStatement ps;
	// ResultSet rs;
	// conn = null;
	// ps = null;
	// rs = null;
	// try {
	// conn = getConnection();
	//
	// ps = conn.prepareStatement("update baby_card_meaning set sound=? where
	// meaningId=?");
	// ps.setString(1, sound);
	// ps.setLong(2, meaningId);
	// ps.executeUpdate();
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } finally {
	// close(conn, ps, rs);
	// }
	// }

	public void cardMeaningSoundQue(long meaningId, String soundQue) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement("update baby_card_meaning set soundQue=? where meaningId=?");
			ps.setString(1, soundQue);
			ps.setLong(2, meaningId);
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

	// public Card getCard(long cardId, long tagId, boolean staticFlag) {
	// Connection conn;
	// PreparedStatement ps;
	// ResultSet rs;
	// conn = null;
	// ps = null;
	// rs = null;
	// Card c = null;
	//
	// try {
	// conn = getConnection();
	// // if (cardId == -1) {
	// // ps = conn.prepareStatement("select * from baby_card where tagId=?
	// // and picCount>0 order by cardId desc");
	// // ps.setLong(1, tagId);
	// // }
	// if (staticFlag) {
	// ps = conn.prepareStatement("select * from baby_card where picCount>0 and
	// cardId=?");
	// ps.setLong(1, cardId);
	// } else {
	// ps = conn.prepareStatement(
	// "select * from baby_card where picCount>0 and cardId>? and cardId in
	// (select cardId from baby_bind_card_tag where tagId=?)");
	// ps.setLong(1, cardId);
	// ps.setLong(2, tagId);
	// }
	// rs = ps.executeQuery();
	// if (rs.next()) {
	// c = new Card(rs);
	// } else {
	// return null;
	// }
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } finally {
	// close(conn, ps, rs);
	// }
	// return c;
	// }

	public Card getCardByCardId(long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Card c = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card where cardId=?");
			ps.setLong(1, cardId);
			rs = ps.executeQuery();
			if (rs.next()) {
				c = new Card(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return c;
	}

	public Card getCardByCardIndex(long cardIndex, String flag, long tagId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Card c = null;
		try {
			conn = getConnection();
			if (flag == null || flag.equals("n")) {
				ps = conn.prepareStatement(
						"select * from baby_card where picCount>0 and cardIndex>? and cardId in (select cardId from baby_bind_card_tag where tagId=?)");
			} else {
				ps = conn.prepareStatement(
						"select * from baby_card where picCount>0 and cardIndex<? and cardId in (select cardId from baby_bind_card_tag where tagId=?) order by cardIndex desc");
			}
			ps.setLong(1, cardIndex);
			ps.setLong(2, tagId);

			rs = ps.executeQuery();

			if (rs.next()) {
				c = new Card(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return c;
	}

	public Collection getCardListByTag(long tagId, long page) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();

			StringBuffer sb = new StringBuffer(
					"select * from baby_card where status=0 and cardId in( select cardId from baby_bind_card_tag where tagId=?) order by cardIndex limit ");
			sb.append((page - 1) * NUMS);
			sb.append(",");
			sb.append(NUMS);
			ps = conn.prepareStatement(sb.toString());
			ps.setLong(1, tagId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getCardListRecent(long kehuId) {
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
					"select cardId from baby_card_history where kId=? order by historyId desc limit 0,7");
			ps.setLong(1, kehuId);

			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(rs.getLong(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getCardListByKid(long kId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card where  kId=? order by cardId desc limit 0,20");
			ps.setLong(1, kId);

			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getCardListFav(long kehuId) {
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
					"select * from baby_card where cardId in( select cardId from baby_card_fav where kehuId=? and status=0 order by favId desc) limit 0,20");
			ps.setLong(1, kehuId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getCardListByTagAge(long tagId, long age) {
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
					"select * from baby_card where cardId in( select cardId from baby_bind_card_tag where tagId=?) and age=? order by cardIndex");
			ps.setLong(1, tagId);
			ps.setLong(2, age);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getCardListByAge(long age, long page) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer("select * from baby_card where status=0 and age=? order by cardIndex limit ");
			sb.append((page - 1) * NUMS);
			sb.append(",");
			sb.append(NUMS);
			ps = conn.prepareStatement(sb.toString());
			ps.setLong(1, age);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllCardListOrderByFavCount(long page) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer("select * from baby_card where status=0 order by favCount desc limit ");
			sb.append((page - 1) * NUMS);
			sb.append(",");
			sb.append(NUMS);
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllCardListOrderByNew(long page) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();
			// StringBuffer sb = new StringBuffer("select * from baby_card where
			// kId=0 order by cardId desc limit ");
			StringBuffer sb = new StringBuffer("select * from baby_card where status=0 and picCount>=5 order by cardId desc limit ");
			sb.append((page - 1) * NUMS);
			sb.append(",");
			sb.append(NUMS);
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllCardListOrderByAZ(long page) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer("select * from baby_card where status=0 order by meaning limit ");
			sb.append((page - 1) * NUMS);
			sb.append(",");
			sb.append(NUMS);
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllCardList() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllCardListOrderByFavCount() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card where status=0 order by favCount desc limit 0,10");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection searchCard(String qString, long page) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();

			StringBuffer sb = new StringBuffer(
					" select * from baby_card where status=0 and meaning like '%" + qString + "%' limit ");
			sb.append((page - 1) * NUMS);
			sb.append(",");
			sb.append(NUMS);
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection searchCard(String qString, long page, long age) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();

			StringBuffer sb = new StringBuffer(
					" select * from baby_card where status=0 and meaning like '%" + qString + "%' and age=? limit ");
			sb.append((page - 1) * NUMS);
			sb.append(",");
			sb.append(NUMS);
			ps = conn.prepareStatement(sb.toString());
			ps.setLong(1, age);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getCardListByIndex(long cardIndex) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from baby_card where cardIndex=? limit 0,8");
			ps.setLong(1, cardIndex);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Card getTestCard(long tagId, long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Card c = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select * from baby_card where picCount>0 and cardId in (select cardId from baby_bind_card_tag where tagId=?) order by rand() limit 1");
			ps.setLong(1, tagId);
			rs = ps.executeQuery();

			if (rs.next()) {
				c = new Card(rs);
				if (c.getCardId() == cardId) {
					System.out.println("lucky,重复了");
					return getTestCard(tagId, cardId);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return c;
	}

	public CardTag addCardTag(String tag) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardTag t = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_tag where tag=? ");
			ps.setString(1, tag);
			rs = ps.executeQuery();

			if (rs.next()) {
				t = new CardTag(rs);
			} else {
				rs.close();
				ps.close();
				ps = conn.prepareStatement("insert into baby_card_tag(tag)values(?) ");
				ps.setString(1, tag);
				ps.executeUpdate();
				ps.close();
				return (addCardTag(tag));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return t;
	}

	public CardTag addCardTag(CardTag t) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_card_tag(tag,kehuId)values(?,?) ");
			ps.setString(1, t.getTag());
			ps.setLong(2, t.getKehuId());
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("select * from baby_card_tag where kehuId=? order by tagId desc ");
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

	public long getTagId(long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		long tagId = 0;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select tagId from baby_bind_card_tag where cardId=? ");
			ps.setLong(1, cardId);
			rs = ps.executeQuery();

			if (rs.next()) {
				tagId = rs.getLong(1);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return tagId;
	}

	public void BindCardTag(long cardId, long tagId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_bind_card_tag(cardId,tagId)values(?,?) ");
			ps.setLong(1, cardId);
			ps.setLong(2, tagId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	/*
	 * 以下部分是为了从网络抓取图片
	 */

	private static void downloadPicture(String urlList) {
		URL url = null;
		int imageNumber = 0;

		try {
			url = new URL(urlList);
			DataInputStream dataInputStream = new DataInputStream(url.openStream());

			String imageName = "/Users/wangjunwu/Downloads/temp/" + System.currentTimeMillis() + ".jpg";

			FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int length;

			while ((length = dataInputStream.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			byte[] context = output.toByteArray();
			fileOutputStream.write(output.toByteArray());
			dataInputStream.close();
			fileOutputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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

	public void processCardIndex() {
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
			ps = conn.prepareStatement("select * from baby_card  order by favCount desc, cardId");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Card(rs));
			}
			rs.close();
			ps.close();

			Iterator it = coll.iterator();
			int index = 0;
			ps = conn.prepareStatement("update baby_card set cardIndex=? where cardId=?");
			while (it.hasNext()) {
				Card c = (Card) it.next();
				ps.setLong(1, index++);
				ps.setLong(2, c.getCardId());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

	}

	public static void main(String arg[]) {
		CardDAO dao = new CardDAO();
		// System.out.println(dao.getAllCardListOrderByFavCount().size());
		Collection coll = dao.getAllCardList();
		Iterator it = coll.iterator();
		CardPicDAO cDAO = new CardPicDAO();
		while (it.hasNext()) {
			Card c = (Card) it.next();
			System.out.println(c.toString());
			dao.updateCardCount(c.getCardId(), cDAO.getCardPicCountByCardId(c.getCardId()));
		}

		// Iterator it = dao.getCardListRecent(2670).iterator();
		// while (it.hasNext()) {
		// Card c = (Card) it.next();
		// System.out.println(c.toString());
		// }

		if (true)
			return;

		// Iterator it = dao.getAllCardList().iterator();
		// while(it.hasNext()){
		// Card c = (Card)it.next();
		//
		// long count = new CardPicDAO().getCardPicCountByCardId(c.getCardId());
		//
		// dao.updateCardCount(c.getCardId(), count);
		//
		//
		// System.out.println(c.toString());
		// System.out.println(count);
		//
		// }

	}

}
