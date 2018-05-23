package model;

import model.document.Document;

/**
 * Интерфейс сохранения документов
 * @author nsychev
 * @since 22.05.2018
 */
public interface Storable{
    /**Сохранение документа
     * @return id документа
     */
    Long save();

    /**Получение имени хранилища
     * @return имя хранилища
     */
    String getStoreName();
}
