package com.babycard.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CardWord {
	private long wordId = 0;
	private String meaning = null;
	private String sound = null;
	private long cardId = 0;
	private long topP = 0;
	private long leftP = 0;
	private long widthP = 0;
	private double sizeP = 0;
	private long soundFlag = -1;

	public long getSoundFlag() {
		return soundFlag;
	}

	public void setSoundFlag(long soundFlag) {
		this.soundFlag = soundFlag;
	}

	public double getSizeP() {
		if (sizeP == 0)
			return 3;
		return sizeP;
	}

	public void setSizeP(double sizeP) {
		this.sizeP = sizeP;
	}

	public long getWordId() {
		return wordId;
	}

	public void setWordId(long wordId) {
		this.wordId = wordId;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public long getTopP() {
		if (topP == 0)
			return 10;
		return topP;
	}

	public void setTopP(long topP) {
		this.topP = topP;
	}

	public long getLeftP() {
		if (leftP == 0)
			return 20;
		return leftP;
	}

	public void setLeftP(long leftP) {
		this.leftP = leftP;
	}

	public long getWidthP() {
		if (widthP == 0)
			return 20;
		return widthP;
	}

	public void setWidthP(long widthP) {
		this.widthP = widthP;
	}

	public CardWord() {

	}

	public CardWord(java.sql.ResultSet rs) {
		try {
			this.setCardId(rs.getLong("cardId"));
			this.setLeftP(rs.getLong("leftP"));
			this.setTopP(rs.getLong("topP"));
			this.setWidthP(rs.getLong("widthP"));
			this.setWordId(rs.getLong("wordId"));
			this.setMeaning(rs.getString("meaning"));
			this.setSound(rs.getString("sound"));
			this.setSizeP(rs.getDouble("sizeP"));
			this.setSoundFlag(rs.getLong("soundFlag"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "CardWord [wordId=" + wordId + ", meaning=" + meaning + ", sound=" + sound + ", cardId=" + cardId
				+ ", topP=" + topP + ", leftP=" + leftP + ", widthP=" + widthP + ", sizeP=" + sizeP + ", soundFlag="
				+ soundFlag + "]";
	}

	public static void main(String arg[]) {
	}

}
