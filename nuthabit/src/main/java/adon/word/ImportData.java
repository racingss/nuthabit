package adon.word;

import java.io.*;
import java.util.*;

public class ImportData {

	public static void main(String[] args) {
		readTxt();
		print();
	}

	private static Collection totalWord = new ArrayList();

	private static void put(String temp) {
		Iterator it = totalWord.iterator();
		boolean flag = false;
		while (it.hasNext()) {
			String a[] = (String[]) it.next();
			if (a[0].equalsIgnoreCase(temp)) {
				a[1] = Integer.toString(Integer.parseInt(a[1]) + 1);
				flag = true;
			}
		}
		if (!flag) {
			totalWord.add(new String[] { temp, "1" });
		}
	}

	private static void print() {
		Iterator it = totalWord.iterator();
		while (it.hasNext()) {
			String a[] = (String[]) it.next();
			System.out.println(a[0] + " " + a[1]);
		}
	}

	private static void readTxt() {
		File file = new File("/Users/wangjunwu/news/1.txt");
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 0;
			// 一次读入一行，直到读入null为文件结束
			
			ArticalDAO dao = new ArticalDAO();
			Artical a = new Artical();
			a.setTitle(reader.readLine());
			a.setUrl(reader.readLine());
			a = dao.add(a);

			while ((tempString = reader.readLine()) != null) {
				if (tempString.trim().length() < 1)
					continue;
				line++;
				System.out.println("第" + line + "行" + tempString);
				a.setTotalNums(a.getTotalNums() + tempString.length());

				ArticalLine l = new ArticalLine();
				l.setArticalId(a.getId());
				l.setDetail(tempString);
				l.setLine(line);
				dao.addArticalLine(l);

				tempString = tempString + ".";

				char c[] = tempString.toCharArray();
				String temp = "";
				for (char cc : c) {
					if (cc >= 'a' && cc <= 'z' || cc >= 'A' && cc <= 'Z') {
						temp = temp + cc;
					} else {
						if (temp.length() > 1) {
							System.out.println(temp);
							put(temp);
							dao.addArticalLineWord(a.getId(), line, temp);
						}
						temp = "";
					}
				}
			}

			Iterator it = totalWord.iterator();
			while (it.hasNext()) {
				String word[] = (String[]) it.next();
				ArticalWord aw = new ArticalWord();
				aw.setArticalId(a.getId());
				aw.setWord(word[0]);
				aw.setNums(Long.parseLong(word[1]));
				dao.addArticalWords(aw);
			}

			a.setWordNums(totalWord.size());
			dao.update(a);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

	private static void readWord() {
		File file = new File("/Users/wangjunwu/百度云同步盘/suyuserver/WebContent/WEB-INF/classes/collins.txt");
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			Collection wCOll = new ArrayList();
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
				int a = tempString.indexOf("	");
				System.out.println("word:  " + tempString.substring(0, a));
				System.out.println("deteil:" + tempString.substring(a, tempString.length()));
				Wordlist w = new Wordlist();
				w.setWord(tempString.substring(0, a));
				w.setMeaning(tempString.substring(a, tempString.length()));
				wCOll.add(w);
			}
			new WordlistDAO().add(wCOll);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

}
