package model;

import java.util.Date;
/**
 * Модельный класс для Входящих документов
 * @author nsychev
 * @since 21.05.2018
 */
public class IncomingDocument extends Document {

    /** Отправитель (Person)*/
    private String sender;

    /** Адресат (Person)*/
    private String addressee;

    /** Исходящий номер*/
    private Long outgoingNumber;

    /** Исходящая дата регистрации*/
    private Date outgoingRegistrationDate;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
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
}
