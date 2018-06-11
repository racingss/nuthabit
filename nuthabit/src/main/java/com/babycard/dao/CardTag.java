package com.babycard.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class CardTag {
	private long tagId = 0;
	private String tag = null;
	private long kehuId = 0;
	private String headpng = null;

	public String getHeadpng() {
		if (headpng != null)
			return headpng;
		return "default.png";
	}

	public void setHeadpng(String headpng) {
		this.headpng = headpng;
	}

	public long getKehuId() {
		return kehuId;
	}

	public void setKehuId(long kehuId) {
		this.kehuId = kehuId;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public static Collection coll = null;

	public static CardTag getCartTagByTagId(long tagId) {
		if (coll == null)
			coll = new TagDAO().getAllTagList();
		Iterator it = coll.iterator();
		while (it.hasNext()) {
			CardTag ct = (CardTag) it.next();
			if (ct.getTagId() == tagId)
				return ct;
		}

		coll = new TagDAO().getAllTagList();
		it = coll.iterator();
		while (it.hasNext()) {
			CardTag ct = (CardTag) it.next();
			if (ct.getTagId() == tagId)
				return ct;
		}
		return null;

	}

	public CardTag() {

	}

	public CardTag(java.sql.ResultSet rs) {
		try {
			this.setTag(rs.getString("tag"));
			this.setTagId(rs.getLong("tagId"));
			this.setKehuId(rs.getLong("kehuId"));
			this.setHeadpng(rs.getString("headpng"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "CardTag [tagId=" + tagId + ", tag=" + tag + ", kehuId=" + kehuId + ", headpng=" + headpng + "]";
	}

}
