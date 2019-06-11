package medical;

import medical.model.Pacient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import medical.service.PacientService;
import medical.util.MedicalServiceErrorType;

import java.util.List;

/**
 * Spring RestController annotation is used to create RESTful web services using Spring MVC.
 * Spring RestController takes care of mapping request data to the defined request handler method.  *
 * Every request handling method of the controller class automatically serializes return objects into HttpResponse
 */
@RestController
@RequestMapping("/medicalService/api")
public class PacientsRestController {

    public static final Logger logger = LoggerFactory.getLogger(PacientsRestController.class);

    @Autowired
    PacientService pacientService; //Service which will do all data retrieval/manipulation work

    @RequestMapping(value = "/pacient", method = RequestMethod.GET)
    public ResponseEntity<List<Pacient>> listAllPacients() {
        List<Pacient> pacients = pacientService.findAllPacients();
        if (pacients.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Pacient>>(pacients, HttpStatus.OK);
    }

    // -------------------Create a Pacient-------------------------------------------

    @RequestMapping(value = "/pacient/", method = RequestMethod.POST)
    public ResponseEntity<?> createPacient(@RequestBody Pacient pacient) {
        logger.info("Creating Pacient : {}", pacient);

        Pacient newPacient = pacientService.createPacient(pacient);

        return new ResponseEntity<Pacient>(newPacient, HttpStatus.OK);
    }


    // ------------------- Update a Pacient ------------------------------------------------

    @RequestMapping(value = "/pacient/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updatePacient(@PathVariable("id") String id, @RequestBody Pacient pacient) {
        logger.info("Updating Pacient with id {}", id);
        pacientService.updatePacient(pacient);
        return new ResponseEntity<Pacient>(pacient, HttpStatus.OK);
    }
}