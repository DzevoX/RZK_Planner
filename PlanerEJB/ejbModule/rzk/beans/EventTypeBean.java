package rzk.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.EventType;

@Singleton
@LocalBean
public class EventTypeBean implements EventTypeBeanLocal {

	@PersistenceContext
	private EntityManager em;
	private List<EventType> types;

	public EventTypeBean() {
	}

	@PostConstruct
	private void create() {
		TypedQuery<EventType> query = em.createNamedQuery("EventType.findAll", EventType.class);
		types = query.getResultList();
		System.out.println(this.getClass().getSimpleName() + ": created");
	}

	@PreDestroy
	private void destroy() {
		System.out.println(this.getClass().getSimpleName() + ": destroyed");
	}

	@Override
	public List<EventType> getTypes() {
		return types;
	}
}
