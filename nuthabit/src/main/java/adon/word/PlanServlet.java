package adon.word;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlanServlet
 */
@WebServlet("/yoyoword/plan.html")
public class PlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlanServlet() {
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
			String kehuId = "adon";

			long bookId = Long.parseLong(request.getParameter("bookId"));

			int planCount = Integer.parseInt(request.getParameter("planCount"));

			PlanDAO dao = new PlanDAO();

			Plan p = new Plan();
			p.setBookId(bookId);
			p.setKehuId(kehuId);
			p.setPlanCount(planCount);

			if (request.getParameter("delete") != null) {
				dao.deletePlan(bookId, kehuId);
			} else if (request.getParameter("modify") != null) {
				dao.modifyPlan(p);
			} else {
				dao.addPlan(p);
			}
			response.sendRedirect("study.html");
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
