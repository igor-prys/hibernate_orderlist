package dao.orm;

import dao.OrderDao;
import dao.ProductDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.Order;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory = SessionFactoryCreator.getInstance().getSessionFactory();
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public void create(Order order) {
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
    public boolean delete(Long id) {
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
