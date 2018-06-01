package controller;

import dao.PersonDao;
import exception.DocumentExistsException;
import model.document.Document;
import model.staff.Person;
import service.DocumentService;
import service.XmlService.PersonXmlService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.List;

@ApplicationPath("ecm")
public class MyApplication extends Application {
    public MyApplication() throws DocumentExistsException {
        PersonDao personDao = new PersonDao();
        personDao.loadFromXmlToDB();
        DocumentService documentService = new DocumentService();
        documentService.generateDocuments();
    }
}
