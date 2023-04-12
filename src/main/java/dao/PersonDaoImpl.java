package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.Order;
import pojo.Person;
import pojo.Product;

import java.util.List;

public class PersonDaoImpl implements PersonDao {
    private SessionFactory sessionFactory = SessionFactoryCreator.getInstance().getSessionFactory();

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
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Person person=session.get(Person.class,id);
            List<Order>orders=person.getOrderList();
            for(int i=0;i<orders.size();i++){
                Order order=person.getOrderList().get(i);
                for(int j=0;j<order.getProductList().size();j++){
                    Product product=order.getProductList().get(j);
                    session.remove(product);
                }

                session.remove(order);
            }
            session.remove(person);
            session.getTransaction().commit();
        }
    }
}
