package factory;

import exception.DocumentExistsException;
import model.document.Document;
import model.document.IncomingDocument;
import model.document.OutgoingDocument;
import model.document.Task;
import model.staff.Person;

import java.util.Date;
import java.util.Random;

/**
 * Фабрика создания документов
 * @author nsychev
 * @since 22.05.2018
 */
public class DocumentFactory {
    /**Функция создания документов
     * @param clazz класс создаваемого документа
     * @return Document
     */
    public Document create(Class clazz) throws DocumentExistsException {
        if(clazz.isAssignableFrom(Task.class)){
           return new TaskFactory().create();
        } else if(clazz.isAssignableFrom(IncomingDocument.class)){
            return new IncomingDocumentFactory().create();
        } else if(clazz.isAssignableFrom(OutgoingDocument.class)){
            return new OutgoingDocumentFactory().create();
        } else {
            throw new IllegalArgumentException("Incorrect class");
        }
    }
}
