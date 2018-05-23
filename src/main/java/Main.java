import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import factory.DocumentFactory;
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
            List<Person> personList = unmarshal("persons.xml", unmarshaller);
            List<Department> departmentList = unmarshal("departments.xml", unmarshaller);
        }catch (Exception e){
            e.printStackTrace();
        }
        DocumentFactory documentFactory = new DocumentFactory();
        Collections.sort(Person.allInstance);
        for(int i=0; i< 5; i++){
            documents.add(documentFactory.createDocument(Task.class));
            documents.add(documentFactory.createDocument(IncomingDocument.class));
            documents.add(documentFactory.createDocument(OutgoingDocument.class));
        }
        //Сортировка списка документов
        //Путь к исполняемому jar
        String pathName = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        pathName = pathName.substring(1,pathName.lastIndexOf("/") );
        for(Person person: Person.allInstance){
//            System.out.println(person.getShortname());
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

                if(document.getAuthor().getId().equals(person.getId())){
                    personDocuments.add(document);
//                  System.out.format("\t -%s №%d от %tD. %s \n", document.getClass().getSimpleName(), document.getRegistrationNumber(), document.getRegistrationDate(), document.getName());
                }
            }
            Collections.sort(personDocuments);
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
