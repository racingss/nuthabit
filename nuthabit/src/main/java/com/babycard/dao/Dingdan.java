package com.babycard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class Dingdan {

	private long id;
	private String kehuId;
	private long dizhiId;
	private long zongjine;
	private long status;
	public static final long STATUS_GOUWUCHE = 0L;
	public static final long STATUS_DAIZHIFU = 1L;
	public static final long STATUS_QUXIAO = -1L;
	public static final long STATUS_YIZHIFU = 2L;
	public static final long STATUS_YIFAHUO = 3L;
	public static final long STATUS_YIPINGJIA = 4L;
	private String beizhu;
	private String kuaidi;
	private String kuaididan;
	private String fankui;
	private String riqi;
	private String songhuori;
	private long shangpinshu;
	private long yunfei;
	private String dingdanId;
	private long tuijianyouhui = 0;
	private long zhifujine;
	private Collection dingdanXiangxi = null;
	private long tuangoubiaoji = 0;
	private long hongbao = 0;
	private String fuwu1 = null;
	private String fuwu2 = null;
	private String fuwu3 = null;
	private String lianxishijian = null;
	private long youhuima = 0;
	public String liquanId = null;

	public String getLiquanId() {
		return liquanId;
	}

	public void setLiquanId(String liquanId) {
		this.liquanId = liquanId;
	}

	public long getYouhuima() {
		return youhuima;
	}

	public void setYouhuima(long youhuima) {
		this.youhuima = youhuima;
	}

	public long getHongbao() {
		return hongbao;
	}

	public void setHongbao(long hongbao) {
		this.hongbao = hongbao;
	}

	public long getTuangoubiaoji() {
		return tuangoubiaoji;
	}

	public void setTuangoubiaoji(long tuangoubiaoji) {
		this.tuangoubiaoji = tuangoubiaoji;
	}

	public long getTuijianyouhui() {
		return tuijianyouhui;
	}

	public void setTuijianyouhui(long tuijianyouhui) {
		this.tuijianyouhui = tuijianyouhui;
	}

	public Collection getDingdanXiangxi() {
		return dingdanXiangxi;
	}

	public void setDingdanXiangxi(Collection dingdanXiangxi) {
		this.dingdanXiangxi = dingdanXiangxi;
	}

	public String getDingdanId() {
		return dingdanId;
	}

	public void setDingdanId(String dingdanId) {
		this.dingdanId = dingdanId;
	}

	public long getZhifujine() {
		return zhifujine;
	}

	public void setZhifujine(long zhifujine) {
		this.zhifujine = zhifujine;
	}

	public long getYunfei() {
		return yunfei;
	}

	public void setYunfei(long yunfei) {
		this.yunfei = yunfei;
	}

	public long getShangpinshu() {
		return shangpinshu;
	}

	public void setShangpinshu(long shangpinshu) {
		this.shangpinshu = shangpinshu;
	}

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

	public long getDizhiId() {
		return dizhiId;
	}

	public void setDizhiId(long dizhiId) {
		this.dizhiId = dizhiId;
	}

	public long getZongjine() {
		return zongjine;
	}

	public void setZongjine(long zongjine) {
		this.zongjine = zongjine;
	}

	public long getStatus() {
		return status;
	}

	public String getStatusJieshi() {
		if (this.status == this.STATUS_DAIZHIFU)
			return "待支付";
		if (this.status == this.STATUS_GOUWUCHE)
			return "待提交";
		if (this.status == this.STATUS_QUXIAO)
			return "已取消";
		if (this.status == this.STATUS_YIZHIFU)
			return "已支付";
		if (this.status == this.STATUS_YIFAHUO)
			return "已发货";
		return "状态出错";
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getFuwu1() {
		return fuwu1;
	}

	public void setFuwu1(String fuwu1) {
		this.fuwu1 = fuwu1;
	}

	public String getFuwu2() {
		return fuwu2;
	}

	public void setFuwu2(String fuwu2) {
		this.fuwu2 = fuwu2;
	}

	public String getFuwu3() {
		return fuwu3;
	}

	public void setFuwu3(String fuwu3) {
		this.fuwu3 = fuwu3;
	}

	public String getLianxishijian() {
		return lianxishijian;
	}

	public void setLianxishijian(String lianxishijian) {
		this.lianxishijian = lianxishijian;
	}

	public String getKuaidi() {
		return kuaidi;
	}

	public void setKuaidi(String kuaidi) {
		this.kuaidi = kuaidi;
	}

	public String getKuaididan() {
		return kuaididan;
	}

	public void setKuaididan(String kuaididan) {
		this.kuaididan = kuaididan;
	}

	public String getFankui() {
		return fankui;
	}

	public void setFankui(String fankui) {
		this.fankui = fankui;
	}

	public String getRiqi() {
		return riqi;
	}

	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}

	public Dingdan() {
		id = 0L;
		kehuId = "";
		dizhiId = 0L;
		zongjine = 0L;
		status = 0L;
		beizhu = null;
		kuaidi = null;
		kuaididan = null;
		fankui = null;
		riqi = null;
		songhuori = null;
		shangpinshu = 0L;
		yunfei = 0L;
		dingdanId = null;
		zhifujine = 0L;
	}

	public String getSonghuori() {
		return songhuori;
	}

	public void setSonghuori(String songhuori) {
		this.songhuori = songhuori;
	}
	
	

	@Override
	public String toString() {
		return "Dingdan [id=" + id + ", kehuId=" + kehuId + ", dizhiId=" + dizhiId + ", zongjine=" + zongjine
				+ ", status=" + status + ", beizhu=" + beizhu + ", kuaidi=" + kuaidi + ", kuaididan=" + kuaididan
				+ ", fankui=" + fankui + ", riqi=" + riqi + ", songhuori=" + songhuori + ", shangpinshu=" + shangpinshu
				+ ", yunfei=" + yunfei + ", dingdanId=" + dingdanId + ", tuijianyouhui=" + tuijianyouhui
				+ ", zhifujine=" + zhifujine + ", dingdanXiangxi=" + dingdanXiangxi + ", tuangoubiaoji=" + tuangoubiaoji
				+ ", hongbao=" + hongbao + ", fuwu1=" + fuwu1 + ", fuwu2=" + fuwu2 + ", fuwu3=" + fuwu3
				+ ", lianxishijian=" + lianxishijian + ", youhuima=" + youhuima + ", liquanId=" + liquanId + "]";
	}

	public Dingdan(ResultSet rs) {
		id = 0L;
		kehuId = "";
		dizhiId = 0L;
		zongjine = 0L;
		status = 0L;
		beizhu = null;
		kuaidi = null;
		kuaididan = null;
		fankui = null;
		riqi = null;
		songhuori = null;
		shangpinshu = 0L;
		yunfei = 0L;
		dingdanId = null;
		zhifujine = 0L;
		try {
			setBeizhu(rs.getString("beizhu"));
			setDizhiId(rs.getLong("dizhiId"));
			setFankui(rs.getString("fankui"));
			setId(rs.getLong("id"));
			setKehuId(rs.getString("kehuId"));
			setKuaidi(rs.getString("kuaidi"));
			setKuaididan(rs.getString("kuaididan"));
			setRiqi(rs.getString("riqi"));
			setStatus(rs.getLong("status"));
			setZongjine(rs.getLong("zongjine"));
			setShangpinshu(rs.getLong("shangpinshu"));
			setSonghuori(rs.getString("songhuori"));
			setYunfei(rs.getLong("yunfei"));
			setDingdanId(rs.getString("dingdanId"));
			setZhifujine(rs.getLong("zhifujine"));
			setTuijianyouhui(rs.getLong("tuijianyouhui"));
			this.setTuangoubiaoji(rs.getLong("tuangoubiaoji"));
			this.setHongbao(rs.getLong("hongbao"));
			this.setFuwu1(rs.getString("fuwu1"));
			this.setFuwu2(rs.getString("fuwu2"));
			this.setFuwu3(rs.getString("fuwu3"));
			this.setLianxishijian(rs.getString("lianxishijian"));
			this.setYouhuima(rs.getLong("youhuima"));
			this.setLiquanId(rs.getString("liquanId"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}