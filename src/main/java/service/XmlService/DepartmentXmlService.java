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

public class DepartmentXmlService {

    public List<Department> getList(){
        List<Department> objects = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Organization.class, Department.class, Person.class, Wrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StreamSource streamSource = new StreamSource(DepartmentXmlService.class.getResourceAsStream("/orgstructure_xml/departments.xml"));
            Wrapper<Department> wrapper = (Wrapper<Department>) unmarshaller.unmarshal(streamSource, Wrapper.class).getValue();
            objects = wrapper.getItems();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return objects;
    }

}
