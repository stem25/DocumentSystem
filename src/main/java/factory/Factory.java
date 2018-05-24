package factory;

import exception.DocumentExistsException;
import model.document.Document;

public abstract class Factory {
    /**Абстрактная функция создания экземпляра документа
     * @return созданую реализацию Document
     * @throws DocumentExistsException
     */
    public abstract Document create() throws DocumentExistsException;

    /**Функция проверки повторения регистрационого номера документа
     * @param id идентификатор для проверки
     * @return true если не повторяется, false если повторился
     */
    boolean checkId(Long id){
        for(Document document: Document.allInstance){
            if(document.getRegistrationNumber() != null) {
                if (document.getRegistrationNumber().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }
}
