package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exceptions.WebApplicationExceptions;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    StudentService studentService;

    @Inject
    public StudentRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @Path("create")
    @POST
    public Response createStudent(Student student) {

        if (emailExists(studentService.getAll(), student.getEmail())) {
            WebApplicationExceptions.sendEmailException();
            return null;
        } else {
            studentService.create(student);
            return Response.ok(student).build();
        }
    }

    @Path("read")
    @GET
    public Response getAllStudents() {
        List<Student> foundStudents = studentService.getAll();
        return Response.ok(foundStudents).build();
    }

    @Path("update")
    @PUT
    public Response updateStudent(Student student) {
        studentService.update(student);
        return Response.ok(student).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.delete(id);
        return Response.noContent().build();
    }

    @Path("readbylastname")
    @GET
    public List<Student> findByLastname(@QueryParam("findbylastname") String lastName) {
        return studentService.findByLastname(lastName);
    }

    public boolean emailExists(List<Student> foundStudents, String emailValue) {
        boolean isPresent = false;

        for (Student el : foundStudents) {
            if (el.getEmail().contains(emailValue)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }
}
