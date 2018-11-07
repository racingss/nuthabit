package com.babycard.dao;

import java.sql.ResultSet;

import java.sql.SQLException;

public class Chanpinpingjia {
	private long id = 0;
	private long chanpinId = 0;
	private String kehuId = null;
	private long dingdanId = 0;
	private long pingfen = 0;
	private String pingjia = null;
	private String tupian1 = null;
	private String tupian2 = null;
	private String tupian3 = null;
	private String tupian4 = null;
	private String tupian5 = null;
	private String tupian6 = null;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getChanpinId() {
		return chanpinId;
	}

	public void setChanpinId(long chanpinId) {
		this.chanpinId = chanpinId;
	}

	public String getKehuId() {
		return kehuId;
	}

	public void setKehuId(String kehuId) {
		this.kehuId = kehuId;
	}

	public long getDingdanId() {
		return dingdanId;
	}

	public void setDingdanId(long dingdanId) {
		this.dingdanId = dingdanId;
	}

	public long getPingfen() {
		return pingfen;
	}

	public void setPingfen(long pingfen) {
		this.pingfen = pingfen;
	}

	public String getPingjia() {
		return pingjia;
	}

	public void setPingjia(String pingjia) {
		this.pingjia = pingjia;
	}

	public String getTupian1() {
		return tupian1;
	}

	public void setTupian1(String tupian1) {
		this.tupian1 = tupian1;
	}

	public String getTupian2() {
		return tupian2;
	}

	public void setTupian2(String tupian2) {
		this.tupian2 = tupian2;
	}

	public String getTupian3() {
		return tupian3;
	}

	public void setTupian3(String tupian3) {
		this.tupian3 = tupian3;
	}

	public String getTupian4() {
		return tupian4;
	}

	public void setTupian4(String tupian4) {
		this.tupian4 = tupian4;
	}

	public String getTupian5() {
		return tupian5;
	}

	public void setTupian5(String tupian5) {
		this.tupian5 = tupian5;
	}

	public String getTupian6() {
		return tupian6;
	}

	public void setTupian6(String tupian6) {
		this.tupian6 = tupian6;
	}

	public Chanpinpingjia() {

	}

	@Override
	public String toString() {
		return "Chanpinpingjia [id=" + id + ", chanpinId=" + chanpinId + ", kehuId=" + kehuId + ", dingdanId="
				+ dingdanId + ", pingfen=" + pingfen + ", pingjia=" + pingjia + ", tupian1=" + tupian1 + ", tupian2="
				+ tupian2 + ", tupian3=" + tupian3 + ", tupian4=" + tupian4 + ", tupian5=" + tupian5 + ", tupian6="
				+ tupian6 + "]";
	}

	public Chanpinpingjia(ResultSet rs) {
		try {
			this.setChanpinId(rs.getLong("chanpinId"));
			this.setDingdanId(rs.getLong("dingdanId"));
			this.setId(rs.getLong("id"));
			this.setKehuId(rs.getString("kehuId"));
			this.setPingfen(rs.getLong("pingfen"));
			this.setPingjia(rs.getString("pingjia"));
			this.setTupian1(rs.getString("tupian1"));
			this.setTupian2(rs.getString("tupian2"));
			this.setTupian3(rs.getString("tupian3"));
			this.setTupian4(rs.getString("tupian4"));
			this.setTupian5(rs.getString("tupian5"));
			this.setTupian6(rs.getString("tupian6"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

