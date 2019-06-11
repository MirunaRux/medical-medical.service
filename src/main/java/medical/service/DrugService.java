package medical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import medical.dao.DrugDao;
import medical.dao.DrugDao;
import medical.model.Drug;
import medical.model.Drug;

@Service
public class DrugService {

    private static List<Drug> drugs;

    @Autowired
    private DrugDao drugDao;

    public List<Drug> findAllDrugs() {
        drugs = drugDao.getAllDrugs();
        return drugs;
    }

    public Drug createDrug(Drug drug) {
        drugDao.create(drug);
        return drug;
    }

    public void updateDrug(Drug drug) {
        drugDao.update(drug);
    }
    
    public Drug findById(String id) {
        for (Drug drug : drugs) {
            if (drug.getId().equals(id)) {
                return drug;
            }
        }
        return null;
    }    
}
