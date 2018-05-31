package controller;

import dao.PersonDao;
import exception.DocumentExistsException;
import model.staff.Person;
import service.DocumentService;
import service.XmlService.PersonXmlService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.List;

@ApplicationPath("ecm")
public class MyApplication extends Application {
    public MyApplication(){
        PersonDao personDao = new PersonDao();
        PersonXmlService xmlService = new PersonXmlService();
        List<Person> personList = xmlService.getList();
        for (Person person: personList){
            personDao.create(person);
        }
        DocumentService documentService = new DocumentService();
        try {
            documentService.generateDocuments();
        } catch (DocumentExistsException e) {
            e.printStackTrace();
        }
    }
}
