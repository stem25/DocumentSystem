package model.document;

import model.staff.Person;

/**
 * Модельный класс для Исходящих документов
 * @author nsychev
 * @since 21.05.2018
 */
public class OutgoingDocument extends Document {

    /** Адресат(Person)*/
    private Person addressee;

    /** Способ доставки*/
    private String deliveryType;


    public Person getAddressee() {
        return addressee;
    }

    public void setAddressee(Person addressee) {
        this.addressee = addressee;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    @Override
    public String getStoreName() {
        return "Исходящий документ";
    }
}
