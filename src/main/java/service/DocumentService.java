package service;

import exception.DocumentExistsException;
import factory.DocumentFactory;
import model.document.Document;
import model.document.IncomingDocument;
import model.document.OutgoingDocument;
import model.document.Task;

import java.util.ArrayList;
import java.util.List;

/** Сервис работы с документами
 * @author nsychev
 */
public class DocumentService {

    /** Статическое хранилище документов*/
    private static List<Document> documents = null;

    /** Получение документа по id
     * @param id идентификатор документа
     * @return {@link Document}
     */
    public Document read(Long id){
        List<Document> documents = new ArrayList<>();
        for (Document document: DocumentService.documents){
            if(document.getId().equals(id)){
                return document;
            }
        }
        return null;
    }

    /** Генерирует список документов через {@link DocumentFactory}
     * @throws DocumentExistsException если документ с таким {@link Document#registrationNumber} уже существует
     */
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

    /** Возвращает список документов
     * @return {@link List<Document>}
     */
    public List<Document> getDocumentList() {
        return documents;
    }

    /** Получение документа по id автора
     * @param id идентификатор автора документа
     * @return {@link List<Document>}
     */
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
