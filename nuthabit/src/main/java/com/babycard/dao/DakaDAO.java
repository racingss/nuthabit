package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.nuthabit.dao.SampleDAO;

public class DakaDAO extends SampleDAO {
	public void add(long kId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from daka where kId=?");
			ps.setLong(1, kId);
			rs = ps.executeQuery();
			if (rs.next()) {
				rs.close();
				ps.close();
				ps = conn.prepareStatement("update daka set dakaTime=?,dakaNum=dakaNum+1 where kId=?");
				ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
				ps.setLong(2, kId);
				ps.executeUpdate();

			} else {
				rs.close();
				ps.close();
				ps = conn.prepareStatement("insert into daka(kId,dakaTime,dakaNum)values(?,?,1);");
				ps.setLong(1, kId);
				ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
				ps.executeUpdate();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Daka get(long kId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;

		Daka d = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from daka where kId=?");
			ps.setLong(1, kId);
			rs = ps.executeQuery();
			if (rs.next()) {
				d = new Daka(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return d;
	}

	public static void main(String arg[]) {
		long kId=2;
		DakaDAO dao = new DakaDAO();
		Daka d = dao.get(kId);
		if(d==null){
			//第一次打卡
			dao.add(kId);
			System.out.println("第一次打卡");
		}else{
			//判断打卡能否连续
			Calendar c1 = Calendar.getInstance();
			c1.setTime(new Date());
			c1.add(c1.DAY_OF_MONTH, -1);
			Date yesterday =c1.getTime();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String yes = format1.format(c1.getTime());
			
			if(yes.equals(d.getDakaTime().toString().substring(0, 10))){
				//可以打卡
				dao.add(kId);
				System.out.println("打卡了："+(d.getDakaNum()+1));
			}else{
				//超时无法打卡ß
				System.out.println("不能打卡");
			}
			
		
		}

	}

}
