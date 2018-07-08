package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nuthabit.dao.SampleDAO;

public class BabyDAO extends SampleDAO {

	public void addBaby(Baby b) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby(kId,babyName,bYear,bMonth,bDay,headimg,sex)values(?,?,?,?,?,?,?) ");
			ps.setLong(1, b.getkId());
			ps.setString(2, b.getBabyName());
			ps.setLong(3, b.getbYear());
			ps.setLong(4, b.getbMonth());
			ps.setLong(5, b.getbDay());
			ps.setString(6, b.getHeadImg());
			ps.setLong(7, b.getSex());
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}
	
	public Baby getBabyById(long babyId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Baby b=null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from  baby where babyId=?");
			ps.setLong(1, babyId);
			rs = ps.executeQuery();
			if(rs.next()){
				b = new Baby(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return b;
	}
	
	public void updateBaby(Baby b) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update baby set babyName=?,bYear=?,bMonth=?,bDay=?,headimg=? where kId=? ");
			ps.setString(1, b.getBabyName());
			ps.setLong(2, b.getbYear());
			ps.setLong(3, b.getbMonth());
			ps.setLong(4, b.getbDay());
			ps.setString(5, b.getHeadImg());
			ps.setLong(6, b.getkId());
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}
	
	public static void main(String arg[]){
//		Baby b =  new Baby();
//		b.setkId(12);
//		b.setBabyName("yoyo");
//		b.setbYear(2008);
//		b.setbMonth(8);
//		b.setbDay(26);
//		b.setHeadImg("Ailsa.jpg");
//		new BabyDAO().updateBaby(b);
		System.out.println(new BabyDAO().getBabyById(1).toString());
	}
	
	

}
