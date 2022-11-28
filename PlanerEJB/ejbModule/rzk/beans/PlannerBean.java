package rzk.beans;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.Event;
import model.EventType;
import model.User;

@Stateful
@LocalBean
public class PlannerBean implements PlannerBeanRemote {

	User user;
	@PersistenceContext
	EntityManager em;

	@EJB
	EventTypeBeanLocal etb;

	public String login(String email, String password) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.email LIKE :email AND u.password LIKE :pass");
		q.setParameter("email", email);
		q.setParameter("pass", password);
		List<User> users = q.getResultList();
		try {
			user = users.get(0);
			return users.get(0).getEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public PlannerBean() {
	}

	@Override
	public boolean createEvent(String desc, Date fromDate, Date toDate, int eventTypeId) {
		if (desc != null && fromDate != null && toDate != null) {
			Query q = em.createQuery("Select e from EventType e where e.id = :id");
			q.setParameter("id", eventTypeId);
			EventType et = (EventType) q.getSingleResult();
			Event newEvent = new Event();
			newEvent.setDescription(desc);
			newEvent.setFromDate(fromDate);
			newEvent.setToDate(toDate);
			newEvent.setEventType(et);
			newEvent.setUser(user);
			em.persist(newEvent);
			return true;
		}
		return false;
	}

	@Override
	public List<Event> searchEvents(LocalDate date) {
		TypedQuery<Event> query = em
				.createQuery("SELECT e FROM Event e WHERE e.user=:user AND DATE(e.fromDate)=DATE(:date)", Event.class);
		query.setParameter("date", date);
		query.setParameter("user", user);
		List<Event> events = query.getResultList();
		return events;
	}

	@Override
	public List<EventType> getTypes() {
		return etb.getTypes();
	}

	@PostConstruct
	public void connect() {
		System.out.println(this.getClass().getSimpleName() + ": created");
	}

	@PreDestroy
	private void destroy() {
		System.out.println(this.getClass().getSimpleName() + ": destroyed");
	}
}
