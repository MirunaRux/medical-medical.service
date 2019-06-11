package medical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import medical.dao.RequestDao;
import medical.model.Request;

@Service
public class RequestService {

    private static List<Request> requests;

    @Autowired
    private RequestDao requestDao;

    public List<Request> findAllRequests() {
        requests = requestDao.getAllRequests();
        return requests;
    }

    public Request createRequest(Request request) {
        requestDao.create(request);
        return request;
    }

    public Request findById(String id) {
        for (Request request : requests) {
            if (request.getId().equals(id)) {
                return request;
            }
        }
        return null;
    }

    public boolean deleteRequestById(String id) {
        /*for (Iterator<Request> iterator = requests.iterator(); iterator.hasNext(); ) {
            Request request = iterator.next();
            if (request.getId().equals(id)) {
                iterator.remove();
            }
        }*/

        try{
            requestDao.delete(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean isRequestExist(Request request) {
        return findById(request.getId()) != null;
    }
}
