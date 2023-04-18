package managers;

import dao.OrderDao;
import dao.ProductDao;
import pojo.Order;

import java.util.List;

public class OrderManager {
    private OrderDao orderDao;
    private ProductDao productDao;

    public OrderManager(OrderDao orderDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
    }

    public void create(Order order) {
        orderDao.create(order);
    }

    public Order find(Long id) {
        return orderDao.find(id);
    }

    public List<Order> findAll() {
        return orderDao.findAll();
    }

    public void update(Order order) {
        orderDao.update(order);
    }

    public void delete(Long id) {
        Order order = orderDao.find(id);
        for (int i = 0; i < order.getProductList().size(); i++) {
            productDao.delete(order.getProductList().get(i).getId());
        }
        orderDao.delete(id);
    }

    public Order findOrderWithProduct(Long id) {
        return orderDao.findOrderWithProduct(id);
    }
}
