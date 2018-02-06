package com.nuthabit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MessageDAO extends SampleDAO {
	public void add(MyplanMessage m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into myplan_message (kehuId,sourceId,sourceName,contect,mainId,msgTime,status,sourcehead)values(?,?,?,?,?,?,?,?)");
			ps.setString(1, m.getKehuId());
			ps.setString(2, m.getSourceId());
			ps.setString(3, m.getSourceName());
			ps.setString(4, m.getContent());
			ps.setLong(5, m.getMainId());
			ps.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
			ps.setLong(7, m.getStatus());
			ps.setString(8, m.getSourcehead());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

}
