package repository;

import entity.PersonalProperty;
import entity.Request;
import entity.User;
import entity.enums.RequestStatus;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RequestRepo extends AbstractRepo<Request> {
    public boolean addRequest(Request newRequest){
        return super.insertNewEntity(newRequest);
    }
    public boolean deleteRequest(String deletedRequest){
        return super.deleteObject(deletedRequest,Request.class);
    }
    public List<Request> getAllDatabaseRequests(){
        List<String> columns=new ArrayList<>();
        columns.add("status");
        columns.add("requestedAt");
        return super.getAllEntities(Request.class,columns,true);
    }
    public List<Request> getAllRequestsByUser(User u){
        return super.getAllEntitiesJoin(Request.class,u,User.class,"requester");
    }
    public boolean changeStatus(Request request, RequestStatus newStatus){
        request.setStatus(newStatus);
        return super.updateInfo(request,Request.class);
    }
    public int selectRequestsThisYear(User owner){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        EntityManager manager = entityManagerFactory.createEntityManager();
        Session session=(Session) manager.getDelegate();
        Query<BigInteger> query = session.createNativeQuery(
                "SELECT COUNT(*) FROM request WHERE YEAR(request.requestedAt)=? AND request.requester_id=?");
        query.setParameter(1,year);
        query.setParameter(2,owner.getId());
        Integer res= query.uniqueResult().intValue();
        manager.close();
        return res;
    }
    public List<Request> filterRequestsWithJoins(Request request){
        return super.findByCriteriaWithJoin(request,Request.class);
    }
}
