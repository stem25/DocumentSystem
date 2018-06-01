package service;

import dao.PersonDao;
import model.staff.Person;

import java.util.List;
import java.util.Map;

public class PersonService implements Crud<Person> {
    private PersonDao dao = new PersonDao();
    @Override
    public Person create(Person entity) {
        return dao.create(entity);
    }

    @Override
    public Person read(Long id) {
        return dao.read(id);
    }

    @Override
    public void update(Person entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public List<Person> list(Map<String, String> filter) {
        return dao.list();
    }
}
