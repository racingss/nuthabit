package com.babycard.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class KehuDAO extends SampleDAO {

	public KehuDAO() {
	}

	public Kehu addKehu(Kehu k){
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into kehu(shouji,unionId,openId,nickname,sex,headimgurl,jifen,status,tuijianren,zhuceri,pwd,kehuId,dengluri,province,city,country)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			ps.setString(1, k.getShouji());
			ps.setString(2, k.getUnionId());
			ps.setString(3, k.getOpenId());
			ps.setString(4, k.getNickname());
			ps.setString(5, k.getSex());
			ps.setString(6, k.getHeadimgurl());
			ps.setLong(7, k.getJifen());
			ps.setLong(8, k.getStatus());
			ps.setString(9, k.getTuijianren());
			ps.setString(10, k.getZhuceri());
			ps.setString(11, k.getPwd());
			ps.setString(12, k.getKehuId());
			ps.setString(13, k.getDengluri());
			ps.setString(14, k.getProvince());
			ps.setString(15, k.getCity());
			ps.setString(16, k.getCountry());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return getKehu("kehuId", k.getKehuId());
	}

	public void updateOpenId(Kehu k) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update kehu set openId=? where kehuId=? ");
			ps.setString(1, k.getOpenId());
			ps.setString(2, k.getKehuId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return;
	}

	public void updateNickname(Kehu k) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update kehu set nickname=? where id=? ");
			ps.setString(1, k.getNickname());
			ps.setLong(2, k.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return;
	}

	public Kehu findByNickname(String nickname) throws Exception {
		if (nickname == null)
			return null;
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Kehu k = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from  kehu where nickname=? ");
			ps.setString(1, nickname);
			rs = ps.executeQuery();
			if (rs.next()) {
				k = new Kehu(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return k;
	}

	public void updateShouji(Kehu k) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update kehu set shouji=? where kehuId=? ");
			ps.setString(1, k.getShouji());
			ps.setString(2, k.getKehuId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return;
	}

	public void updateZhifubao(Kehu k) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update kehu set zhifubao=?, quanming=? where kehuId=? ");
			ps.setString(1, k.getZhifubao());
			ps.setString(2, k.getQuanming());
			ps.setString(3, k.getKehuId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return;
	}

	public void updateTuijianren(Kehu k) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update kehu set tuijianren=? where kehuId=? ");
			ps.setString(1, k.getTuijianren());
			ps.setString(2, k.getKehuId());
			ps.executeUpdate();
			// System.out.println(ps.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return;
	}

	public void updatePassword(Kehu k) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update kehu set pwd=? where id=? ");
			ps.setString(1, k.getPwd());
			ps.setLong(2, k.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return;
	}

	public void updateJifen(Kehu k, long jifen) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update kehu set jifen+=? where id=? ");
			ps.setLong(1, jifen);
			ps.setLong(2, k.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return;
	}

	public void denglu(Kehu k) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update kehu set denglucishu=denglucishu+1,dengluri=? where id=? ");
			ps.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));
			ps.setLong(2, k.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return;
	}

	public Kehu getKehu(String type, String value) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Kehu k;
		conn = null;
		ps = null;
		rs = null;
		k = null;
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer("select * from kehu where ");
			sb.append(type);
			sb.append("=? ");
			ps = conn.prepareStatement(sb.toString());
			ps.setString(1, value);
			rs = ps.executeQuery();
			if (rs.next())
				k = new Kehu(rs);
			System.out.println((new StringBuilder("kehuDAO :")).append(ps.toString()).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return k;
	}

	public Kehu getDaili(String shouji) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Kehu k;
		conn = null;
		ps = null;
		rs = null;
		k = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from kehu where shouji=? and dailibiaoji>0 ");
			ps.setString(1, shouji);
			rs = ps.executeQuery();
			if (rs.next())
				k = new Kehu(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return k;
	}

	public Collection getAllDaili() throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Kehu k;
		conn = null;
		ps = null;
		rs = null;
		Collection c = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from kehu where dailibiaoji>0 ");

			rs = ps.executeQuery();
			while (rs.next())
				c.add(new Kehu(rs));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return c;
	}

	public Collection getAll() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Kehu k;
		conn = null;
		ps = null;
		rs = null;
		Collection c = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from kehu ");

			rs = ps.executeQuery();
			while (rs.next())
				c.add(new Kehu(rs));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return c;
	}

	public Collection getAllByShouji(String shouji) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Kehu k;
		conn = null;
		ps = null;
		rs = null;
		Collection c = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from kehu where shouji=?");
			ps.setString(1, shouji);

			rs = ps.executeQuery();
			while (rs.next())
				c.add(new Kehu(rs));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return c;
	}

	public Kehu findById(Integer id) throws Exception {
		if (id == null)
			return null;
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Kehu k = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from  kehu where id=? ");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				k = new Kehu(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return k;
	}
	
	public static void main(String[] arg) {
		System.out.println("开始");
		Iterator it = new KehuDAO().getAll().iterator();// .getAllByShouji("13601621719").iterator();
		// .getAll().iterator();//
		int i = 0;
		while (it.hasNext()) {
			Kehu k = (Kehu) it.next();
		    //System.out.println(k.toString());
			//
			// HongbaoDAO dao = new HongbaoDAO();
			// Hongbao h = dao.getMaxCanUse(k.getKehuId(), 1000);
			// if(h!=null){
			// System.out.println(h.toString());
			// }else{
			// ++i;
			System.out.println("发红包了:" + i);
			//Fahongbao.kuaisufabao(k.getKehuId(), k.getShouji(), 20, 100, 14);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// }
		}

		System.out.println("结束,发了:" + i + "红包");
	}
	
	public Kehu updateKehu(Kehu k){
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"update kehu set shouji=? ,unionId=?,openId=?,nickname=?,sex=?,headimgurl=?,jifen=?,status=?,tuijianren=?,zhuceri=?,pwd=?,kehuId=?,dengluri=?,province=?,city=?,country=? where id=?");
			ps.setString(1, k.getShouji());
			ps.setString(2, k.getUnionId());
			ps.setString(3, k.getOpenId());
			ps.setString(4, k.getNickname());
			ps.setString(5, k.getSex());
			ps.setString(6, k.getHeadimgurl());
			ps.setLong(7, k.getJifen());
			ps.setLong(8, k.getStatus());
			ps.setString(9, k.getTuijianren());
			ps.setString(10, k.getZhuceri());
			ps.setString(11, k.getPwd());
			ps.setString(12, k.getKehuId());
			ps.setString(13, k.getDengluri());
			ps.setString(14, k.getProvince());
			ps.setString(15, k.getCity());
			ps.setString(16, k.getCountry());
			ps.setLong(17, k.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return getKehu("kehuId", k.getKehuId());
	}
}