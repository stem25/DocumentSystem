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

public class OrganizationXmlService {

    public List<Organization> getList(){
        List<Organization> objects = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Organization.class, Department.class, Person.class, Wrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StreamSource streamSource = new StreamSource(OrganizationXmlService.class.getResourceAsStream("/orgstructure_xml/organizations.xml"));
            Wrapper<Organization> wrapper = (Wrapper<Organization>) unmarshaller.unmarshal(streamSource, Wrapper.class).getValue();
            objects = wrapper.getItems();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return objects;
    }

}
