package dao;


import pojo.Order;

import java.util.List;

public interface OrderDao extends GeneralDao<Order>{
    Order findOrderWithProduct(Long id);
}
