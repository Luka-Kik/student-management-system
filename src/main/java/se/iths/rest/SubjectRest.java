package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exceptions.WebApplicationExceptions;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    SubjectService subjectService;

    @Inject
    public SubjectRest(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Path("create")
    @POST
    public Response createSubject(Subject subject) {

        if (nameExists(subjectService.getAll(), subject.getName())) {
            WebApplicationExceptions.sendJsonException(subject.getName());
            return null;
        } else {
            subjectService.create(subject);
            return Response.ok(subject).build();
        }
    }

    @Path("readAll")
    @GET
    public Response getAllSubjects() {
        List<Subject> foundSubjects = subjectService.getAll();
        return Response.ok(foundSubjects).build();
    }

    @Path("update")
    @PUT
    public Response updateSubject(Subject subject) {

        if (nameExists(subjectService.getAll(), subject.getName())) {
            WebApplicationExceptions.sendJsonException(subject.getName());
            return null;
        } else {
            subjectService.update(subject);
            return Response.ok(subject).build();
        }
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        subjectService.delete(id);
        return Response.noContent().build();
    }

    @Path("readbyname")
    @GET
    public List<Subject> findByName(@QueryParam("findbyname") String name) {
        return subjectService.findByName(name);
    }

    public boolean nameExists(List<Subject> foundSubjects, String nameValue) {
        boolean isPresent = false;

        for (Subject el : foundSubjects) {
            if (el.getName().contains(nameValue)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }
}
