package repository;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepo<T> {
    protected final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    protected boolean insertNewEntity(T entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch(Exception e){
            return false;
        }
        em.close();
        return true;
    }
    protected boolean updateInfo(T newInfo,Class<T> objectType){
        EntityManager manager = entityManagerFactory.createEntityManager();
        try{
            manager.getTransaction().begin();
            Session session=(Session) manager.getDelegate();
            session.update(newInfo);
            session.flush();

            manager.getTransaction().commit();
            manager.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }
    protected T findById(Class<T> objectClass,Object id){
        EntityManager manager = entityManagerFactory.createEntityManager();
        T result=null;
        manager.getTransaction().begin();
        result=manager.find(objectClass,id);
        manager.getTransaction().commit();
        manager.close();
        return result;

    }
    protected List<T> findByCriteria(T object,Class<T> objectClass){
        EntityManager manager = entityManagerFactory.createEntityManager();
        Session session=(Session) manager.getDelegate();
        CriteriaBuilder criteriaBuilder=manager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery=criteriaBuilder.createQuery(objectClass);
        Root<T> root=criteriaQuery.from(objectClass);
        List<Predicate> filters=PredicateFactory.fieldsToPredicates(criteriaBuilder,object,root);
        criteriaQuery.select(root).where((filters.toArray(new Predicate[filters.size()])));

        Query<T> query = session.createQuery(criteriaQuery);
        List<T> results = query.getResultList();
        manager.close();
        return results;
    }

    /**
     * Also adds the join entity fields columns in the criteria builder
     * @param object
     * @param objectClass
     * @return
     */
    protected List<T> findByCriteriaWithJoin(T object,Class<T> objectClass){
        EntityManager manager = entityManagerFactory.createEntityManager();
        Session session=(Session) manager.getDelegate();
        CriteriaBuilder criteriaBuilder=manager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery=criteriaBuilder.createQuery(objectClass);
        Root<T> root=criteriaQuery.from(objectClass);
        List<Predicate> filters=PredicateFactory.fieldsToPredicatesAddJoins(criteriaBuilder,object,root);
        criteriaQuery.select(root).where((filters.toArray(new Predicate[filters.size()])));

        Query<T> query = session.createQuery(criteriaQuery);
        List<T> results = query.getResultList();
        manager.close();
        return results;
    }
    protected boolean deleteObject(Object identityField,Class<T> objectType){
        EntityManager manager = entityManagerFactory.createEntityManager();
        try{
            Session session=(Session) manager.getDelegate();
            manager.getTransaction().begin();
            T object=manager.find(objectType,identityField);
            session.delete(object);
            session.flush();
            manager.getTransaction().commit();
            manager.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    protected <S> List<T> getAllEntitiesJoin(Class<T> rootObjectClass, S theOtherObject, Class<S> theOtherObjectClass,String joinColumnName){
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(rootObjectClass);
        Root<T> root = criteria.from(rootObjectClass);
        criteria.select(root);
        Join<T, S> join = root.join(joinColumnName);
        List<Predicate> joinPred=PredicateFactory.fieldsToPredicatesExact(builder,theOtherObject,join);
        criteria.where(joinPred.toArray(new Predicate[joinPred.size()]));
        TypedQuery<T> query = em.createQuery(criteria);
        return query.getResultList();
    }
    protected List<T> getAllEntities(Class<T> objectClass){
        EntityManager manager = entityManagerFactory.createEntityManager();
        Session session=(Session) manager.getDelegate();
        CriteriaBuilder criteriaBuilder=manager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery=criteriaBuilder.createQuery(objectClass);
        Root<T> root=criteriaQuery.from(objectClass);
        criteriaQuery.select(root);

        Query<T> query = session.createQuery(criteriaQuery);
        List<T> results = query.getResultList();
        manager.close();
        return results;
    }
    protected List<T> getAllEntities(Class<T> objectClass, String orderByColumn, boolean ascending){
        EntityManager manager = entityManagerFactory.createEntityManager();
        Session session=(Session) manager.getDelegate();
        CriteriaBuilder criteriaBuilder=manager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery=criteriaBuilder.createQuery(objectClass);

        Root<T> root=criteriaQuery.from(objectClass);
        if(ascending)
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderByColumn)));
        else
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderByColumn)));
        criteriaQuery.select(root);


        Query<T> query = session.createQuery(criteriaQuery);
        List<T> results = query.getResultList();
        manager.close();
        return results;
    }
    protected List<T> getAllEntities(Class<T> objectClass, List<String> orderingColumns, boolean ascending){
        EntityManager manager = entityManagerFactory.createEntityManager();
        Session session=(Session) manager.getDelegate();
        CriteriaBuilder criteriaBuilder=manager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery=criteriaBuilder.createQuery(objectClass);

        Root<T> root=criteriaQuery.from(objectClass);
        List<Order> orderList=new ArrayList<>();
        orderingColumns.forEach(orderByColumn->{
            if(ascending)
                orderList.add(criteriaBuilder.asc(root.get(orderByColumn)));
            else
                orderList.add(criteriaBuilder.asc(root.get(orderByColumn)));
        });
        criteriaQuery.orderBy(orderList);
        criteriaQuery.select(root);

        Query<T> query = session.createQuery(criteriaQuery);
        List<T> results = query.getResultList();
        manager.close();
        return results;
    }
}
