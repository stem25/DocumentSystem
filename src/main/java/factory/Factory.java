package factory;

import exception.DocumentExistsException;
import model.document.Document;

public abstract class Factory {
    /**Абстрактная функция создания экземпляра документа
     * @return Document
     * @throws DocumentExistsException
     */
    public abstract Document create() throws DocumentExistsException;

    /**Функция проверки повторения регистрационого номера документа
     * @param id идентификатор для проверки
     * @return true если не повторяется, false если повторился
     */
    protected boolean checkId(Long id){
        for(Document document1: Document.allInstance){
            if(document1.getRegistrationNumber() == id){
                return false;
            }
        }
        return true;
    }
}
