package model.staff;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
/**
 * Временное хранилище для персон
 * @author nsychev
 * @since 22.05.2018
 */
@XmlType
@XmlRootElement
public class Person extends Staff implements Comparable<Person>{
    /**Имя*/
    private String firstName;
    /**Отчество*/
    private String middleName;
    /**Фамилия*/
    private String lastName;
    /**Должность*/
    private String position;

    public static List<Person> allInstance;
    static {
        allInstance = new ArrayList<>();
    }
    public Person(){
        allInstance.add(this);
    }

    @Override
    protected void finalize() throws Throwable {
        allInstance.remove(this);
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public static List<String> persons;
    static {
        persons = new ArrayList<>();
        persons.add("Иванов Иван Иванович");
        persons.add("Владимиров Владимир Владимирович");
        persons.add("Константинов Константин Константинович");
        persons.add("Алексеев Алексей Алексеевич");
        persons.add("Александров Александр Александрович");
    }

    public String getShortname(){
        StringBuilder shortname= new StringBuilder();
        shortname.append(this.lastName != null ? this.lastName : "")
                .append(" ")
                .append(this.firstName != null ? this.firstName : "")
                .append(" ")
                .append(this.middleName != null ? this.middleName : "");

        return shortname.toString();
    }
    @Override
    public int compareTo(Person o) {
        return this.getShortname().compareTo(o.getShortname());
    }
}
