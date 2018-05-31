package controller;

import dao.PersonDao;
import model.document.Document;
import model.staff.Department;
import model.staff.Organization;
import model.staff.Person;
import service.DocumentService;
import service.XmlService.DepartmentXmlService;
import service.XmlService.OrganizationXmlService;
import service.XmlService.PersonXmlService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/employees")
public class EmployeesController {
    @GET
    @Produces("application/xml")
    public List<Person> personsList(){
        PersonXmlService xmlService = new PersonXmlService();
        return xmlService.getList();
    }

    @GET
    @Path("/organizations")
    @Produces("application/xml")
    public List<Organization> organiztionsList(){
        OrganizationXmlService xmlService = new OrganizationXmlService();
        return xmlService.getList();
    }

    @GET
    @Path("/departments")
    @Produces("application/xml")
    public List<Department> departmentsList(){
        DepartmentXmlService xmlService = new DepartmentXmlService();
        return xmlService.getList();
    }

    @GET
    @Path("{id}")
    @Produces("application/json; charset=UTF-8" )
    public List<Document> documentsByAuthor(@PathParam("id") Long id){
        DocumentService documentService = new DocumentService();
        return documentService.documentsByAuthor(id);
    }

    @GET
    @Path("/persons")
    @Produces("application/json; charset=UTF-8")
    public List<Person> personList(){
        PersonDao personDao = new PersonDao();
        return personDao.list();
    }

    @GET
    @Path("/persons/{id}")
    @Produces("application/json")
    public Person createList(@PathParam("id") Long id){
        PersonDao personDao = new PersonDao();
        Person person = new Person();
        person.setFirstName("helloController");
        return personDao.create(person);
    }
}
