package consoleApp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exception.DocumentExistsException;
import model.document.Document;
import model.document.Task;
import model.staff.Person;
import service.DocumentService;
import service.XmlService.PersonXmlService;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Консольное приложение
 */
public class Main {

    /**
     * main функция консольного приложения
     * Произовдит создание {@link Document} через {@link DocumentService}
     * Считывает {@link Person} из xml
     * Сортирует и выводит в консоль список персон и документы в которых персона является автором
     * Вывод отчетности по каждой персоне в отдельный json файл("ФИО".json).
     * @param args аргументы при запуске
     */
    public static void main(String[] args){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        DocumentService documentService = new DocumentService();
        List<Document> documents = null;
        try {
            documentService.generateDocuments();
        } catch (DocumentExistsException e) {
            e.printStackTrace();
        }
        documents = documentService.getDocumentList();

        PersonXmlService xmlService = new PersonXmlService();
        List<Person> people = xmlService.getList();
        //Путь к исполняемому jar
        String pathName = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        pathName = pathName.substring(1,pathName.lastIndexOf("/") );
        Collections.sort(people);
        for(Person person: people){
            System.out.println(person.getShortname());
            //путь + имя файла
            String filename = pathName + "/" + person.getShortname().trim() + ".json";
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(filename, "UTF-8");
            } catch (FileNotFoundException |UnsupportedEncodingException e){
                e.printStackTrace();
            }
            List<Document> personDocuments = new ArrayList<>();
            for(Document document: documents){
                if(document.getAuthor() != null) {
                    if (document.getAuthor().getId().equals(person.getId())) {
                        personDocuments.add(document);
                    }
                }
            }
            //Сортировка списка документов
            Collections.sort(personDocuments);
            personDocuments.forEach(
                    document-> System.out.format("\t -%s №%d от %tD. %s %s\n", document.getStoreName(), document.getRegistrationNumber(), document.getRegistrationDate(), document.getName(), document.getAuthor().getShortname())
            );
            if(printWriter != null) {
                printWriter.append(gson.toJson(personDocuments));
                printWriter.close();
            }
        }
    }
}
