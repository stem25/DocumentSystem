package model.document;

import model.staff.Person;

import java.util.Date;
/**
 * Модельный класс для Входящих документов
 * @author nsychev
 * @since 21.05.2018
 */
public class IncomingDocument extends Document {

    /** Отправитель (Person)*/
    private Person sender;

    /** Адресат (Person)*/
    private Person addressee;

    /** Исходящий номер*/
    private Long outgoingNumber;

    /** Исходящая дата регистрации*/
    private Date outgoingRegistrationDate;

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getAddressee() {
        return addressee;
    }

    public void setAddressee(Person addressee) {
        this.addressee = addressee;
    }

    public Long getOutgoingNumber() {
        return outgoingNumber;
    }

    public void setOutgoingNumber(Long outgoingNumber) {
        this.outgoingNumber = outgoingNumber;
    }

    public Date getOutgoingRegistrationDate() {
        return outgoingRegistrationDate;
    }

    public void setOutgoingRegistrationDate(Date outgoingRegistrationDate) {
        this.outgoingRegistrationDate = outgoingRegistrationDate;
    }
    @Override
    public String getStoreName() {
        return "Входящий документ";
    }
}
