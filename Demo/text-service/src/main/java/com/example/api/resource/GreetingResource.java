package com.example.api.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Simple greeting endpoint.
 *
 * URI:  GET /api/greet[?name=&lt;name&gt;]
 *
 * Returns TEXT_PLAIN.  JAX-RS's built-in String MessageBodyWriter handles
 * this — no JAXB, no extra dependency, no serialisation code needed.
 *
 * curl http://localhost:8080/text-service/api/greet
 * curl "http://localhost:8080/text-service/api/greet?name=Alice"
 */
@Path("/greet")
@Produces(MediaType.TEXT_PLAIN)
public class GreetingResource {

    @GET
    public String greet(
            @QueryParam("name") @DefaultValue("World") String name) {

        return "Hello, " + name + "! Welcome to the plain-text service.\n";
    }
}
