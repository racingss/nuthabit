package com.babycard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.StringTokenizer;

public class Chanpin {

	private long id;
	private String mingcheng;
	private String zhutu;
	private long jiage;
	private long yuanjiage;
	private long leibieId;
	private String miaoshu;
	private long kucun;
	private long status;
	private long tuijianId;
	private long shunxu;
	private long baoyou;
	private String guige;
	private String shangshi;
	private String baozhuang;
	private String chandi;
	private String fahuo;
	private String pinpai;
	private long tuangoujia = 0;
	private long tuangoubiaoji = 0;
	private long tuangourenshu = 0;
	private long chengbenjia = 0;
	private String fahuoxianzhi = null;
	private String jiancheng = null;
	private String jianshu = null;
	private String fuwu = null;
	private long miaoshaFlag = 0;
	private String miaoshaShijian = null;
	private long kId = 0;
	private String headurl = null;
	public Collection futuColl = new ArrayList();

	public long getkId() {
		return kId;
	}

	public void setkId(long kId) {
		this.kId = kId;
	}

	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	public String getMiaoshaShijian() {
		return miaoshaShijian;
	}

	public void setMiaoshaShijian(String miaoshaShijian) {
		this.miaoshaShijian = miaoshaShijian;
	}

	public long getMiaoshaFlag() {
		return miaoshaFlag;
	}

	public void setMiaoshaFlag(long miaoshaFlag) {
		this.miaoshaFlag = miaoshaFlag;
	}

	private Collection fuwuColl = null;

	public Collection getFuwu() {
		if (fuwu == null)
			return null;
		fuwuColl = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(fuwu, "|");

		while (tokenizer.hasMoreTokens()) {
			fuwuColl.add(tokenizer.nextToken());
		}
		return fuwuColl;
	}

	public String getJiancheng() {
		return jiancheng;
	}

	public void setJiancheng(String jiancheng) {
		this.jiancheng = jiancheng;
	}

	public String getJianshu() {
		return jianshu;
	}

	public void setJianshu(String jianshu) {
		this.jianshu = jianshu;
	}

	public String getFahuoxianzhi() {
		if (fahuoxianzhi == null || fahuoxianzhi.trim().length() < 1)
			return null;
		return fahuoxianzhi;
	}

