package factory;

import exception.DocumentExistsException;
import model.document.Document;
import model.document.OutgoingDocument;
import model.staff.Person;

import java.util.Date;
import java.util.Random;

public class OutgoingDocumentFactory extends Factory {
    @Override
    public Document create() throws DocumentExistsException {
        Integer personCount = Person.allInstance.size();
        Random random = new Random();
        OutgoingDocument document = new OutgoingDocument();
        Long registrationId = (long) (Math.random() * 300);
        if(!checkId(registrationId)){
            throw new DocumentExistsException();
        }
        document.setRegistrationNumber(registrationId);
        document.setAddressee(Person.allInstance.get(random.nextInt(personCount)));
        document.setDeliveryType("Способ доставки");
        document.setRegistrationDate(new Date(12222223335L));
        document.setAuthor(Person.allInstance.get(random.nextInt(personCount)));
        document.setName("Название поручения");
        document.setText("Текст поручения");
        document.save();
        return document;
    }
}
