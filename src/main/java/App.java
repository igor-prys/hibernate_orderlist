

import dao.PersonDao;
import dao.ProductDao;
import dao.orm.PersonDaoImpl;
import dao.orm.ProductDaoImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pojo.Order;
import pojo.Person;
import pojo.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
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
//        try (Session session = sf.openSession()) {
//            session.beginTransaction();
//            session.persist(gosha);
//            session.persist(cake);
//            session.persist(funnyTrain);
//            session.persist(soap);
//            session.persist(goshaOrder);
//            session.getTransaction().commit();
//        }
        PersonDaoImpl pdi = new PersonDaoImpl();
        List<Person>personList=pdi.findAllWithOrders();

        System.out.println(personList.get(0).getOrderList().get(0));


//        try(Session session=sf.openSession()){
//            session.beginTransaction();
//            session.merge(person);
//            Order order=person.getOrderList().get(0);
//            System.out.println(order);
//            session.getTransaction().commit();
//        }
//        System.out.println(result);
//        Order order=result.get(1).getOrderList().get(0);
//        System.out.println(order);

//        pdi.delete(2L);
//        pdi.delete(1L);
//        OrderDaoImpl odi=new OrderDaoImpl();
//        odi.delete(2L);
//        ProductDao productDao = new ProductDaoImpl();
//        System.out.println(productDao.find(7L));
//        System.out.println(productDao.productCostMore());
//        productDao.delete(8L);
    }
}
