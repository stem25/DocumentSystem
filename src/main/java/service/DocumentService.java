package service;

import exception.DocumentExistsException;
import factory.DocumentFactory;
import model.document.Document;
import model.document.IncomingDocument;
import model.document.OutgoingDocument;
import model.document.Task;

import java.util.ArrayList;
import java.util.List;

public class DocumentService {

    private static List<Document> documents = null;

    public Document read(Long id){
        List<Document> documents = new ArrayList<>();
        for (Document document: DocumentService.documents){
            if(document.getId().equals(id)){
                return document;
            }
        }
        return null;
    }
    public void generateDocuments() throws DocumentExistsException{
        documents = new ArrayList<>();
        DocumentFactory documentFactory = new DocumentFactory();
        //Генерация документов разного типа
        for(int i=0; i< 15; i++){
            documents.add(documentFactory.create(Task.class));
            documents.add(documentFactory.create(IncomingDocument.class));
            documents.add(documentFactory.create(OutgoingDocument.class));
        }
    }

    public List<Document> getDocumentList() {
        return documents;
    }

    public List<Document> documentsByAuthor(Long id){
        List<Document> documents = new ArrayList<>();
        for (Document document: DocumentService.documents){
            if(document.getAuthor().getId().equals(id)){
                documents.add(document);
            }
        }
        return documents;
    }
}
