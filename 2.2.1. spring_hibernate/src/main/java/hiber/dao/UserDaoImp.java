package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    /*
    При удачном резуьтате возвращает пользователя
    При неудачном выбрасывает исключение NoResultException: No entity found for query
     */
    @Override
    public User getUserByCar(String model, int series) {
        String hql = "from User where car.model=:model and car.series=:series";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("model", model).setParameter("series", series);
        User user = (User) query.getSingleResult();
        return user;


    }

}
