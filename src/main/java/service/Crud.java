package service;

import model.staff.Person;

import java.util.List;
import java.util.Map;

public interface Crud<T> {

    Person create(T entity);
    T read(Long id);
    void update(T entity);
    void delete(Long id);
    List<T> list(Map<String, String> filter);
}
