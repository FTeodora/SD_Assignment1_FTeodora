package repository;

import entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.ResultSet;
import java.util.List;

public class UserRepo extends AbstractRepo<User>{
	
	public boolean insertNewUser(User u){
		return super.insertNewEntity(u);
	}
	public boolean updateUser(User newInfo){
		return super.updateInfo(newInfo,User.class);
	}
	public List<User> findByFilters(User filters){
		return super.findByCriteria(filters,User.class);
	}
	public User findByEMail(String eMail){
		EntityManager manager = entityManagerFactory.createEntityManager();
		Session session=(Session) manager.getDelegate();
		CriteriaBuilder criteriaBuilder=manager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
		Root<User> root=criteriaQuery.from(User.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("eMail"),eMail));


		Query<User> query = session.createQuery(criteriaQuery);
		List<User> results = query.getResultList();
		manager.close();
		if(results.isEmpty())
			return null;
		return results.get(0);
	}
	public List<User> getAllUsers(){
		return super.getAllEntities(User.class);
	}
	public boolean deleteUser(String id){
		return super.deleteObject(id,User.class);
	}
	public User fetchUserByID(String id){ return super.findById(User.class,id);}

}
