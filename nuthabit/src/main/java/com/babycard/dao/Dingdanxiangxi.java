package com.babycard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dingdanxiangxi {

	private long id;
	private long dingdanId;
	private long chanpinId;
	private long jine;
	private long jianshu;
	private long tuangoubiaoji;
	private String fuwu = null;
	private long pingjia = 0;
	private String mingcheng = null;

	public String getMingcheng() {
		return mingcheng;
	}

	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}

	public long getPingjia() {
		return pingjia;
	}

	public void setPingjia(long pingjia) {
		this.pingjia = pingjia;
	}

	public String getFuwu() {
		return fuwu;
	}

	public void setFuwu(String fuwu) {
		this.fuwu = fuwu;
	}

	public long getTuangoubiaoji() {
		return tuangoubiaoji;
	}

	public void setTuangoubiaoji(long tuangoubiaoji) {
		this.tuangoubiaoji = tuangoubiaoji;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDingdanId() {
		return dingdanId;
	}

	public void setDingdanId(long dingdanId) {
		this.dingdanId = dingdanId;
	}

	public long getChanpinId() {
		return chanpinId;
	}

	public void setChanpinId(long chanpinId) {
		this.chanpinId = chanpinId;
	}

	public long getJine() {
		return jine;
	}

	public void setJine(long jine) {
		this.jine = jine;
	}

	public long getJianshu() {
		return jianshu;
	}

	public void setJianshu(long jianshu) {
		this.jianshu = jianshu;
	}

	public Dingdanxiangxi() {
		id = 0L;
		dingdanId = 0L;
		chanpinId = 0L;
		jine = 0L;
		jianshu = 0L;
	}

	public Dingdanxiangxi(ResultSet rs) {
		id = 0L;
		dingdanId = 0L;
		chanpinId = 0L;
		jine = 0L;
		jianshu = 0L;
		try {
			setChanpinId(rs.getLong("chanpinId"));
			setDingdanId(rs.getLong("dingdanId"));
			setId(rs.getLong("Id"));
			setJianshu(rs.getLong("jianshu"));
			setJine(rs.getLong("jine"));
			this.setTuangoubiaoji(rs.getLong("tuangoubiaoji"));
			this.setPingjia(rs.getLong("pingjia"));
			this.setMingcheng(rs.getString("mingcheng"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
