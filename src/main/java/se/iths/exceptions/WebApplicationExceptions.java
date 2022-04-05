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
}
