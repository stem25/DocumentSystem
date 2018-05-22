package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Абстрактный класс для документов
 * @author nsychev
 * @since 21.05.2018
 */
public abstract class Document implements Comparable<Document>{

    /** Идентификатор*/
    private Long id;
    /** Название*/
    private String name;
    /** Текст*/
    private String text;
    /** Регистрационный номер*/
    private Long registrationNumber;
    /** Дата регистрации*/
    private Date registrationDate;
    /** Автор документа(Person)*/
    private String author;

    /** Автор документа(Person)*/
    public static List<Document> allInstance;
    static {
        allInstance = new ArrayList<>();
    }
    public Document(){
        allInstance.add(this);
    }

    @Override
    protected void finalize() throws Throwable {
        allInstance.remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int compareTo(Document o) {
        int dateCompare = (int)(o.registrationDate.getTime() - this.registrationDate.getTime());
        if(dateCompare == 0){
            return (int)(o.registrationNumber - this.registrationNumber);
        }else {
            return dateCompare;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(getId(), document.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", registrationNumber=" + registrationNumber +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
