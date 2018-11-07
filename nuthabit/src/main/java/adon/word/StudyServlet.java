package adon.word;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudyServlet
 */
@WebServlet("/yoyoword/study.html")
public class StudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// TODO Auto-generated method stub
			String kehuId = "adon";

			int tempAddCount = 0;
			if (request.getParameter("tempAddCount") != null) {
				tempAddCount = Integer.parseInt(request.getParameter("tempAddCount"));
			} else if (request.getSession().getAttribute("tempAddCount") != null) {
				tempAddCount = Integer.parseInt(request.getSession().getAttribute("tempAddCount").toString());
			}

			MywordDAO dao = new MywordDAO();

			// if (request.getParameter("process") != null) {
			// String timeline = request.getParameter("timeline");
			// if (request.getSession().getAttribute("timeline") == null
			// ||
			// !timeline.equals(request.getSession().getAttribute("timeline").toString()))
			// {
			//
			// dao.addNewwordToMyList(kehuId, request.getParameter("word"));
			//
			// Myword m = dao.getMyword(kehuId, request.getParameter("word"));
			// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			// m.setReviewday(formatter.format(new Date()));
			// if (request.getParameter("process").equalsIgnoreCase("passed"))
			// dao.reviewPassed(m);
			// else
			// dao.reviewNotPass(m);
			// if (tempAddCount > 0) {
			// tempAddCount--;
			// }
			// new PlanDAO().updatePlan(kehuId);
			// request.getSession().setAttribute("timeline", timeline);
			// }
			// }

			int planCount = 0;
			Plan p = new PlanDAO().getPlan(kehuId);
			if (p != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String today = formatter.format(new Date());
				if (p.getCurrentDay() != null && p.getCurrentDay().equals(today)) {
					planCount = p.getPlanCount() - p.getTodayIndex();
				} else {
					planCount = p.getPlanCount();
				}
			}
			request.setAttribute("plan", p);

			request.setAttribute("planCount", planCount);
			request.setAttribute("tempAddCount", tempAddCount);
			request.getSession().setAttribute("tempAddCount", tempAddCount);

			System.out.println("planCount:" + planCount);
			System.out.println("tempAddCount:" + tempAddCount);

			if (planCount > 0) {
				request.setAttribute("newWord", dao.getNewword(p));
			} else if (tempAddCount > 0) {
				request.setAttribute("newWord", dao.getNewword(p));
			}

			request.getRequestDispatcher("study.jsp").forward(request, response);
		} catch (NumberFormatException e) {
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
