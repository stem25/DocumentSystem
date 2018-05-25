package dao;

import exception.ConnectionException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public abstract class AbstractEntityDao<E> {

    private Connection connection;

    private String connectionUrl = "jdbc:derby:ecmDb;create=true";

    public AbstractEntityDao(){
        try {
            Properties properties = new Properties();
            properties.setProperty("derby.language.sequence.preallocator", "1");
            connection = DriverManager.getConnection(connectionUrl, properties);
        } catch (SQLException e) {
            throw new ConnectionException("Incorrect URL");
        }

        if(!isTableExsit(getTableName().toUpperCase())){
            System.out.println("Creating table " + getCreateSQL());
            try {
                Statement st = connection.createStatement();
                st.execute(getCreateSQL());
                st.close();
                connection.commit();
            } catch (SQLException e) {
                throw new IllegalArgumentException("Can't create table "+getTableName()+" "+e);
            }
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public PreparedStatement getPreparedStatement(String sql){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new ConnectionException("Can't create Prepared Statement " + e);
        }
        return preparedStatement;
    }

    public void closePreparedStatement(PreparedStatement ps){
        if(ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new ConnectionException("Can't close Prepared Statement");
            }
        }
    }

    public abstract E read(Long id);
    public abstract List<E> list();
    public abstract E update(E entity);
    public abstract void delete(Long id);
    public abstract E create(E entity);
    protected abstract String getSelectSql();
    protected abstract String getCreateSQL();
    protected abstract String getTableName();

    private boolean isTableExsit(String name){
        String[] names = {"TABLE"};
        try {
            ResultSet resultSet = connection.getMetaData().getTables(null, null, null, names);
            while (resultSet.next()){
                if(resultSet.getString("TABLE_NAME").equals(name)){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new ConnectionException("Get metadata troubles");
        }
    }
}
