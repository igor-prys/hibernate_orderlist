package dao;

import java.util.List;

public interface GeneralDao<T> {
    void create(T t);

    T find(Long id);

    List<T> findAll();

    void update(T t);

    boolean delete(Long id);
}
