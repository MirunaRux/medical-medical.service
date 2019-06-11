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

import medical.model.Request;
import medical.service.RequestService;
import medical.util.MedicalServiceErrorType;

@RestController
@RequestMapping("/medicalService/api")
public class RequestRestController {
    public static final Logger logger = LoggerFactory.getLogger(RequestRestController.class);

    @Autowired
    RequestService requestService; //Service which will do all data retrieval/manipulation work

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public ResponseEntity<List<Request>> listAllRequests() {
        List<Request> requests = requestService.findAllRequests();
        if (requests.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Request>>(requests, HttpStatus.OK);
    }

    // -------------------Create a Request-------------------------------------------

    @RequestMapping(value = "/request/", method = RequestMethod.POST)
    public ResponseEntity<?> createRequest(@RequestBody Request request) {
        logger.info("Creating Request : {}", request);

        Request newRequest = requestService.createRequest(request);

        return new ResponseEntity<Request>(newRequest, HttpStatus.OK);
    }


   /* // ------------------- Update a Request ------------------------------------------------

    @RequestMapping(value = "/request/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateRequest(@PathVariable("id") String id, @RequestBody Request request) {
        logger.info("Updating Request with id {}", id);

        Request currentRequest = requestService.findById(id);

        if (currentRequest == null) {
            logger.error("Unable to update. Request with id {} not found.", id);
            return new ResponseEntity(new MedicalServiceErrorType("Unable to upate Request.", "Request with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentRequest.setDrugName(request.getDrugName());
        currentRequest.setCantity(request.getCantity());

        requestService.updateRequest(currentRequest);
        return new ResponseEntity<Request>(currentRequest, HttpStatus.OK);
    }
*/
    // ------------------- Delete a Request-----------------------------------------

    @RequestMapping(value = "/request/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRequest(@PathVariable("id") String id) {
        logger.info("Fetching & Deleting Request with id {}", id);

        Request request = requestService.findById(id);
        if (request == null) {
            logger.error("Unable to delete. Request with id {} not found.", id);
            return new ResponseEntity(new MedicalServiceErrorType("Unable to delete Request.", "Request with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        requestService.deleteRequestById(id);
        return new ResponseEntity<Request>(HttpStatus.NO_CONTENT);
    }

}
