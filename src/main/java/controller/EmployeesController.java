package controller;

import exception.DocumentExistsException;
import model.document.Document;
import model.staff.Department;
import model.staff.Organization;
import model.staff.Person;
import service.DocumentService;
import service.XmlService.DepartmentXmlService;
import service.XmlService.OrganizationXmlService;
import service.XmlService.PersonXmlService;

import javax.ws.rs.*;
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
    @Produces("application/json")
    public List<Document> documentsByAuthor(@PathParam("id") Long id){
        try {
            DocumentService documentService = new DocumentService();
            return documentService.documentsByAuthor(id);
        }catch (DocumentExistsException e){
            throw new BadRequestException(e);
        }

    }
}
