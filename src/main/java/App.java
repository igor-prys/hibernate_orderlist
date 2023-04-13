

import dao.OrderDaoImpl;
import dao.PersonDaoImpl;
import dao.ProductDaoImpl;
import dao.SessionFactoryCreator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pojo.Order;
import pojo.Person;
import pojo.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] arg) {
        Person gosha = Person.builder().firstname("Gosha").lastname("Ivanov").build();
        Person masha = Person.builder().firstname("Masha").lastname("Petrova").build();

        BigDecimal cakePrice = new BigDecimal(12.5);
        Product cake = Product.builder().name("cake").price(cakePrice).type(Product.Type.FOOD).build();
        BigDecimal funnyTrainPrice = new BigDecimal(25.0);
        Product funnyTrain = Product.builder().name("funnyTrain").price(funnyTrainPrice).type(Product.Type.TOY).build();
        BigDecimal soapPrice = new BigDecimal(1.2);
        Product soap = Product.builder().name("soap").price(soapPrice).type(Product.Type.COSMETIC).build();

        Order goshaOrder = Order.builder().productList(List.of(cake, funnyTrain, soap)).person(gosha)
                .creationDate(LocalDate.now()).build();
        cake.setOrder(goshaOrder);
        funnyTrain.setOrder(goshaOrder);
        soap.setOrder(goshaOrder);

        SessionFactory sf = new Configuration().configure()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();

        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.persist(gosha);
            session.persist(cake);
            session.persist(funnyTrain);
            session.persist(soap);
            session.persist(goshaOrder);
            session.getTransaction().commit();
        }
//        PersonDaoImpl pdi = new PersonDaoImpl();
//        pdi.delete(2L);
//        pdi.delete(1L);
//        OrderDaoImpl odi=new OrderDaoImpl();
//        odi.delete(2L);
        ProductDaoImpl productDao=new ProductDaoImpl();
        System.out.println(productDao.productCostMore());
//        productDao.delete(8L);
    }
}
