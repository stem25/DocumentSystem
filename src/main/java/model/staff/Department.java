package model.staff;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**Модельный класс для "Отделов"
 * @author nsychev
 * @since 22.05.2018
 */
@XmlType
@XmlRootElement
public class Department extends Staff {
    /**Краткое имя*/
    private String shortName;
    /**Полное имя*/
    private String fullName;
    /**Руководитель(Person)*/
    private Person head;
    /**Контактные телефоны*/
    private String contacts;

    private Organization organization;

    private List<Person> persons;

    public static List<Department> allInstance;
    static {
        allInstance = new ArrayList<>();
    }
    public Department(){
        allInstance.add(this);
    }

    @Override
    protected void finalize() throws Throwable {
        allInstance.remove(this);
    }
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Person getHead() {
        return head;
    }

    public void setHead(Person head) {
        this.head = head;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + this.getId() + '\'' +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", head=" + head +
                ", contacts='" + contacts + '\'' +
                ", organization=" + organization +
                ", persons=" + persons +
                '}';
    }
}
