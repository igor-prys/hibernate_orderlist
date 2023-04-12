package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.Person;

import java.util.List;

public class PersonDaoImpl implements PersonDao {
    private final SessionFactory sessionFactory = SessionFactoryCreator.getInstance().getSessionFactory();
    private final OrderDao orderDao=new OrderDaoImpl();

    @Override
    public void add(Person person) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(person);
            session.getTransaction().commit();
        }
    }

    @Override
    public Person find(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Person.class, id);
        }
    }

    @Override
    public List<Person> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select p from Person p", Person.class).list();
        }
    }

    @Override
    public void update(Person person) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(person);
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean delete(Long id){
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            Person person=session.get(Person.class,id);
            for(int i=0;i<person.getOrderList().size();i++){
              orderDao.delete(person.getOrderList().get(i).getId());
            }
            session.remove(person);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
