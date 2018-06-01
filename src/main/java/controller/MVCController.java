package controller;

import dao.PersonDao;
import model.document.Document;
import model.document.IncomingDocument;
import model.document.OutgoingDocument;
import model.document.Task;
import model.staff.Person;
import service.DocumentService;
import service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import java.io.IOException;

@Path("/")
public class MVCController {

    @GET
    public void persons(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        PersonService service = new PersonService();
        request.setAttribute("page", "persons");
        request.setAttribute("persons", service.list(null));
        request.getRequestDispatcher("/jsp/table_person.jsp").forward(request, response);

    }

    @GET
    @Path("{id}")
    public void documentsByAuthor(@Context HttpServletRequest request, @Context HttpServletResponse response, @PathParam("id") Long id) throws ServletException, IOException {
        DocumentService documentService = new DocumentService();
        request.setAttribute("page", "documentsByAuthor");
        request.setAttribute("documents", documentService.documentsByAuthor(id));
        request.getRequestDispatcher("/jsp/table_documents.jsp").forward(request, response);

    }

    @GET
    @Path("document/{id}")
    public void document(@Context HttpServletRequest request, @Context HttpServletResponse response, @PathParam("id") Long id) throws ServletException, IOException{
        DocumentService documentService = new DocumentService();
        Document document = documentService.read(id);
        if(document.getClass().isAssignableFrom(Task.class)){
            request.setAttribute("document", document);
            request.getRequestDispatcher("/jsp/task_card.jsp").forward(request, response);
        }else if(document.getClass().isAssignableFrom(IncomingDocument.class)){
            request.setAttribute("document", document);
            request.getRequestDispatcher("/jsp/incoming_card.jsp").forward(request, response);
        }else if(document.getClass().isAssignableFrom(OutgoingDocument.class)){
            request.setAttribute("document", document);
            request.getRequestDispatcher("/jsp/outgoing_card.jsp").forward(request, response);
        }
    }
    @GET
    @Path("person/{id}")
    public void person(@Context HttpServletRequest request, @Context HttpServletResponse response, @PathParam("id") Long id) throws ServletException, IOException {
        PersonService service = new PersonService();
        Person person = service.read(id);
        request.setAttribute("person", person);
        request.getRequestDispatcher("/jsp/person_card.jsp").forward(request, response);
    }
}
