package model.document;

import model.staff.Person;

import java.util.Date;
/**
 * Модельный класс для Поручений
 * @author nsychev
 * @since 22.05.2018
 */
public class Task extends Document {

    /** Дата выдачи */
    private Date issueDate;

    /** Исполнитель (Person)*/
    private Person executor;

    /** Признак контрольности*/
    //todo уточнить

    /** контроллер порчения(Person)*/
    private Person inspector;

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Person getExecutor() {
        return executor;
    }

    public void setExecutor(Person executor) {
        this.executor = executor;
    }

    public Person getInspector() {
        return inspector;
    }

    public void setInspector(Person inspector) {
        this.inspector = inspector;
    }
    @Override
    public String getStoreName() {
        return "Поручение";
    }
}
