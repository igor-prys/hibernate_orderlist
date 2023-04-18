package dao;

import pojo.Person;

import java.util.List;

public interface PersonDao extends GeneralDao<Person>{
    List<Person> findAllWithOrders();
    Person findByIdWithOrders(Long id);

}
