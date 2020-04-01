package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserById(Long id) {
      Query query = sessionFactory.openSession().createQuery("From User u where u.id = :nowId ");
      query.setParameter("nowId", id);
      return (User) query.uniqueResult();
   }

   @Override
   public User getUserByCarSeries(int series) {
      Query query = sessionFactory.openSession().createQuery("From Car cr where cr.series = :nowSeries ");
      query.setParameter("nowSeries", series);
      Car car = (Car) query.uniqueResult();
      return car.getUser();
   }

   @Override
   public User getUserByCarId(Long id) {
      Query query = sessionFactory.openSession().createQuery("From Car cr where cr.id = :nowId ");
      query.setParameter("nowId", id);
      Car car = (Car) query.uniqueResult();
      return car.getUser();
   }
}
