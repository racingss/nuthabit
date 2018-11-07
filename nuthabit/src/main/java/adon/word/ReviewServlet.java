package adon.word;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/yoyoword/review.html")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewServlet() {
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

			if (request.getParameter("clean") != null && request.getParameter("clean").equalsIgnoreCase("t")) {
				dao.deleteAll(kehuId);
			}

			if (request.getParameter("review") != null) {
				Myword m = dao.getMyword(kehuId, request.getParameter("word"));
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				m.setReviewday(formatter.format(new Date()));
				if (request.getParameter("review").equalsIgnoreCase("passed"))
					dao.reviewPassed(m, false);
				else if (request.getParameter("review").equalsIgnoreCase("nopass"))
					dao.reviewNotPass(m);
				else
					dao.reviewPassed(m, true);
			}

			Map reviewMap = null;

//			if (request.getParameter("bId") != null) {
//
//				long bId = Long.parseLong(request.getParameter("bId"));
//
//				reviewMap = dao.getReview(kehuId, bId);
//				request.getSession().setAttribute("reviewMap", reviewMap);
//				request.getSession().setAttribute("bId", bId);
//			} else {
//				reviewMap = (HashMap) request.getSession().getAttribute("reviewMap");
//				if (request.getParameter("word") != null)
//					reviewMap.remove(request.getParameter("word"));
//			}
//			request.setAttribute("reviewMap", reviewMap);
//
			request.setAttribute("reviewNums", dao.getReviewNums(kehuId));

			Myword m = null;

			int newCount = 0;
			if (request.getSession().getAttribute("newCount") != null)
				newCount = Integer.parseInt(request.getSession().getAttribute("newCount").toString());
			newCount++;

			if (newCount > 8) {
				m = dao.getReview(kehuId, 1);
				if (m == null)
					m = dao.getReview(kehuId, 0);
				request.setAttribute("reviewWord", m);
			} else {
				m = dao.getReview(kehuId, 0);
				if (m == null)
					m = dao.getReview(kehuId, 1);

				request.setAttribute("reviewWord", m);
			}
			if (newCount >= 12) {
				newCount = 0;
			}

			request.getSession().setAttribute("newCount", newCount);

			request.getRequestDispatcher("review.jsp").forward(request, response);
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
