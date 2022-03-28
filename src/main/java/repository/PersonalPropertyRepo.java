package repository;

import entity.PersonalProperty;
import entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

public class PersonalPropertyRepo extends AbstractRepo<PersonalProperty>{

    public boolean insertNewAddress(PersonalProperty address) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try{
            manager.getTransaction().begin();
            Session session=(Session) manager.getDelegate();
            User u=session.load(User.class,address.getUser().getId());
            address.setUser(u);
            session.save(address);

            manager.getTransaction().commit();
            manager.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<PersonalProperty> getAllPersonalProperties(User owner) {
        PersonalProperty property=new PersonalProperty();
        property.setUser(owner);
        return super.getAllEntitiesJoin(PersonalProperty.class,owner,User.class,"user");
    }
    public boolean removeAddress(PersonalProperty address){
        return super.deleteObject(address.getId(),PersonalProperty.class);
    }
    public int getPersonalPropertyCount(User owner){
        EntityManager manager = entityManagerFactory.createEntityManager();
        Session session=(Session) manager.getDelegate();
        Query<BigInteger> query = session.createNativeQuery(
                "SELECT COUNT(*) FROM Personal_Property WHERE Personal_Property.user_id=?");
        query.setParameter(1,owner.getId());
        int res=query.uniqueResult().intValue();
        manager.close();
        return res;
    }

}
