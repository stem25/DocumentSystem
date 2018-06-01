package service.XmlService;

import model.Wrapper;
import model.staff.Department;
import model.staff.Organization;
import model.staff.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.util.List;

public class PersonXmlService {

    public List<Person> getList(){
        List<Person> objects = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class, Wrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StreamSource streamSource = new StreamSource(PersonXmlService.class.getResourceAsStream("/orgstructure_xml/persons.xml"));
            Wrapper<Person> wrapper = (Wrapper<Person>) unmarshaller.unmarshal(streamSource, Wrapper.class).getValue();
            objects = wrapper.getItems();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return objects;
    }

}
