package consoleApp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exception.DocumentExistsException;

import model.document.Document;
import model.staff.Person;
import service.DocumentService;
import service.XmlService.PersonXmlService;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        DocumentService documentService = new DocumentService();
        List<Document> documents = new ArrayList<>();
        try {
            documents = documentService.getDocumentList();
        }catch (DocumentExistsException e){
            e.printStackTrace();
        }

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
            } catch (FileNotFoundException|UnsupportedEncodingException e){
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
                    document-> System.out.format("\t -%s №%d от %tD. %s \n", document.getStoreName(), document.getRegistrationNumber(), document.getRegistrationDate(), document.getName())
            );
            if(printWriter != null) {
                printWriter.append(gson.toJson(personDocuments));
                printWriter.close();
            }
        }

    }
}