package adon.word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/yoyoword/articaladd.html")
public class ArticalAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticalAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Collection totalWord = new ArrayList();

	private void put(String temp) {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String addinfo = request.getParameter("addinfo");

		if (addinfo == null) {
			request.getRequestDispatcher("articaladd.jsp").forward(request, response);
			return;
		}

		try {
			StringTokenizer st = new StringTokenizer(addinfo, "\n");
			String tempString = null;

			int line = 0;
			// 一次读入一行，直到读入null为文件结束

			ArticalDAO dao = new ArticalDAO();
			Artical a = new Artical();
			if (st.hasMoreElements()) {
				a.setTitle(st.nextToken());
			}
			if (st.hasMoreElements()) {
				a.setUrl(st.nextToken());
			}
			a = dao.add(a);

			while (st.hasMoreElements()) {
				tempString = st.nextToken();

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
			request.setAttribute("result", "添加成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result", "添加失败:" + e.toString());
		}

		request.getRequestDispatcher("articaladd.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}