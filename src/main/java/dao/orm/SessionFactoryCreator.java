package dao.orm;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pojo.Order;
import pojo.Person;
import pojo.Product;

public class SessionFactoryCreator {
    private static SessionFactoryCreator sessionFactoryCreator;
    private SessionFactory sessionFactory;

    private SessionFactoryCreator(){
        sessionFactory=new Configuration().configure()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Product.class).
                buildSessionFactory();
    }
    public static SessionFactoryCreator getInstance(){
        if(sessionFactoryCreator==null){
            sessionFactoryCreator=new SessionFactoryCreator();
        }
        return sessionFactoryCreator;
    }
    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
