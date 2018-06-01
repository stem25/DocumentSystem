package service;

import dao.DepartmentDao;
import dao.PersonDao;
import model.staff.Department;
import model.staff.Person;

import java.util.List;
import java.util.Map;

public class DepartmentService implements Crud<Department> {

    private DepartmentDao dao = new DepartmentDao();

    @Override
    public Department create(Department entity) {
        return dao.create(entity);
    }

    @Override
    public Department read(Long id) {
        return dao.read(id);
    }

    @Override
    public void update(Department entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public List<Department> list(Map<String, String> filter) {
        return dao.list();
    }
}
