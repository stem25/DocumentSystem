package dao;

import model.staff.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDao extends AbstractEntityDao<Person> {
    private final String SELECTSQL =
            "SELECT * FROM persons\n";
    private final String CREATESQL =
            "CREATE TABLE PERSONS(" +
                    "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, " +
                    "firstName varchar(70)," +
                    " middleName varchar(70), " +
                    "lastName varchar(70)," +
                    "CONSTRAINT primary_key PRIMARY KEY (id)" +
            ")\n";


    @Override
    protected String getTableName() {
        return "PERSONS";
    }
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
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Incorrect data");
        }
        return person;
    }

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
                list.add(person);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Incorrect data");
        }
        return list;
    }

    @Override
    public Person update(Person entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Person create(Person entity) {
        PreparedStatement ps = getPreparedStatement("INSERT INTO persons( firstName, middleName, lastName) VALUES( ?, ?, ?)");
        try {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getMiddleName());
            ps.setString(3, entity.getLastName());
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            while (resultSet.next()){
                entity.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Incorrect data: "+ entity);
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

    public void loadFromXmlToDB(){

    }
}
