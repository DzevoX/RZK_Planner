package rzk.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Event;
import rzk.beans.PlannerBeanRemote;

@WebServlet("/ShowEventServlet")
public class ShowEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowEventServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PlannerBeanRemote plannerBean = (PlannerBeanRemote) request.getSession().getAttribute("planerbean");
		String date = request.getParameter("eventsDate");
		LocalDate d = LocalDate.parse(date);
		List<Event> events = plannerBean.searchEvents(d);
		request.setAttribute("events", events);
		request.getRequestDispatcher("event.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
