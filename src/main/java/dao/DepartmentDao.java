package dao;

import jdk.internal.org.objectweb.asm.Type;
import model.staff.Department;
import model.staff.Person;
import service.XmlService.DepartmentXmlService;
import service.XmlService.PersonXmlService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ДАО класс для отделов
 * @author nsychev
 * @since 29.05.2018
 */
public class DepartmentDao extends AbstractEntityDao<Department> {

    /** Базовый запрос выборки*/
    private final String SELECTSQL =
            "SELECT * FROM departments\n";
    /** Базовый запрос создания таблицы*/
    private final String CREATESQL =
            "CREATE TABLE departments(" +
                    "id BIGINT GENERATED BY DEFAULT AS IDENTITY, " +
                    "shortName varchar(70)," +
                    "fullName varchar(70), " +
                    "head BIGINT," +
                    "contacts varchar(70)," +
                    "CONSTRAINT primary_key PRIMARY KEY (id)" +
            ")\n";


    /** Получение имени таблицы
     * @return String имя таблицы
     */
    @Override
    protected String getTableName() {
        return "DEPARTMENTS";
    }

    /**
     * Получение сущности по id
     * @param id идентификатор сущности
     * @return сущность
     */
    @Override
    public Department read(Long id) {
        PreparedStatement ps = getPreparedStatement(SELECTSQL + "WHERE id = ?");
        Department department = null;
        try {
            department = new Department();
            ps.setString(1, id.toString());
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                department.setId(resultSet.getLong("id"));
                department.setShortName(resultSet.getString("shortName"));
                department.setFullName(resultSet.getString("fullName"));
                department.setContacts(resultSet.getString("contacts"));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Incorrect data");
        }finally {
            closePreparedStatement(ps);
        }
        return department;
    }

    /**
     * Получение списка сущностей
     * @return список сущностей
     */
    @Override
    public List<Department> list() {
        List<Department> list = new ArrayList<>();
        PreparedStatement ps = getPreparedStatement(SELECTSQL);
        try {
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Department department = new Department();
                department.setId(resultSet.getLong("id"));
                department.setShortName(resultSet.getString("shortName"));
                department.setFullName(resultSet.getString("fullName"));
                department.setContacts(resultSet.getString("contacts"));
                list.add(department);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Incorrect data" + e);
        }finally {
            closePreparedStatement(ps);
        }
        return list;
    }

    /**
     * Редактирование сущности
     * @param entity значения для редактирования
     */
    @Override
    public Department update(Department entity) {
        return null;
    }

    /** Удаление сущности по id
     * @param id идентификатор сущности
     */
    @Override
    public void delete(Long id) {

    }

    /**
     * Создание сущности
     * @param entity создаваяемая сущность
     * @return созданная сущность
     */
    @Override
    public Department create(Department entity) {
        PreparedStatement ps;
        if(entity.getId() != null) {
            ps = getPreparedStatement("INSERT INTO departments( shortName, fullName, contacts, id) VALUES(?, ?, ?, ?)");
        }else{
            ps = getPreparedStatement("INSERT INTO departments( shortName, fullName, contacts) VALUES( ?, ?, ?)");
        }
        try {
            ps.setString(1, entity.getShortName());
            ps.setString(2, entity.getFullName());
            ps.setString(3, entity.getContacts());
            if(entity.getId() != null) {
                ps.setLong(4, entity.getId());
            }
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            while (resultSet.next()){
                entity.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Incorrect data: "+ entity + " " + e);
        }finally {
            closePreparedStatement(ps);
        }
        return read(entity.getId());
    }

    @Override
    protected String getSelectSql() {
        return SELECTSQL;
    }

    @Override
    protected String getCreateSQL() {
        return CREATESQL;
    }

    /**
     * Выгрузка данных из xml в бд
     */
    public void loadFromXmlToDB(){
        DepartmentXmlService xmlService = new DepartmentXmlService();
        List<Department> personList = xmlService.getList();
        for (Department entity: personList){
            try {
                create(entity);
            } catch (Exception e){}
        }
    }
}