package medical.service;

import medical.dao.PacientDao;
import medical.model.Pacient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Service annotation is used in your service layer and annotates classes that perform service tasks, often you don't use it but in many case you use this annotation to represent a best practice.
 * For example, you could directly call a Data Access Object (DAO) class to persist an object to your database but this is horrible.
 * It is pretty good to call a service class that calls a DAO. This is a good thing to perform the separation of concerns pattern.
 */
@Service
public class PacientService {

    private static List<Pacient> pacients;

    @Autowired
    private PacientDao pacientDao;

    public List<Pacient> findAllPacients() {
        pacients = pacientDao.getAllPacients();
        return pacients;
    }

    public Pacient createPacient(Pacient pacient) {
        pacientDao.create(pacient);
        return pacient;
    }

    public void updatePacient(Pacient pacient) {
        pacientDao.update(pacient);
    }
}