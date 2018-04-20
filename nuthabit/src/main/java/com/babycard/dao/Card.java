package com.babycard.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Card {
	private long cardId = 0;
	private String meaning = "";
	private long picCount = 0;
	private long favCount = 0;
	private long cardIndex = 0;

	public long getCardIndex() {
		return cardIndex;
	}

	public void setCardIndex(long cardIndex) {
		this.cardIndex = cardIndex;
	}

	public Collection cardMeaningColl = null;
	public Collection cardSoundColl = null;

	public String getMeaning(long languageId) {
		Iterator it = cardMeaningColl.iterator();
		while (it.hasNext()) {
			CardMeaning cm = (CardMeaning) it.next();
			if (cm.getLanguageId() == languageId)
				return cm.getMeaning();
		}
		return "";
	}

	public long getFavCount() {
		return favCount;
	}

	public void setFavCount(long favCount) {
		this.favCount = favCount;
	}

	public long getPicCount() {
		return picCount;
	}

	public void setPicCount(long picCount) {
		this.picCount = picCount;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	private static Collection cardPicColl = null;

	public String getImg() {
		if (cardPicColl == null)
			cardPicColl = new CardDAO().getCardPicCollection();
		Iterator it = cardPicColl.iterator();
		while (it.hasNext()) {
			CardPic cp = (CardPic) it.next();
			if (cp.getCardId() == cardId)
				return cp.getImgurl();
		}

		cardPicColl = new CardDAO().getCardPicCollection();
		it = cardPicColl.iterator();
		while (it.hasNext()) {
			CardPic cp = (CardPic) it.next();
			if (cp.getCardId() == cardId)
				return cp.getImgurl();
		}

		return "没找到";
	}

	public Card() {

	}

	public Card(java.sql.ResultSet rs) {
		try {
			this.setCardId(rs.getLong("cardId"));
			this.setMeaning(rs.getString("meaning"));
			this.setPicCount(rs.getLong("picCount"));
			this.setFavCount(rs.getLong("favCount"));
			this.setCardIndex(rs.getLong("cardIndex"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", meaning=" + meaning + ", picCount=" + picCount + ", favCount=" + favCount
				+ ", cardIndex=" + cardIndex + "]";
	}

}
