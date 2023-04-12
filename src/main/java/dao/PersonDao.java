package dao;

import pojo.Person;

import java.util.List;

public interface PersonDao {
    void add(Person person);
    Person find(Long id);
    List<Person> findAll();
    void update(Person person);
    boolean delete(Long id);

}
