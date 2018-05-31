package factory;

import exception.DocumentExistsException;
import model.document.Document;
import model.document.IncomingDocument;
import service.XmlService.PersonXmlService;

import java.util.Date;
import java.util.Random;

public class IncomingDocumentFactory extends Factory {
    @Override
    public Document create() throws DocumentExistsException {
        PersonXmlService personXmlService = new PersonXmlService();
        Integer personCount = personXmlService.getList().size();
        Random random = new Random();
        IncomingDocument document = new IncomingDocument();
        Long registrationId = (long) (Math.random() * 20000L);
        if(checkId(registrationId)){
            throw new DocumentExistsException();
        }
        document.setRegistrationNumber(registrationId);
        document.setSender(personXmlService.getList().get(random.nextInt(personCount)));
        document.setAddressee(personXmlService.getList().get(random.nextInt(personCount)));
        document.setOutgoingNumber(new Random().nextLong());
        document.setOutgoingRegistrationDate(new Date(new Random().nextLong()));
        document.setAuthor(personXmlService.getList().get(random.nextInt(personCount)));
        document.setRegistrationDate(new Date(12222223335L));
        document.setName("Название документа");
        document.setText("Текст документа");
        document.save();
        return document;
    }
}