	public Calendar getFahuoxianzhiCal() {
		if (fahuoxianzhi == null || fahuoxianzhi.trim().length() < 1)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date date = sdf.parse(fahuoxianzhi);

			Calendar calendar = Calendar.getInstance();

			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public long getMiaoshaTime() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

		try {
			Date date = sdf.parse(miaoshaShijian);

			Calendar calendar = Calendar.getInstance();

			calendar.setTime(date);
			return calendar.getTimeInMillis();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public void setFahuoxianzhi(String fahuoxianzhi) {
		this.fahuoxianzhi = fahuoxianzhi;
	}

	public long getChengbenjia() {
		return chengbenjia;
	}

	public void setChengbenjia(long chengbenjia) {
		this.chengbenjia = chengbenjia;
	}

	public final static long STATUS_NORMAL = 0;
	public final static long STATUS_XIAJIA = -1;

	public long getTuangourenshu() {
		return tuangourenshu;
	}

	public void setTuangourenshu(long tuangourenshu) {
		this.tuangourenshu = tuangourenshu;
	}

	public long getTuangoujia() {
		return tuangoujia;
	}

	public void setTuangoujia(long tuangoujia) {
		this.tuangoujia = tuangoujia;
	}

	public long getTuangoubiaoji() {
		return tuangoubiaoji;
	}

	public void setTuangoubiaoji(long tuangoubiaoji) {
		this.tuangoubiaoji = tuangoubiaoji;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public String getShangshi() {
		return shangshi;
	}

	public void setShangshi(String shangshi) {
		this.shangshi = shangshi;
	}

	public String getBaozhuang() {
		return baozhuang;
	}

	public void setBaozhuang(String baozhuang) {
		this.baozhuang = baozhuang;
	}

	public String getChandi() {
		return chandi;
	}

	public void setChandi(String chandi) {
		this.chandi = chandi;
	}

	public String getFahuo() {
		return fahuo;
	}

	public void setFahuo(String fahuo) {
		this.fahuo = fahuo;
	}

	public String getPinpai() {
		return pinpai;
	}

	public void setPinpai(String pinpai) {
		this.pinpai = pinpai;
	}

	public long getBaoyou() {
		return baoyou;
	}

	public void setBaoyou(long baoyou) {
		this.baoyou = baoyou;
	}

	public long getTuijianId() {
		return tuijianId;
	}

	public void setTuijianId(long tuijianId) {
		this.tuijianId = tuijianId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMingcheng() {
		return jiancheng + " " + jianshu;
	}

	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}

	public String getZhutu() {
		return zhutu;
	}

	public void setZhutu(String zhutu) {
		this.zhutu = zhutu;
	}

	public long getJiage() {
		return jiage;
	}

	public void setJiage(long jiage) {
		this.jiage = jiage;
	}

	public long getYuanjiage() {
		return yuanjiage;
	}

	public long getShunxu() {
		return shunxu;
	}

	public void setShunxu(long shunxu) {
		this.shunxu = shunxu;
	}

	public void setYuanjiage(long yuanjiage) {
		this.yuanjiage = yuanjiage;
	}

	public long getLeibieId() {
		return leibieId;
	}

	public void setLeibieId(long leibieId) {
		this.leibieId = leibieId;
	}

	public String getMiaoshu() {
		return miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

	public long getKucun() {
		return kucun;
	}

	public void setKucun(long kucun) {
		this.kucun = kucun;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Chanpin() {
		id = 0L;
		mingcheng = null;
		zhutu = null;
		jiage = 0L;
		yuanjiage = 0L;
		leibieId = 0L;
		miaoshu = null;
		kucun = 0L;
		status = 0L;
		tuijianId = 0L;
		shunxu = 0L;
		baoyou = 0L;
		guige = "";
		shangshi = "";
		baozhuang = "";
		chandi = "";
		fahuo = "";
		pinpai = "";
	}

	@Override
	public String toString() {
		return "Chanpin [id=" + id + ", mingcheng=" + mingcheng + ", zhutu=" + zhutu + ", jiage=" + jiage
				+ ", yuanjiage=" + yuanjiage + ", leibieId=" + leibieId + ", miaoshu=" + miaoshu + ", kucun=" + kucun
				+ ", status=" + status + ", tuijianId=" + tuijianId + ", shunxu=" + shunxu + ", baoyou=" + baoyou
				+ ", guige=" + guige + ", shangshi=" + shangshi + ", baozhuang=" + baozhuang + ", chandi=" + chandi
				+ ", fahuo=" + fahuo + ", pinpai=" + pinpai + ", tuangoujia=" + tuangoujia + ", tuangoubiaoji="
				+ tuangoubiaoji + ", tuangourenshu=" + tuangourenshu + ", chengbenjia=" + chengbenjia
				+ ", fahuoxianzhi=" + fahuoxianzhi + ", jiancheng=" + jiancheng + ", jianshu=" + jianshu + ", fuwu="
				+ fuwu + ", miaoshaFlag=" + miaoshaFlag + ", miaoshaShijian=" + miaoshaShijian + ", kId=" + kId
				+ ", headurl=" + headurl + "]";
	}

	public Chanpin(ResultSet rs) {
		id = 0L;
		mingcheng = null;
		zhutu = null;
		jiage = 0L;
		yuanjiage = 0L;
		leibieId = 0L;
		miaoshu = null;
		kucun = 0L;
		status = 0L;
		tuijianId = 0L;
		shunxu = 0L;
		baoyou = 0L;
		guige = "";
		shangshi = "";
		baozhuang = "";
		chandi = "";
		fahuo = "";
		pinpai = "";
		try {
			setId(rs.getLong("id"));
			setJiage(rs.getLong("jiage"));
			setKucun(rs.getLong("kucun"));
			setLeibieId(rs.getLong("leibieId"));
			setMiaoshu(rs.getString("miaoshu"));
			setMingcheng(rs.getString("mingcheng"));
			setStatus(rs.getLong("status"));
			setYuanjiage(rs.getLong("yuanjiage"));
			setZhutu(rs.getString("zhutu"));
			setTuijianId(rs.getLong("tuijianId"));
			setShunxu(rs.getLong("shunxu"));
			setBaoyou(rs.getLong("baoyou"));
			setGuige(rs.getString("guige"));
			setPinpai(rs.getString("pinpai"));
			setChandi(rs.getString("chandi"));
			setBaozhuang(rs.getString("baozhuang"));
			setShangshi(rs.getString("shangshi"));
			this.setTuangoubiaoji(rs.getLong("tuangoubiaoji"));
			this.setTuangoujia(rs.getLong("tuangoujia"));
			setFahuo(rs.getString("fahuo"));
			this.setTuangourenshu(rs.getLong("tuangourenshu"));
			this.setChengbenjia(rs.getLong("chengbenjia"));
			this.setFahuoxianzhi(rs.getString("fahuoxianzhi"));
			this.setJiancheng(rs.getString("jiancheng"));
			this.setJianshu(rs.getString("jianshu"));
			this.fuwu = rs.getString("fuwu");
			this.miaoshaFlag = rs.getLong("miaoshaFlag");
			this.miaoshaShijian = rs.getString("miaoshaShijian");
			this.setkId(rs.getLong("kId"));
			this.setHeadurl(rs.getString("headurl"));
			// System.out.println(this.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}