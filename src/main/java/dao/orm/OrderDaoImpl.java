package dao.orm;

import dao.OrderDao;
import dao.ProductDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.Order;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory = SessionFactoryCreator.getInstance().getSessionFactory();

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
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Order order=new Order();
            order.setId(id);
            session.remove(order);
            session.getTransaction().commit();
        }
    }
    public Order findOrderWithProduct(Long id){
        try(Session session=sessionFactory.openSession()){
            return session.createQuery("select o from Order o join fetch o.productList where o.id=:id",Order.class)
                    .setParameter("id",id).uniqueResult();
        }
    }
}
