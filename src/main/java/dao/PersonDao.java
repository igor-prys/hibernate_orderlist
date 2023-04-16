package dao;

import pojo.Person;

import java.util.List;

public interface PersonDao {
    void create(Person person);
    Person find(Long id);
    List<Person> findAll();
    void update(Person person);
    boolean delete(Long id);
    List<Person> findAllWithOrders();
    Person findByIdWithOrders(Long id);

}
