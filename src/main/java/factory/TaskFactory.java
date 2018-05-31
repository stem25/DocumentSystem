package factory;

import exception.DocumentExistsException;
import model.document.Document;
import model.document.Task;
import service.XmlService.PersonXmlService;

import java.util.Date;
import java.util.Random;

public class TaskFactory extends Factory {
    @Override
    public Document create() throws DocumentExistsException {
        PersonXmlService personXmlService = new PersonXmlService();
        Integer personCount = personXmlService.getList().size();
        Random random = new Random();
        Task document = new Task();
        Long registrationId = (long) (Math.random() * 20000L);
        if(checkId(registrationId)){
            throw new DocumentExistsException();
        }
        document.setRegistrationNumber(registrationId);
        document.setIssueDate(new Date(new Random().nextLong()));
        document.setExecutor(personXmlService.getList().get(random.nextInt(personCount)));
        document.setInspector(personXmlService.getList().get(random.nextInt(personCount)));
        document.setAuthor(personXmlService.getList().get(random.nextInt(personCount)));
        document.setRegistrationDate(new Date(12222223335L));
        document.setName("Название поручения");
        document.setText("Текст поручения");
        document.save();
        return document;
    }
}
