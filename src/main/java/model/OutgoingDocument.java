package model;

/**
 * Модельный класс для Исходящих документов
 * @author nsychev
 * @since 21.05.2018
 */
public class OutgoingDocument extends Document {

    /** Адресат(Person)*/
    private String addressee;

    /** Способ доставки*/
    private String deliveryType;


    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }
}
