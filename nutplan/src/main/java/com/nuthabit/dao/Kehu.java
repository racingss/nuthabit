package com.nuthabit.dao;

import java.sql.ResultSet;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Kehu {

	private long id;
	private String shouji;
	private String unionId;
	private String openId;
	private String nickname;
	private String sex;
	private String headimgurl;
	private long jifen;
	private long status;
	private String tuijianren;
	private String zhuceri;
	private String dengluri;
	private String pwd;
	private String kehuId;
	private long guanlibiaoji = 0;
	private long dailibiaoji = 0;
	private String zhifubao = null;
	private String quanming = null;
	private String country = null;
	private String city = null;
	private String province = null;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getMD5Str(String str) {
		if (str == null) {
			return null;
		}

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	public String getZhifubao() {
		return zhifubao;
	}

	public void setZhifubao(String zhifubao) {
		this.zhifubao = zhifubao;
	}

	public String getQuanming() {
		return quanming;
	}

	public void setQuanming(String quanming) {
		this.quanming = quanming;
	}

	public long getDailibiaoji() {
		return dailibiaoji;
	}

	public void setDailibiaoji(long dailibiaoji) {
		this.dailibiaoji = dailibiaoji;
	}

	public long getGuanlibiaoji() {
		return guanlibiaoji;
	}

	public void setGuanlibiaoji(long guanlibiaoji) {
		this.guanlibiaoji = guanlibiaoji;
	}

	public String getKehuId() {
		return kehuId;
	}

	public void setKehuId(String kehuId) {
		this.kehuId = kehuId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = this.getMD5Str(pwd);
	}

	public boolean equalPwd(String pwd1) {
		return (this.getMD5Str(pwd1).equals(this.getPwd()));
	}

	public String getZhuceri() {
		return zhuceri;
	}

	public void setZhuceri(String zhuceri) {
		this.zhuceri = zhuceri;
	}

	public String getDengluri() {
		return dengluri;
	}

	public void setDengluri(String dengluri) {
		this.dengluri = dengluri;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShouji() {
		return shouji;
	}

	public void setShouji(String shouji) {
		this.shouji = shouji;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public long getJifen() {
		return jifen;
	}

	public void setJifen(long jifen) {
		this.jifen = jifen;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getTuijianren() {
		return tuijianren;
	}

	public void setTuijianren(String tuijianren) {
		this.tuijianren = tuijianren;
	}

	public Kehu() {
		id = 0L;
		shouji = null;
		unionId = null;
		openId = null;
		nickname = null;
		sex = null;
		headimgurl = null;
		jifen = 0L;
		status = 0L;
		tuijianren = null;
		zhuceri = null;
		dengluri = null;
		pwd = null;
		kehuId = null;
	}

	public String toString() {
		return (new StringBuilder("Kehu [id=")).append(id).append(", shouji=").append(shouji).append(", unionId=")
				.append(unionId).append(", openId=").append(openId).append(", nickname=").append(nickname)
				.append(", sex=").append(sex).append(", headimgurl=").append(headimgurl).append(", jifen=")
				.append(jifen).append(", status=").append(status).append(", tuijianren=").append(tuijianren)
				.append(", zhuceri=").append(zhuceri).append(", dengluri=").append(dengluri).append(", pwd=")
				.append(pwd).append(", kehuId=").append(kehuId).append("]").toString();
	}

	public Kehu(ResultSet rs) {
		id = 0L;
		shouji = null;
		unionId = null;
		openId = null;
		nickname = null;
		sex = null;
		headimgurl = null;
		jifen = 0L;
		status = 0L;
		tuijianren = null;
		zhuceri = null;
		dengluri = null;
		pwd = null;
		kehuId = null;
		try {
			setHeadimgurl(rs.getString("headimgurl"));
			setId(rs.getLong("id"));
			setJifen(rs.getLong("jifen"));
			setNickname(rs.getString("nickname"));
			setOpenId(rs.getString("openId"));
			setSex(rs.getString("sex"));
			setShouji(rs.getString("shouji"));
			setStatus(rs.getLong("status"));
			setTuijianren(rs.getString("tuijianren"));
			setUnionId(rs.getString("unionId"));
			setZhuceri(rs.getString("zhuceri"));
			setDengluri(rs.getString("dengluri"));
			this.pwd = rs.getString("pwd");
			setKehuId(rs.getString("kehuId"));
			this.setDailibiaoji(rs.getLong("dailibiaoji"));
			this.setGuanlibiaoji(rs.getLong("guanlibiaoji"));
			this.setZhifubao(rs.getString("zhifubao"));
			this.setQuanming(rs.getString("quanming"));
			this.setProvince(rs.getString("province"));
			this.setCity(rs.getString("city"));
			this.setCountry(rs.getString("country"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}