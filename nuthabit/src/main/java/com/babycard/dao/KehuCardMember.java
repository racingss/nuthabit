package com.babycard.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class KehuCardMember {
	private long id = 0;
	private String kehuId = null;
	private long memberLevel = 0; // 0月付；1年付；2终身
	public static final long MEMBER_LEVEL_MONTH = 0;
	public static final long MEMBER_LEVEL_YEAR = 1;
	public static final long MEMBER_LEVEL_LIFELONG = 2;
	private Timestamp createDate = null;
	private Timestamp closeDate = null;

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

	public long getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(long memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public void rebuild(Timestamp old) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(old.getTime());
		if (memberLevel == MEMBER_LEVEL_MONTH) {
			calendar.add(Calendar.MONTH, 1);// 把日期往后增加一天.正数往后推,负数往前推
		} else if (memberLevel == MEMBER_LEVEL_YEAR) {
			calendar.add(Calendar.YEAR, 1);// 把日期往后增加一天.正数往后推,负数往前推
		} else if (memberLevel == MEMBER_LEVEL_LIFELONG) {
			calendar.add(Calendar.YEAR, 10);// 把日期往后增加一天.正数往后推,负数往前推
		}

		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		closeDate = new Timestamp(date.getTime());
	}

	public Timestamp getCloseDate() {
		if (closeDate == null) {
			// 第一次初始化
			Date date = new Date();// 取时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);

			if (memberLevel == MEMBER_LEVEL_MONTH) {
				calendar.add(Calendar.MONTH, 1);// 把日期往后增加一天.正数往后推,负数往前推
			} else if (memberLevel == MEMBER_LEVEL_YEAR) {
				calendar.add(Calendar.YEAR, 1);// 把日期往后增加一天.正数往后推,负数往前推
			} else if (memberLevel == MEMBER_LEVEL_MONTH) {
				calendar.add(Calendar.YEAR, 99);// 把日期往后增加一天.正数往后推,负数往前推
			}

			date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
			closeDate = new Timestamp(date.getTime());
		}
		return closeDate;
	}

	public void setCloseDate(Timestamp closeDate) {
		this.closeDate = closeDate;
	}

	public KehuCardMember() {

	}

	@Override
	public String toString() {
		return "KehuCardMember [id=" + id + ", kehuId=" + kehuId + ", memberLevel=" + memberLevel + ", createDate="
				+ createDate + ", closeDate=" + closeDate + "]";
	}

	public KehuCardMember(ResultSet rs) {
		try {
			this.setCloseDate(rs.getTimestamp("closeDate"));
			this.setCreateDate(rs.getTimestamp("createDate"));
			this.setId(rs.getLong("id"));
			this.setKehuId(rs.getString("kehuId"));
			this.setMemberLevel(rs.getLong("memberLevel"));
			this.setMemberLevel(rs.getLong("memberLevel"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
