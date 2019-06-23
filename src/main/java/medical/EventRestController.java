package medical;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import medical.model.Event;
import medical.service.EventService;
import medical.util.MedicalServiceErrorType;

@RestController
@RequestMapping("/medicalService/api")
public class EventRestController {
    public static final Logger logger = LoggerFactory.getLogger(EventRestController.class);

    @Autowired
    EventService eventService; //Service which will do all data retrieval/manipulation work

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public ResponseEntity<List<Event>> listAllEvents() {
        List<Event> events = eventService.findAllEvents();
        if (events.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    // -------------------Create a Event-------------------------------------------

    @RequestMapping(value = "/event/", method = RequestMethod.POST)
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        logger.info("Creating Event : {}", event);
        System.out.println("Event create ok");
        Event newEvent = eventService.createEvent(event);

        return new ResponseEntity<Event>(newEvent, HttpStatus.OK);
    }


    // ------------------- Update a Event ------------------------------------------------

    @RequestMapping(value = "/event/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateEvent(@PathVariable("id") String id, @RequestBody Event event) {
        logger.info("Updating Event with id {}", id);

        Event currentEvent = eventService.findById(id);

        eventService.updateEvent(currentEvent);
        return new ResponseEntity<Event>(currentEvent, HttpStatus.OK);
    }

    // ------------------- Delete a Event-----------------------------------------

    @RequestMapping(value = "/event/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEvent(@PathVariable("id") String id) {
        logger.info("Fetching & Deleting Event with id {}", id);

        Event event = eventService.findById(id);
        if (event == null) {
            logger.error("Unable to delete. Event with id {} not found.", id);
            return new ResponseEntity(new MedicalServiceErrorType("Unable to delete Event.", "Event with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        if (eventService.deleteEventById(id) == true) {
            return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }
    }

}
