package medical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

import medical.dao.EventDao;
import medical.model.Event;

@Service
public class EventService {
    private static List<Event> events;

    @Autowired
    private EventDao eventDao;

    public List<Event> findAllEvents() {
        events = eventDao.getAllEvents();
        return events;
    }

    public Event createEvent(Event event) {
        eventDao.create(event);
        return event;
    }

    public void updateEvent(Event event) {
        eventDao.update(event);
    }

    public Event findById(String id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }

    public boolean deleteEventById(String id) {
        /*for (Iterator<Event> iterator = events.iterator(); iterator.hasNext(); ) {
            Event Event = iterator.next();
            if (Event.getId().equals(id)) {
                iterator.remove();
            }
        }*/

        try{
            eventDao.delete(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean isEventExist(Event Event) {
        return findById(Event.getId()) != null;
    }

}
