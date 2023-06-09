package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.Order;
import pojo.Product;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory = SessionFactoryCreator.getInstance().getSessionFactory();

    @Override
    public void add(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public Product find(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select p from Product p", Product.class).list();
        }
    }

    @Override
    public void update(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.remove(product);
            session.getTransaction().commit();
        }
    }
}
