package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.nuthabit.dao.SampleDAO;

public class SoundDAO extends SampleDAO {

	public Object[] getSoundColl(String soundType) {
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
			ps = conn.prepareStatement("select * from baby_sound where soundType=?");
			ps.setString(1, soundType);
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

	public static void main(String arg[]) {
		Object soundObjs[] = new SoundDAO().getSoundColl("SUCC_CN");
		String soundCuss = ((Sound) soundObjs[new Random().nextInt(soundObjs.length)]).getSoundUrl();
		System.out.println(soundCuss);

	}

}
