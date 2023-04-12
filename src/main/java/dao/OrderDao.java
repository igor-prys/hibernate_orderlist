package dao;


import pojo.Order;

import java.util.List;

public interface OrderDao {
    void add(Order order);

    Order find(Long id);

    List<Order> findAll();

    void update(Order order);

    boolean delete(Long id);
}
