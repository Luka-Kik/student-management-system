package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.exceptions.WebApplicationExceptions;
import se.iths.service.StudentService;
import se.iths.service.SubjectService;

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
    SubjectService subjectService;

    @Inject
    public StudentRest(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
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

    @Path("readAll")
    @GET
    public Response getAllStudents() {
        List<Student> foundStudents = studentService.getAll();
        return Response.ok(foundStudents).build();
    }

    @Path("update")
    @PUT
    public Response updateStudent(Student student) {

        if (emailExists(studentService.getAll(), student.getEmail())) {
            WebApplicationExceptions.sendEmailException();
            return null;
        } else {
            studentService.update(student);
            return Response.ok(student).build();
        }
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

    @Path("addsubjecttostudent/{studentId}/{subjectId}")
    @PUT
    public Response addSubjectToStudent(@PathParam("studentId") Long studentId, @PathParam("subjectId") Long subjectId) {

        Student foundStudent = studentService.findById(studentId);
        Subject foundSubject = subjectService.findById(subjectId);

        foundStudent.addSubject(foundSubject);
        studentService.update(foundStudent);

        return Response.ok(foundStudent).build();
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
