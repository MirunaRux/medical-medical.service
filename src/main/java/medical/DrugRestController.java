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

import medical.model.Drug;
import medical.service.DrugService;
import medical.util.MedicalServiceErrorType;

@RestController
@RequestMapping("/medicalService/api")
public class DrugRestController {
    public static final Logger logger = LoggerFactory.getLogger(DrugRestController.class);

    @Autowired
    DrugService drugService; //Service which will do all data retrieval/manipulation work

    @RequestMapping(value = "/drug", method = RequestMethod.GET)
    public ResponseEntity<List<Drug>> listAllDrugs() {
        List<Drug> drugs = drugService.findAllDrugs();
        if (drugs.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Drug>>(drugs, HttpStatus.OK);
    }

    // -------------------Create a Drug-------------------------------------------

    @RequestMapping(value = "/drug/", method = RequestMethod.POST)
    public ResponseEntity<?> createDrug(@RequestBody Drug drug) {
        logger.info("Creating Drug : {}", drug);

        Drug newDrug = drugService.createDrug(drug);

        return new ResponseEntity<Drug>(newDrug, HttpStatus.OK);
    }


    // ------------------- Update a Drug ------------------------------------------------

    @RequestMapping(value = "/drug/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateDrug(@PathVariable("id") String id, @RequestBody Drug drug) {
        logger.info("Updating Drug with id {}", id);

        Drug currentDrug = drugService.findById(id);
        currentDrug.setName(drug.getName());
        currentDrug.setDrugNumber(drug.getDrugNumber());

        drugService.updateDrug(currentDrug);
        return new ResponseEntity<Drug>(currentDrug, HttpStatus.OK);
    }

}
