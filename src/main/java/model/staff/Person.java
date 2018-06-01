package model.staff;

import jdk.nashorn.internal.ir.annotations.Ignore;

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

    private List<Department> departments;

    private String avatar;

    public static List<Person> allInstance;
    static {
        allInstance = new ArrayList<>();
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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String getShortname(){
        StringBuilder shortname= new StringBuilder();
        shortname.append(this.lastName != null ? this.lastName : "")
                .append(" ")
                .append(this.firstName != null ? this.firstName : "")
                .append(" ")
                .append(this.middleName != null ? this.middleName : "");
        return shortname.toString().trim();
    }
    @Override
    public int compareTo(Person o) {
        return this.getShortname().compareTo(o.getShortname());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", departments=" + departments +
                '}';
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
