package adon.word;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudyServlet
 */
@WebServlet("/yoyoword/test.html")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			// TODO Auto-generated method stub
			String kehuId = "adon";

			MywordDAO dao = new MywordDAO();
			Collection wordColl = null;

			if (request.getParameter("process") != null) {

				long kId = Long.parseLong(request.getSession().getAttribute("kId").toString());
				long bId = Long.parseLong(request.getSession().getAttribute("bId").toString());
				dao.addNewwordToMyList(kehuId, request.getParameter("word"), kId, bId);

				Myword m = dao.getMyword(kehuId, request.getParameter("word"));
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				m.setReviewday(formatter.format(new Date()));
				if (request.getParameter("process").equalsIgnoreCase("passed"))
					dao.reviewPassed(m, false);
				else if (request.getParameter("process").equalsIgnoreCase("nopass"))
					dao.reviewNotPass(m);
				else
					dao.reviewPassed(m, true);
				wordColl = (Collection) request.getSession().getAttribute("wordColl");
				wordColl.remove(request.getParameter("word"));
			}

			

			if (request.getParameter("kId") != null) {

				long kId = Long.parseLong(request.getParameter("kId"));
				long bookId = Long.parseLong(request.getParameter("bookId"));

				wordColl = dao.getWordByke(kId, bookId);
				request.getSession().setAttribute("wordColl", wordColl);
				request.getSession().setAttribute("kId", kId);
				request.getSession().setAttribute("bId", bookId);
			} else {
				if (wordColl.size() == 0) {
					KeHistory k = new KeHistory();
					k.setKehuId(kehuId);
					k.setKeId(Long.parseLong(request.getSession().getAttribute("kId").toString()));
					new KeHistoryDAO().add(k);
				}
			}

			request.setAttribute("wordColl", wordColl);

			request.getRequestDispatcher("test.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
