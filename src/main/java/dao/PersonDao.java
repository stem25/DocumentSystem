package dao;

import model.staff.Person;
import service.XmlService.PersonXmlService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ДАО класс для персон
 * @author nsychev
 * @since 29.05.2018
 */
public class PersonDao extends AbstractEntityDao<Person> {

    /** Базовый запрос выборки*/
    private final String SELECTSQL =
            "SELECT * FROM persons\n";
    /** Базовый запрос создания таблицы*/
    private final String CREATESQL =
            "CREATE TABLE PERSONS(" +
                    "id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY, " +
                    "firstName varchar(70)," +
                    " middleName varchar(70), " +
                    "lastName varchar(70)," +
                    "avatar varchar(70)," +
                    "CONSTRAINT primary_key PRIMARY KEY (id)" +
            ")\n";


    /** Получение имени таблицы
     * @return String имя таблицы
     */
    @Override
    protected String getTableName() {
        return "PERSONS";
    }

    /**
     * Получение сущности по id
     * @param id идентификатор сущности
     * @return сущность
     */
    @Override
    public Person read(Long id) {
        PreparedStatement ps = getPreparedStatement(SELECTSQL + "WHERE id = ?");
        Person person = null;
        try {
            person = new Person();
            ps.setString(1, id.toString());
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstName"));
                person.setLastName(resultSet.getString("lastName"));
                person.setMiddleName(resultSet.getString("middleName"));
                person.setAvatar(resultSet.getString("avatar"));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Incorrect data");
        }finally {
            closePreparedStatement(ps);
        }
        return person;
    }

    /**
     * Получение списка сущностей
     * @return список сущностей
     */
    @Override
    public List<Person> list() {
        List<Person> list = new ArrayList<>();
        PreparedStatement ps = getPreparedStatement(SELECTSQL);
        try {
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstName"));
                person.setLastName(resultSet.getString("lastName"));
                person.setMiddleName(resultSet.getString("middleName"));
                person.setAvatar(resultSet.getString("avatar"));
                list.add(person);
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
    public Person update(Person entity) {
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
    public Person create(Person entity) {
        PreparedStatement ps;
        if(entity.getId() == null){
            ps = getPreparedStatement("INSERT INTO persons(firstName, middleName, lastName, avatar, id) VALUES(?, ?, ?, ?, ?)");
        }else{
            ps = getPreparedStatement("INSERT INTO persons(firstName, middleName, lastName, avatar) VALUES(?, ?, ?, ?)");
        }
        try {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getMiddleName());
            ps.setString(3, entity.getLastName());
            ps.setString(4, entity.getAvatar());
            if(entity.getId()!=null){
                ps.setLong(5, entity.getId());
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
        PersonXmlService xmlService = new PersonXmlService();
        List<Person> personList = xmlService.getList();
        for (Person person: personList){
            try {
                create(person);
            } catch (Exception e){}
        }
    }
}
