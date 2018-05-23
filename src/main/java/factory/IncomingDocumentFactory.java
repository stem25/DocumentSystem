package factory;

import exception.DocumentExistsException;
import model.document.Document;
import model.document.IncomingDocument;
import model.staff.Person;

import java.util.Date;
import java.util.Random;

public class IncomingDocumentFactory extends Factory {
    @Override
    public Document create() throws DocumentExistsException {
        Integer personCount = Person.allInstance.size();
        Random random = new Random();
        IncomingDocument document = new IncomingDocument();
        Long registrationId = (long) (Math.random() * 300);
        if(!checkId(registrationId)){
            throw new DocumentExistsException();
        }
        document.setRegistrationNumber(registrationId);
        document.setSender(Person.allInstance.get(random.nextInt(personCount)));
        document.setAddressee(Person.allInstance.get(random.nextInt(personCount)));
        document.setOutgoingNumber(new Random().nextLong());
        document.setOutgoingRegistrationDate(new Date(new Random().nextLong()));
        document.setAuthor(Person.allInstance.get(random.nextInt(personCount)));
        document.setRegistrationDate(new Date(12222223335L));
        document.setName("Название документа");
        document.setText("Текст документа");
        document.save();
        return document;
    }
}
