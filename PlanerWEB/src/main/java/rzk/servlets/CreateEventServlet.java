package rzk.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rzk.beans.PlannerBeanRemote;

@WebServlet("/CreateEventServlet")
public class CreateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateEventServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PlannerBeanRemote pbr = (PlannerBeanRemote) request.getSession().getAttribute("planerbean");
		String desc = request.getParameter("description");
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime ldtFromDate = LocalDateTime.parse(request.getParameter("fromDate"), dtf);
		LocalDateTime ldtToDate = LocalDateTime.parse(request.getParameter("toDate"), dtf);
		Date fromDate = java.sql.Timestamp.valueOf(ldtFromDate);
		Date toDate = java.sql.Timestamp.valueOf(ldtToDate);
		int eventType = Integer.parseInt(request.getParameter("eventTypeId"));
		if (pbr.createEvent(desc, fromDate, toDate, eventType)) {
			request.getRequestDispatcher("/event.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

	}

}
