package dao;

import pojo.Product;

import java.util.List;

public interface ProductDao {
    void create(Product product);
    Product find(Long id);
    List<Product> findAll();
    void update(Product product);
    void delete(Long id);
}
