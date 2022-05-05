package se.iths.exceptions;

import se.iths.entity.Student;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class WebApplicationExceptions {

    public static void sendEmailException() {
        throw new javax.ws.rs.WebApplicationException(Response
                .status(Response.Status.NOT_FOUND)
                .entity("This email exists already!")
                .type(MediaType.TEXT_PLAIN_TYPE).build());
    }

    public static void sendJsonEMailException(String email) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        throw new javax.ws.rs.WebApplicationException(Response
                .status(Response.Status.NOT_ACCEPTABLE)
                .entity(new ErrorMessage("The email " + email + " already exists!", LocalDateTime.now().format(formatter)))
                .type(MediaType.APPLICATION_JSON).build());
    }

    public static void sendJsonException(String name) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        throw new javax.ws.rs.WebApplicationException(Response
                .status(Response.Status.NOT_ACCEPTABLE)
                .entity(new ErrorMessage("The name " + name + " exists already!", LocalDateTime.now().format(formatter)))
                .type(MediaType.APPLICATION_JSON).build());
    }
}
