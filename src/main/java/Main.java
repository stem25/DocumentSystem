import model.*;

import java.text.SimpleDateFormat;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        DocumentFactory documentFactory = new DocumentFactory();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Collections.sort(Person.persons);
        Collections.sort(Document.allInstance);
        for(int i=0; i< 5; i++){
            documentFactory.createDocument(Task.class);
            documentFactory.createDocument(IncomingDocument.class);
            documentFactory.createDocument(OutgoingDocument.class);
        }

        for(String person: Person.persons){
            System.out.println(person);
            for(Document document: Document.allInstance){
                if(document.getAuthor().equals(person)){
                    System.out.format("\t -%s №%d от %tD. %s \n", document.getClass().getSimpleName(), document.getRegistrationNumber(), document.getRegistrationDate(), document.getName());
                }
            }
        }
    }
}
