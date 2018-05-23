package factory;

import exception.DocumentExistsException;
import model.document.Document;
import model.document.Task;
import model.staff.Person;

import java.util.Date;
import java.util.Random;

public class TaskFactory extends Factory {
    @Override
    public Document create() throws DocumentExistsException {
        Integer personCount = Person.allInstance.size();
        Random random = new Random();
        Task document = new Task();
        Long registrationId = (long) (Math.random() * 300);
        if(!checkId(registrationId)){
            throw new DocumentExistsException();
        }
        document.setRegistrationNumber(registrationId);
        document.setIssueDate(new Date(new Random().nextLong()));
        document.setExecutor(Person.allInstance.get(random.nextInt(personCount)));
        document.setInspector(Person.allInstance.get(random.nextInt(personCount)));
        document.setAuthor(Person.allInstance.get(random.nextInt(personCount)));
        document.setRegistrationDate(new Date(12222223335L));
        document.setName("Название поручения");
        document.setText("Текст поручения");
        document.save();
        return document;
    }
}
