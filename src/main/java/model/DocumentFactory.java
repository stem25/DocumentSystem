package model;

import exception.DocumentExistsException;

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
    public Document createDocument(Class clazz){
        Document document;
        if(clazz.isAssignableFrom(Task.class)){
            Task task = new Task();
            task.setIssueDate(new Date(new Random().nextLong()));
            task.setExecutor(Person.getRandomPerson());
            task.setInspector(Person.getRandomPerson());
            document = task;
        } else if(clazz.isAssignableFrom(IncomingDocument.class)){
            IncomingDocument incomingDocument = new IncomingDocument();
            incomingDocument.setSender(Person.getRandomPerson());
            incomingDocument.setAddressee(Person.getRandomPerson());
            incomingDocument.setOutgoingNumber(new Random().nextLong());
            incomingDocument.setOutgoingRegistrationDate(new Date(new Random().nextLong()));
            document = incomingDocument;
        } else if(clazz.isAssignableFrom(OutgoingDocument.class)){
            OutgoingDocument outgoingDocument = new OutgoingDocument();
            outgoingDocument.setAddressee(Person.getRandomPerson());
            outgoingDocument.setDeliveryType("Способ доставки");
            document = outgoingDocument;
        } else {
            throw new IllegalArgumentException();
        }
        Long registrationId = (long) (Math.random() * 200);
        if(!checkId(registrationId)){
            throw new DocumentExistsException();
        }
        document.setRegistrationNumber(registrationId);
        document.setAuthor(Person.getRandomPerson());
        document.setRegistrationDate(new Date());
        document.setName("Название документа");
        document.setText("Текст документа");
        return document;
    }
    /**Функция проверки повторения регистрационого номера документа
     * @param id идентификатор для проверки
     * @return true если не повторяется, false если повторился
     */
    private boolean checkId(Long id){
        for(Document document1: Document.allInstance){
            if(document1.getRegistrationNumber() == id){
                return false;
            }
        }
        return true;
    }
}
