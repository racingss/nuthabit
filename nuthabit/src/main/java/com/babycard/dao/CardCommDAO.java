package com.babycard.dao;

import java.sql.Connection;
import java.io.*;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class CardCommDAO extends SampleDAO {
	public void add(long cardId, long kId, String comm) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		CardPic cp = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_card_comm(cardId,kId,comm,opTime)values(?,?,?,?)");
			ps.setLong(1, cardId);
			ps.setLong(2, kId);
			ps.setString(3, comm);
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public static void main(String arg[]) {
		new CardCommDAO().add(2, 3, "ni 好啊啊");
	}
}
