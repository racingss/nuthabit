package com.babycard.dao;

import java.sql.SQLException;

public class CardPic {
	private long picId = 0;
	private String cardpic = null;
	private String displayurl = null;
	private String weburl = null;
	private long cardId = 0;
	private long favCount = 0;

	public String getDisplayurl() {
		return displayurl;
	}

	public void setDisplayurl(String displayurl) {
		this.displayurl = displayurl;
	}

	public String getWeburl() {
		return weburl;
	}

	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

	public long getFavCount() {
		return favCount;
	}

	public void setFavCount(long favCount) {
		this.favCount = favCount;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public long getPicId() {
		return picId;
	}

	public void setPicId(long picId) {
		this.picId = picId;
	}

	public String getCardpic() {
		return cardpic;
	}

	public String getImgurl() {
		if (cardpic != null)
			return "/" + cardpic;
		return weburl;
	}

	public void setCardpic(String cardpic) {
		this.cardpic = cardpic;
	}

	public CardPic() {

	}

	public CardPic(java.sql.ResultSet rs) {
		try {
			this.setCardpic(rs.getString("cardpic"));
			this.setPicId(rs.getLong("picId"));
			this.setCardId(rs.getLong("cardId"));
			this.setFavCount(rs.getLong("favCount"));
			this.setDisplayurl(rs.getString("displayurl"));
			this.setWeburl(rs.getString("weburl"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "CardPic [picId=" + picId + ", cardpic=" + cardpic + ", displayurl=" + displayurl + ", weburl=" + weburl
				+ ", cardId=" + cardId + ", favCount=" + favCount + "]";
	}

}
