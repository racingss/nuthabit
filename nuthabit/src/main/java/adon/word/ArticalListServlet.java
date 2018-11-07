package adon.word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/yoyoword/articallist.html")
public class ArticalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticalListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			if (request.getParameter("delete") != null) {
				// 删除文章
				new ArticalDAO().delete(Long.parseLong(request.getParameter("id")));
				request.setAttribute("articalColl", new ArticalDAO().getAll());
				request.getRequestDispatcher("articallist.jsp").forward(request, response);
				return;
			}

			if (request.getParameter("line") != null) {
				// 显示例句
				request.setAttribute("word", request.getParameter("word"));
				request.setAttribute("articalLineWordColl", new ArticalDAO().getAllArticalLinesByWord(
						Long.parseLong(request.getParameter("id")), request.getParameter("word")));
				request.getRequestDispatcher("articalline.jsp").forward(request, response);
				return;
			}

			if (request.getParameter("analysis") != null) {
				// 分析文章
				request.setAttribute("artical",
						new ArticalDAO().getArtical(Long.parseLong(request.getParameter("id"))));

				bidui(request);

				request.getRequestDispatcher("articalanalysis.jsp").forward(request, response);
				return;
			}

			if (request.getParameter("deleteword") != null) {
				// 删除单词
				request.setAttribute("artical",
						new ArticalDAO().getArtical(Long.parseLong(request.getParameter("id"))));

				// new
				// ArticalDAO().delete(Long.parseLong(request.getParameter("id")),
				// request.getParameter("word"));
				new MywordDAO().add(new Myword("adon", request.getParameter("word")));

				request.setAttribute("articalWordColl",
						new ArticalDAO().getAllArticalWords(Long.parseLong(request.getParameter("id"))));
				request.getRequestDispatcher("articalanalysis.jsp").forward(request, response);
				return;
			}

			if (request.getParameter("clean") != null) {
				// 清除简单词汇
				Collection coll = new ArticalDAO().getAllArticalWords(Long.parseLong(request.getParameter("id")));
				Iterator it = coll.iterator();

				while (it.hasNext()) {
					ArticalWord aw = (ArticalWord) it.next();
					if (aw.getWord().length() <= 4) {
						new MywordDAO().add(new Myword("adon", aw.getWord()));
						// new
						// ArticalDAO().delete(Long.parseLong(request.getParameter("id")),
						// aw.getWord());
					}
				}
				request.setAttribute("artical",
						new ArticalDAO().getArtical(Long.parseLong(request.getParameter("id"))));

				bidui(request);

				request.getRequestDispatcher("articalanalysis.jsp").forward(request, response);
				return;
			}

			if (request.getParameter("id") != null) {
				// 具体文章
				request.setAttribute("artical",
						new ArticalDAO().getArtical(Long.parseLong(request.getParameter("id"))));
				request.getRequestDispatcher("artical.jsp").forward(request, response);
				return;
			} else {
				// 文章列表
				request.setAttribute("articalColl", new ArticalDAO().getAll());
				request.getRequestDispatcher("articallist.jsp").forward(request, response);
				return;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void bidui(HttpServletRequest request) {
		Collection articalWordColl = new ArticalDAO().getAllArticalWords(Long.parseLong(request.getParameter("id")));

		Collection myWordColl = new MywordDAO().getMywordCollByKehuId("adon");

		Collection distWord = new ArrayList();

		Iterator articalWordIt = articalWordColl.iterator();
		while (articalWordIt.hasNext()) {
			ArticalWord articalWord = (ArticalWord) articalWordIt.next();
			if (!isMyWord(articalWord.getWord(), myWordColl)) {
				distWord.add(articalWord);
			}
		}

		request.setAttribute("articalWordColl", distWord);
	}

	public boolean isMyWord(String word, Collection coll) {

		Iterator myWordIt = coll.iterator();
		while (myWordIt.hasNext()) {
			Myword my = (Myword) myWordIt.next();
			if (my.getWord().equalsIgnoreCase(word))
				return true;
		}

		return false;
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
