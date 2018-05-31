package controller;

import dao.PersonDao;
import model.document.Document;
import model.document.IncomingDocument;
import model.document.OutgoingDocument;
import model.document.Task;
import service.DocumentService;

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

    private final String indexFileName = "/index.jsp";

    @GET
    public void persons(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        PersonDao personDao = new PersonDao();
        request.setAttribute("page", "persons");
        request.setAttribute("persons", personDao.list());
        request.getRequestDispatcher(indexFileName).forward(request, response);

    }

    @GET
    @Path("{id}")
    public void documentsByAuthor(@Context HttpServletRequest request, @Context HttpServletResponse response, @PathParam("id") Long id) throws ServletException, IOException {
        DocumentService documentService = new DocumentService();
        request.setAttribute("page", "documentsByAuthor");
        request.setAttribute("documents", documentService.documentsByAuthor(id));
        request.getRequestDispatcher(indexFileName).forward(request, response);

    }

    @GET
    @Path("document/{id}")
    public void document(@Context HttpServletRequest request, @Context HttpServletResponse response, @PathParam("id") Long id) throws ServletException, IOException{
        DocumentService documentService = new DocumentService();
        Document document = documentService.read(id);
        if(document.getClass().isAssignableFrom(Task.class)){
            request.setAttribute("page", "task");
            request.setAttribute("document", (Task)document);
        }else if(document.getClass().isAssignableFrom(IncomingDocument.class)){
            IncomingDocument incomingDocument = (IncomingDocument)document;
            request.setAttribute("page", "incoming");
            request.setAttribute("document", incomingDocument);
        }else if(document.getClass().isAssignableFrom(OutgoingDocument.class)){
            request.setAttribute("page", "outgoing");
            request.setAttribute("document", (OutgoingDocument)document);
        }
        request.getRequestDispatcher(indexFileName).forward(request, response);

    }
}
