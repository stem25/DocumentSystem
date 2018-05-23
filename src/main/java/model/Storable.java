package model;

import model.document.Document;

/**
 * Интерфейс сохранения документов
 * @author nsychev
 * @since 22.05.2018
 */
public interface Storable{
    /**Сохранение документа
     * @param document сохраняемый документ
     * @return id документа
     */
    Long save(Document document);
    /**Получение имени хранилища
     * @return имя хранилища
     */
    String getStoreName();
}
