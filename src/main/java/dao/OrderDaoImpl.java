package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.Order;
import pojo.Product;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private SessionFactory sessionFactory = SessionFactoryCreator.getInstance().getSessionFactory();
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public void add(Order order) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(order);
            session.getTransaction().commit();
        }
    }

    @Override
    public Order find(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Order.class, id);
        }
    }

    @Override
    public List<Order> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select o from Order o", Order.class).list();
        }
    }

    @Override
    public void update(Order order) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(order);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Order order = session.get(Order.class, id);
            for (int i = 0; i < order.getProductList().size(); i++) {
                Product product = order.getProductList().get(i);
                session.remove(product);
            }
            session.remove(order);
            session.getTransaction().commit();
        }
    }

    public boolean dell(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Order order = session.get(Order.class, id);
            for (int i = 0; i < order.getProductList().size(); i++) {
                productDao.delete(order.getProductList().get(i).getId());
            }
            session.remove(order);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
