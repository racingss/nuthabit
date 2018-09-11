package com.babycard.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CardPic {
	private long picId = 0;
	private String cardpic = null;
	private String displayurl = null;
	private String weburl = null;
	private long cardId = 0;
	private long favCount = 0;
	private long mainPicId = 0;
	private long picIndex = 0;
	private long topP = 0;
	private long leftP = 0;
	private long widthP = 0;
	private long marginTop = 0;
	private long marginTop2 = 0;
	public long index = 0;
	private long linkPicId = 0;

	public long getLinkPicId() {
		return linkPicId;
	}

	public void setLinkPicId(long linkPicId) {
		this.linkPicId = linkPicId;
	}

	public long getMarginTop2() {
		return marginTop2;
	}

	public void setMarginTop2(long marginTop2) {
		this.marginTop2 = marginTop2;
	}

	public long getMarginTop() {
		return marginTop;
	}

	public void setMarginTop(long marginTop) {
		this.marginTop = marginTop;
	}

	public long getWidthP() {
		if (widthP == 0)
			return 18;
		return widthP;
	}

	public void setWidthP(long widthP) {
		this.widthP = widthP;
	}

	public long getTopP() {
		return topP;
	}

	public void setTopP(long topP) {
		this.topP = topP;
	}

	public long getLeftP() {
		return leftP;
	}

	public void setLeftP(long leftP) {
		this.leftP = leftP;
	}

	public long getPicIndex() {
		return picIndex;
	}

	public void setPicIndex(long picIndex) {
		this.picIndex = picIndex;
	}

	private String sound = null;

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String result = "0"; // 0 have not test, 1 test failed, 2 test succ

	public long getMainPicId() {
		return mainPicId;
	}

	public void setMainPicId(long mainPicId) {
		this.mainPicId = mainPicId;
	}

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

	public static Collection cardPicCOll = new ArrayList();

	public static Collection getPicCollByMainPicId(long mainPicId) {
		Iterator it = cardPicCOll.iterator();
		while (it.hasNext()) {
			Object obj[] = (Object[]) it.next();
			if (Long.parseLong(obj[0].toString()) == mainPicId)
				return (Collection) obj[1];
		}
		Collection temp = new CardPicDAO().getCardPicByMainCardId(mainPicId);
		cardPicCOll.add(new Object[] { mainPicId, temp });
		return temp;
	}

	public static void rebuildPicCollByPicId(long picId) {
		Iterator it = cardPicCOll.iterator();
		while (it.hasNext()) {
			Object obj[] = (Object[]) it.next();
			if (Long.parseLong(obj[0].toString()) == picId)
				obj[1] = new CardPicDAO().getCardPicByMainCardId(picId);
		}
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
			this.setPicId(rs.getLong("picId"));
			this.setCardId(rs.getLong("cardId"));
			this.setFavCount(rs.getLong("favCount"));
			this.setMainPicId(rs.getLong("mainPicId"));
			this.setPicIndex(rs.getLong("picIndex"));
			this.setLeftP(rs.getLong("leftP"));
			this.setTopP(rs.getLong("topP"));
			this.setWidthP(rs.getLong("widthP"));
			this.setMarginTop(rs.getLong("marginTop"));
			this.setMarginTop2(rs.getLong("marginTop2"));

			this.setLinkPicId(rs.getLong("linkPicId"));
			if (linkPicId == 0) {
				this.setCardpic(rs.getString("cardpic"));
				this.setDisplayurl(rs.getString("displayurl"));
				this.setWeburl(rs.getString("weburl"));
				this.setSound(rs.getString("sound"));
			} else {
				CardPic link = CardPicDAO.getStaticCardPic(linkPicId);
				this.setCardpic(link.getCardpic());
				this.setDisplayurl(link.getDisplayurl());
				this.setWeburl(link.getWeburl());
				this.setSound(link.getSound());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "CardPic [picId=" + picId + ", cardpic=" + cardpic + ", displayurl=" + displayurl + ", weburl=" + weburl
				+ ", cardId=" + cardId + ", favCount=" + favCount + ", mainPicId=" + mainPicId + ", picIndex="
				+ picIndex + ", topP=" + topP + ", leftP=" + leftP + ", widthP=" + widthP + ", marginTop=" + marginTop
				+ ", marginTop2=" + marginTop2 + ", index=" + index + ", linkPicId=" + linkPicId + ", sound=" + sound
				+ ", result=" + result + "]";
	}


	public static void main(String arg[]){
		System.out.println(new CardPicDAO().getCardPicBypicId(1717).toString());
		System.out.println(new CardPicDAO().getCardPicBypicId(1718).toString());
	}
	

}
