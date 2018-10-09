package com.babycard.dao;

import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.nuthabit.dao.SampleDAO;

public class VoiceDAO extends SampleDAO {

	public Voice add(Voice v) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_voice(cardId,picId,kId,voiceUrl)values(?,?,?,?)");
			ps.setLong(1, v.getCardId());
			ps.setLong(2, v.getPicId());
			ps.setLong(3, v.getkId());
			ps.setString(4, v.getVoiceUrl());
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("select * from baby_voice where kId=? order by id desc");
			ps.setLong(1, v.getkId());
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Voice(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public Collection getAll() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_voice");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Voice(rs));
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
//		Voice v = new Voice();
//		v.setCardId(1);
//		v.setkId(12);
//		v.setPicId(3);
//		v.setVoiceUrl("aa");
//		System.out.println(new VoiceDAO().add(v).toString());
		System.out.println(new VoiceDAO().getAll());
	}

}
