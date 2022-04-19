package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.exceptions.WebApplicationExceptions;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    TeacherService teacherService;

    @Inject
    public TeacherRest(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Path("create")
    @POST
    public Response createTeacher(Teacher teacher) {

        if (emailExists(teacherService.getAll(), teacher.getEmail())) {
            WebApplicationExceptions.sendJsonEMailException(teacher.getEmail());
            return null;
        } else {
            teacherService.create(teacher);
            return Response.ok(teacher).build();
        }
    }

    @Path("readAll")
    @GET
    public Response getAllTeachers() {
        List<Teacher> foundTeachers = teacherService.getAll();
        return Response.ok(foundTeachers).build();
    }

    @Path("update")
    @PUT
    public Response updateTeacher(Teacher teacher) {

        if (emailExists(teacherService.getAll(), teacher.getEmail())) {
            WebApplicationExceptions.sendJsonEMailException(teacher.getEmail());
            return null;
        } else {
            teacherService.update(teacher);
            return Response.ok(teacher).build();
        }
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {
        teacherService.delete(id);
        return Response.noContent().build();
    }

    @Path("readbylastname")
    @GET
    public List<Teacher> findByLastname(@QueryParam("findbylastname") String lastName) {
        return teacherService.findByLastname(lastName);
    }

    public boolean emailExists(List<Teacher> foundTeachers, String emailValue) {
        boolean isPresent = false;

        for (Teacher el : foundTeachers) {
            if (el.getEmail().contains(emailValue)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }
}
