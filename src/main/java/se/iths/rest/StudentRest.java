package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    StudentService studentService;

    @Path("create")
    @POST
    public Response createStudent(Student student) {
        studentService.create(student);
        return Response.ok(student).build();
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

}
