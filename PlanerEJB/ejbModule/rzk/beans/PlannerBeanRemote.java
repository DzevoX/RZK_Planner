package rzk.beans;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import model.Event;
import model.EventType;

@Remote
public interface PlannerBeanRemote {
	public String login(String email, String password);

	public boolean createEvent(String desc, Date fromDate, Date toDate, int eventTypeId);

	public List<Event> searchEvents(LocalDate d);

	public List<EventType> getTypes();
}
