import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exception.DocumentExistsException;
import factory.DocumentFactory;
import factory.Factory;
import factory.TaskFactory;
import model.*;
import model.document.Document;
import model.document.IncomingDocument;
import model.document.OutgoingDocument;
import model.document.Task;
import model.staff.Department;
import model.staff.Organization;
import model.staff.Person;
import model.staff.Staff;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Document> documents = new ArrayList<>();
        //Чтение из xml
        try {
            JAXBContext context = JAXBContext.newInstance(Organization.class, Department.class, Person.class, Wrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            List<Organization> organizationList = unmarshal("organizations.xml", unmarshaller);
            Person.allInstance = unmarshal("persons.xml", unmarshaller);
            List<Department> departmentList = unmarshal("departments.xml", unmarshaller);
        }catch (Exception e){
            e.printStackTrace();
        }
        DocumentFactory documentFactory = new DocumentFactory();
        Collections.sort(Person.allInstance);
        //Генерация документов разного типа
        for(int i=0; i< 5; i++){
            try {
                documents.add(documentFactory.create(Task.class));
                documents.add(documentFactory.create(IncomingDocument.class));
                documents.add(documentFactory.create(OutgoingDocument.class));
            } catch (DocumentExistsException e) {
                e.printStackTrace();
            }
        }
        //Путь к исполняемому jar
        String pathName = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        pathName = pathName.substring(1,pathName.lastIndexOf("/") );
        for(Person person: Person.allInstance){
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
            for(Document document: Document.allInstance){
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

    private static <T extends Staff> List<T> unmarshal(String xmlFileName, Unmarshaller unmarshaller) throws JAXBException{
        StreamSource streamSource = new StreamSource(Main.class.getResourceAsStream("/orgstructure_xml/"+xmlFileName));
        Wrapper<T> wrapper = (Wrapper<T>) unmarshaller.unmarshal(streamSource, Wrapper.class).getValue();
        return wrapper.getItems();
    }
}
