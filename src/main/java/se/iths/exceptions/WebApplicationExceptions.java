package se.iths.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class WebApplicationExceptions {
    public static void sendEmailException() {
        throw new javax.ws.rs.WebApplicationException(Response
                .status(Response.Status.NOT_FOUND)
                .entity("This email exists already!")
                .type(MediaType.TEXT_PLAIN_TYPE).build());
    }

    public static void sendJsonException(String name) {
        throw new javax.ws.rs.WebApplicationException(Response
                .status(Response.Status.NOT_ACCEPTABLE)
                .entity("The name " + name + " exists already!")
                .type(MediaType.APPLICATION_JSON).build());
    }

    public static void sendJsonEMailException(String email) {
        throw new javax.ws.rs.WebApplicationException(Response
                .status(Response.Status.NOT_ACCEPTABLE)
                .entity("The email " + email + " already exists!")
                .type(MediaType.APPLICATION_JSON).build());
    }
}
