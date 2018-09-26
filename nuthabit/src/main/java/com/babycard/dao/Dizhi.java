package com.babycard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dizhi {

	public static final long STATUS_DEFAULT = 0L;
	public static final long STATUS_NO_DEFAULT = 1L;
	private long id;
	private String kehuId;
	private String province;
	private String city;
	private String qu;
	private String xiangxi;
	private String dianhua;
	private String shoujianren;
	private long status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKehuId() {
		return kehuId;
	}

	public void setKehuId(String kehuId) {
		this.kehuId = kehuId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getQu() {
		return qu;
	}

	public void setQu(String qu) {
		this.qu = qu;
	}

	public String getXiangxi() {
		return xiangxi;
	}

	public void setXiangxi(String xiangxi) {
		this.xiangxi = xiangxi;
	}

	public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

	public String getShoujianren() {
		return shoujianren;
	}

	public void setShoujianren(String shoujianren) {
		this.shoujianren = shoujianren;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Dizhi() {
		id = 0L;
		kehuId = null;
		province = null;
		city = null;
		qu = null;
		xiangxi = null;
		dianhua = null;
		shoujianren = null;
		status = 0L;
	}

	public Dizhi(ResultSet rs) {
		id = 0L;
		kehuId = null;
		province = null;
		city = null;
		qu = null;
		xiangxi = null;
		dianhua = null;
		shoujianren = null;
		status = 0L;
		try {
			setCity(rs.getString("city"));
			setDianhua(rs.getString("dianhua"));
			setId(rs.getLong("id"));
			setKehuId(rs.getString("kehuId"));
			setProvince(rs.getString("province"));
			setQu(rs.getString("qu"));
			setShoujianren(rs.getString("shoujianren"));
			setStatus(rs.getLong("status"));
			setXiangxi(rs.getString("xiangxi"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}