package com.babycard.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class KehuDAO extends SampleDAO {

	public KehuDAO() {
	}

	public Kehu addKehu(Kehu k) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into kehu(shouji,unionId,openId,nickname,sex,headimgurl,jifen,status,tuijianren,zhuceri,pwd,kehuId,dengluri,province,city,country,languageId,fromId)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
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
			ps.setLong(17, k.getLanguageId());
			ps.setLong(18, k.getFromId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 因为怕昵称问题导致用户注册不成功，所以名字改为微信用户
			try {
				ps.close();
				k.setNickname("微信用户");
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.err.println("严重警告！！！！！！*******二次注册失败******");
			}

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

	public void updateLanguageId(long kId, long languageId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update kehu set languageId=? where id=? ");
			ps.setLong(1, languageId);
			ps.setLong(2, kId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return;
	}
	
	public void updateLanguageId_2(long kId, long languageId_2) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update kehu set languageId_2=? where id=? ");
			ps.setLong(1, languageId_2);
			ps.setLong(2, kId);
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

	public void updateCode(Kehu k) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update kehu set code=? where kehuId=? ");
			ps.setString(1, k.getCode());
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

	public String getCode(long id) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select code from kehu where id=? ");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public Kehu getKehuByCode(String code) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from kehu where code=? ");
			ps.setString(1, code);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Kehu(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
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

	public boolean updateJifen(long kId, long jifen, boolean plus, String desc) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			long balance = 0;
			ps = conn.prepareStatement("select * from kehu where id=?");
			ps.setLong(1, kId);
			rs = ps.executeQuery();
			if (rs.next()) {
				balance = rs.getLong("jifen");
			}
			rs.close();
			ps.close();

			if (plus) {
				balance += jifen;
				ps = conn.prepareStatement("update kehu set jifen =jifen+? where id=? ");
			} else {
				balance -= jifen;
				//临时先取消
				if (balance < 0) {
					System.err.println("积分不够:" + kId + ":" + balance + ":" + plus + ":" + balance + ":" + ":" + jifen);
					return false;
				}
				ps = conn.prepareStatement("update kehu set jifen =jifen-? where id=? ");
			}
			ps.setLong(1, jifen);
			ps.setLong(2, kId);
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement(
					"insert into kehu_jifen_history(kId,point,jifenDate,description,balance)values(?,?,?,?,?)");
			ps.setLong(1, kId);
			ps.setLong(2, jifen);
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setString(4, desc);
			ps.setLong(5, balance);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return false;
	}

	public void addMember(KehuCardMember k) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from kehu_card_member where kehuId=?");
			ps.setString(1, k.getKehuId());
			rs = ps.executeQuery();
			KehuCardMember temp = null;
			if (rs.next()) {
				temp = new KehuCardMember(rs);
			}
			rs.close();
			ps.close();

			if (temp == null) {
				ps = conn.prepareStatement(
						"insert into kehu_card_member(kehuId,memberLevel,createDate,closeDate)values(?,?,?,?)");
				ps.setString(1, k.getKehuId());
				ps.setLong(2, k.getMemberLevel());
				ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
				ps.setTimestamp(4, k.getCloseDate());
			} else {
				ps = conn.prepareStatement(
						"update kehu_card_member set memberLevel=?,createDate=?,closeDate=? where kehuId=?");
				k.rebuild(temp.getCloseDate());
				ps.setLong(1, k.getMemberLevel());
				ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
				ps.setTimestamp(3, k.getCloseDate());
				ps.setString(4, k.getKehuId());
			}
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return;
	}

	public KehuCardMember getMember(String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		KehuCardMember temp = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from kehu_card_member where kehuId=?");
			ps.setString(1, kehuId);
			rs = ps.executeQuery();

			if (rs.next()) {
				temp = new KehuCardMember(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return temp;
	}

	public void denglu(Kehu k) {
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

			if (k != null) {
				rs.close();
				ps.close();
				ps = conn.prepareStatement("select * from baby where kId=?");
				ps.setLong(1, k.getId());
				rs = ps.executeQuery();
				if (rs.next()) {
					k.b = new Baby(rs);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return k;
	}

	public long getJifen(long kId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Kehu k;
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select jifen from kehu where id=? ");
			ps.setLong(1, kId);
			rs = ps.executeQuery();
			if (rs.next())
				return rs.getLong(1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return 0;
	}

	public Kehu getKehuById(long id) {
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
			ps = conn.prepareStatement("select * from kehu where id=?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				k = new Kehu(rs);

			if (k != null) {
				rs.close();
				ps.close();
				ps = conn.prepareStatement("select * from baby where kId=?");
				ps.setLong(1, k.getId());
				rs = ps.executeQuery();
				if (rs.next()) {
					k.b = new Baby(rs);
				}
			}
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
	
	public Collection getFromlist(long fromId) {
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
			ps = conn.prepareStatement("select * from kehu where fromId=? order by id desc");
			ps.setLong(1, fromId);

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

	public Kehu updateKehu(Kehu k) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"update kehu set shouji=? ,unionId=?,openId=?,nickname=?,sex=?,headimgurl=?,jifen=?,status=?,tuijianren=?,zhuceri=?,pwd=?,kehuId=?,dengluri=?,province=?,city=?,country=?,email=?,wx=? where id=?");
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
			ps.setString(17, k.getEmail());
			ps.setString(18, k.getWx());
			ps.setLong(19, k.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return getKehu("kehuId", k.getKehuId());
	}

	public static void main(String[] arg) {
		KehuDAO dao = new KehuDAO();
		System.out.println(dao.getFromlist(2680).size());
//		Collection coll = dao.getAll();
//		Iterator it = coll.iterator();
//		while(it.hasNext()){
//			Kehu k = (Kehu)it.next();
//			System.out.println(k.toString());
//			KehuCardMember m =  dao.getMember(k.getKehuId());
//			if(m==null){
//				System.out.println("非付费用户");
//				m= new KehuCardMember();
//				m.setKehuId(k.getKehuId());
//				m.setMemberLevel(m.MEMBER_LEVEL_LIFELONG);
//				dao.addMember(m);
//			}else{
//				System.out.println("付费用户");
//			}
//			
//		}
		
		
	}

}