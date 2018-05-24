package factory;

import exception.DocumentExistsException;
import model.document.Document;
import model.document.OutgoingDocument;
import model.staff.Person;
import service.XmlService.PersonXmlService;

import java.util.Date;
import java.util.Random;

public class OutgoingDocumentFactory extends Factory {
    @Override
    public Document create() throws DocumentExistsException {
        PersonXmlService personXmlService = new PersonXmlService();
        Integer personCount = personXmlService.getList().size();
        Random random = new Random();
        OutgoingDocument document = new OutgoingDocument();
        Long registrationId = (long) (Math.random() * Long.MAX_VALUE);
        if(checkId(registrationId)){
            throw new DocumentExistsException();
        }
        document.setRegistrationNumber(registrationId);
        document.setAddressee(personXmlService.getList().get(random.nextInt(personCount)));
        document.setDeliveryType("Способ доставки");
        document.setRegistrationDate(new Date(12222223335L));
        document.setAuthor(personXmlService.getList().get(random.nextInt(personCount)));
        document.setName("Название поручения");
        document.setText("Текст поручения");
        document.save();
        return document;
    }
}
