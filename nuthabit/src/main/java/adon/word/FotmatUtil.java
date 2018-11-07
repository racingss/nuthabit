package adon.word;

import java.util.StringTokenizer;

public class FotmatUtil {
	public static String getFormat(String meaning) {
		meaning = org.apache.commons.lang3.StringEscapeUtils.unescapeJava(meaning);

		StringTokenizer st = new StringTokenizer(meaning);
		StringBuffer sb = new StringBuffer("");
		while (st.hasMoreElements()) {
			String temp = st.nextToken();
			// if (exist(temp)) {
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
	
	public static String getFormatPage2(String meaning) {
		meaning = org.apache.commons.lang3.StringEscapeUtils.unescapeJava(meaning);

		StringTokenizer st = new StringTokenizer(meaning);
		StringBuffer sb = new StringBuffer("");
		while (st.hasMoreElements()) {
			String temp = st.nextToken();
			// if (exist(temp)) {
			sb.append("<a class=\"wordpress\"  style=\"text-decoration:none;color:black;\" href=\"#pagetwo\" data-rel=\"dialog\" word=\"");
			sb.append(replace(temp));
			sb.append("\">");
			sb.append(temp);
			sb.append("</a> ");
		}
		return sb.toString();
	}

	public static String getFormat(String meaning, String word) {
		meaning = org.apache.commons.lang3.StringEscapeUtils.unescapeJava(meaning);

		StringTokenizer st = new StringTokenizer(meaning);
		StringBuffer sb = new StringBuffer("");
		while (st.hasMoreElements()) {
			String temp = st.nextToken();
			// if (exist(temp)) {
			if (temp.equalsIgnoreCase(word))
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

	public static String getWordFuzzy(String word) {
		if (word.endsWith("s")) {
			return word.substring(0, word.lastIndexOf("s"));
		} else if (word.endsWith("ed")) {
			return word.substring(0, word.lastIndexOf("ed"));
		} else if (word.endsWith("ing")) {
			return word.substring(0, word.lastIndexOf("ing"));
		} else if (word.endsWith("ly")) {
			return word.substring(0, word.lastIndexOf("ly"));
		} else if (word.endsWith("'s")) {
			return word.substring(0, word.lastIndexOf("'s"));
		} else if (word.endsWith("s")) {
			return word.substring(0, word.lastIndexOf("s"));
		}
		return word;
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

}
