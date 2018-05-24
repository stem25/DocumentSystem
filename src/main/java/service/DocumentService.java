package service;

import exception.DocumentExistsException;
import factory.DocumentFactory;
import model.document.Document;
import model.document.IncomingDocument;
import model.document.OutgoingDocument;
import model.document.Task;
import model.staff.Person;
import service.XmlService.PersonXmlService;

import java.util.ArrayList;
import java.util.List;

public class DocumentService {

    public List<Document> getDocumentList() throws DocumentExistsException{
        List<Document> documents = new ArrayList<>();
        DocumentFactory documentFactory = new DocumentFactory();
        //Генерация документов разного типа
        for(int i=0; i< 5; i++){
            documents.add(documentFactory.create(Task.class));
            documents.add(documentFactory.create(IncomingDocument.class));
            documents.add(documentFactory.create(OutgoingDocument.class));
        }
        return documents;
    }

    public List<Document> documentsByAuthor(Long id) throws DocumentExistsException{
        List<Document> documents = new ArrayList<>();
        for (Document document: getDocumentList()){
            if(document.getAuthor().getId().equals(id)){
                documents.add(document);
            }
        }
        return documents;
    }
}
