package adon.word;

import java.sql.SQLException;
import java.util.Collection;
import java.util.*;

public class Wordlist {
	private String word = null;
	private String meaning = null;
	private String enPh = null;
	private String amPh = null;
	private String enPhMp3 = null;
	private String amPhMp3 = null;

	private String pos1 = null;
	private String acceptation1 = null;

	private String pos2 = null;
	private String acceptation2 = null;

	private String pos3 = null;
	private String acceptation3 = null;

	private String pos4 = null;
	private String acceptation4 = null;

	public String getPos1() {
		return pos1;
	}

	public void setPos1(String pos1) {
		this.pos1 = pos1;
	}

	public String getAcceptation1() {
		return acceptation1;
	}

	public void setAcceptation1(String acceptation1) {
		this.acceptation1 = acceptation1;
	}

	public String getPos2() {
		return pos2;
	}

	public void setPos2(String pos2) {
		this.pos2 = pos2;
	}

	public String getAcceptation2() {
		return acceptation2;
	}

	public void setAcceptation2(String acceptation2) {
		this.acceptation2 = acceptation2;
	}

	public String getPos3() {
		return pos3;
	}

	public void setPos3(String pos3) {
		this.pos3 = pos3;
	}

	public String getAcceptation3() {
		return acceptation3;
	}

	public void setAcceptation3(String acceptation3) {
		this.acceptation3 = acceptation3;
	}

	public String getPos4() {
		return pos4;
	}

	public void setPos4(String pos4) {
		this.pos4 = pos4;
	}

	public String getAcceptation4() {
		return acceptation4;
	}

	public void setAcceptation4(String acceptation4) {
		this.acceptation4 = acceptation4;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public String getEnPh() {
		return enPh;
	}

	public void setEnPh(String enPh) {
		this.enPh = enPh;
	}

	public String getAmPh() {
		return amPh;
	}

	public void setAmPh(String amPh) {
		this.amPh = amPh;
	}

	public String getEnPhMp3() {
		return enPhMp3;
	}

	public void setEnPhMp3(String enPhMp3) {
		this.enPhMp3 = enPhMp3;
	}

	public String getAmPhMp3() {
		return amPhMp3;
	}

	public void setAmPhMp3(String amPhMp3) {
		this.amPhMp3 = amPhMp3;
	}

	public static String replace(String temp) {
		temp = temp.replace(".", "");
		temp = temp.replace(",", "");
		temp = temp.replace(":", "");
		temp = temp.replace("`", "");
		temp = temp.replace("(", "");
		temp = temp.replace(")", "");
		return temp;
	}

	public String getMeaningFormat() {
		meaning = org.apache.commons.lang3.StringEscapeUtils.unescapeJava(meaning);

		StringTokenizer st = new StringTokenizer(meaning);
		StringBuffer sb = new StringBuffer("");
		while (st.hasMoreElements()) {
			String temp = st.nextToken();
			// if (exist(temp)) {
			if (temp.equalsIgnoreCase(word) || temp.indexOf(word) != -1)
				sb.append("<a style=\"text-decoration:none;color:red;\" href=\"/yoyoword/index.html?word=");
			else
				sb.append("<a style=\"text-decoration:none;color:grey;\" href=\"/yoyoword/index.html?word=");
			sb.append(replace(temp));
			sb.append("\">");
			sb.append(temp);
			sb.append("</a> ");
			// } else {
			// sb.append(temp);
			// sb.append("</a> ");
			// }
		}
		return sb.toString();
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public Wordlist() {

	}

	public Wordlist(java.sql.ResultSet rs) {
		try {
			this.setMeaning(rs.getString("meaning"));
			this.setWord(rs.getString("word"));
			this.setEnPh(rs.getString("enPh"));
			this.setAmPh(rs.getString("amPh"));
			this.setEnPhMp3(rs.getString("enPhMp3"));
			this.setAmPhMp3(rs.getString("amPhMp3"));
			this.setPos1(rs.getString("pos1"));
			this.setPos2(rs.getString("pos2"));
			this.setPos3(rs.getString("pos3"));
			this.setPos4(rs.getString("pos4"));
			this.setAcceptation1(rs.getString("acceptation1"));
			this.setAcceptation2(rs.getString("acceptation2"));
			this.setAcceptation3(rs.getString("acceptation3"));
			this.setAcceptation4(rs.getString("acceptation4"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Wordlist [word=" + word + ", meaning=" + meaning + ", enPh=" + enPh + ", amPh=" + amPh + ", enPhMp3="
				+ enPhMp3 + ", amPhMp3=" + amPhMp3 + ", pos1=" + pos1 + ", acceptation1=" + acceptation1 + ", pos2="
				+ pos2 + ", acceptation2=" + acceptation2 + ", pos3=" + pos3 + ", acceptation3=" + acceptation3
				+ ", pos4=" + pos4 + ", acceptation4=" + acceptation4 + "]";
	}

	public static void main(String[] args) {
		String a = "	Ma.don.na\n   Madonna\n  Catholics and other Christians sometimes call Mary, the mother of Jesus Christ, the Madonna.\n  N-PROPER: the N";
		System.out.println(a.replaceAll("\n", "<br/>"));
	}
}
