package model;

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
    private String executor;

    /** Признак контрольности*/
    //todo уточнить

    /** контроллер порчения(Person)*/
    private String inspector;

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }
}
