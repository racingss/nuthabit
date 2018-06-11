package com.babycard.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import com.baidu.speech.restapi.common.DemoException;
import com.baidu.speech.restapi.ttsdemo.TtsMain;
import com.baidu.translate.demo.Main;
import com.baidu.translate.demo.TransApi;
import com.nuthabit.dao.SampleDAO;

import net.sf.json.JSONObject;

public class SoundDAO extends SampleDAO {

	public Object[] getSoundColl(String soundType, long languageId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_sound where soundType=? and languageId=?");
			ps.setString(1, soundType);
			ps.setLong(2, languageId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Sound(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll.toArray();
	}

	public Collection getCardSoundColl() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_sound where status=0 order by soundId limit 0,10");
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

	public void addCardSound(CardSound s, boolean deleteFlag) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			if (deleteFlag) {
				ps = conn.prepareStatement("delete from baby_card_sound where picId=? and languageId=? ");
				ps.setLong(1, s.getPicId());
				ps.setLong(2, s.getLanguageId());
				ps.executeUpdate();
				ps.close();
			}

			ps = conn.prepareStatement(
					"insert into baby_card_sound(languageId,cardId,sound,kehuId,picId)values(?,?,?,?,?) ");
			ps.setLong(1, s.getLanguageId());
			ps.setLong(2, s.getCardId());
			ps.setString(3, s.getSound());
			ps.setLong(4, s.getKehuId());
			ps.setLong(5, s.getPicId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Collection getCardSoundCollByPicId(long picId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			// ps = conn.prepareStatement("select * from baby_card_sound where
			// status=? and picId=?");
			// ps.setLong(1, CardSound.STATUS_CONFIRMED);
			// ps.setLong(2, picId);
			ps = conn.prepareStatement("select * from baby_card_sound where  picId=?");
			ps.setLong(1, picId);
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

	public CardSound getCardSoundByPicId(long picId, long languageId, long cardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardSound s = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_card_sound where  picId=? and cardId=? and languageId=?");
			ps.setLong(1, picId);
			ps.setLong(2, cardId);
			ps.setLong(3, languageId);
			rs = ps.executeQuery();
			if (rs.next()) {
				s = new CardSound(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return s;
	}

	public void updateCardSoundStatus(long soundId, long status) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("update baby_card_sound set status=? where soundId=?");
			ps.setLong(1, status);
			ps.setLong(2, soundId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void addSound(Sound s) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_sound(soundType,languageId,soundUrl)values(?,?,?) ");
			ps.setString(1, s.getSoundType());
			ps.setLong(2, s.getLanguageId());
			ps.setString(3, s.getSoundUrl());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public static void main(String arg[]) {
		// Object soundObjs[] = new SoundDAO().getSoundColl("SUCC_CN");
		// String soundCuss = ((Sound) soundObjs[new
		// Random().nextInt(soundObjs.length)]).getSoundUrl();
		// System.out.println(soundCuss);
		// new SoundDAO().updateCardSoundStatus(2, -1);
		if (true) {
			String soundType = "result_false";
			String word = "打错啦，加油";

			SoundDAO dao = new SoundDAO();
			Sound s = new Sound();
			s.setSoundType(soundType);

			Collection coll = new LanguageDAO().getAllLanguageColl();

			TransApi api = new TransApi(Main.APP_ID, Main.SECURITY_KEY);

			Iterator it = coll.iterator();
			while (it.hasNext()) {
				Language l = (Language) it.next();
				if (l.getLanguageId() == 0 || l.getLanguageId() == 1) {
					try {
						String r = api.getTransResult(word, "zh", l.getSname());
						JSONObject json = JSONObject.fromObject(r.toString());
						String str1 = json.get("trans_result").toString();
						// 去掉[]
						str1 = str1.replace("[", "");
						str1 = str1.replace("]", "");
						JSONObject json1 = JSONObject.fromObject(str1);

						System.out.println("目标语言：" + json1.get("dst"));
						s.setSoundUrl(TtsMain.build(json1.get("dst").toString()));
						dao.addSound(s);
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		if (false) {

			// Collection coll = new CardMeaningDAO().getAllCardMeaning();
			Collection coll = new CardMeaningDAO().getCardMeaningByCardId(2142);
			Iterator it = coll.iterator();
			SoundDAO dao = new SoundDAO();
			while (it.hasNext()) {
				try {
					CardMeaning m = (CardMeaning) it.next();
					if (m.getLanguageId() != 0 && m.getLanguageId() != 1)
						continue;
					if (dao.getCardSoundByPicId(m.getPicId(), m.getLanguageId(), m.getCardId()) != null)
						continue;

					CardSound cs = new CardSound();
					cs.setCardId(m.getCardId());
					cs.setLanguageId(m.getLanguageId());
					cs.setPicId(m.getPicId());
					cs.setSound(TtsMain.build(m.getMeaning()));
					cs.setStatus(1);
					System.out.println(cs.toString());
					dao.addCardSound(cs,true);

					Thread.sleep(100);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
