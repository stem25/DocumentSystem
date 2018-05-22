package model;

import java.util.ArrayList;
import java.util.List;
/**
 * Временное хранилище для персон
 * @author nsychev
 * @since 22.05.2018
 */
public class  Person {
    public static List<String> persons;
    static {
        persons = new ArrayList<>();
        persons.add("Иванов Иван Иванович");
        persons.add("Владимиров Владимир Владимирович");
        persons.add("Константинов Константин Константинович");
        persons.add("Алексеев Алексей Алексеевич");
        persons.add("Александров Александр Александрович");
    }

    /**
     * Функция возвращает случайную персону
     * @return имя персоны
     */
    public static String getRandomPerson(){
        int id = (int)(Math.random() * persons.size());
        return persons.get(id);
    }
}
