package com.example.api.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Simple arithmetic endpoints.
 *
 * Demonstrates:
 *   - Returning primitive / numeric values as plain text
 *   - @PathParam type conversion (String → int)
 *   - Returning a descriptive plain-text error on bad input
 *   - Using Response.status(400) vs throwing WebApplicationException
 *
 * Endpoints:
 *
 *   GET /api/math/add?a=3&b=4
 *       curl "http://localhost:8080/text-service/api/math/add?a=3&b=4"
 *       → 7
 *
 *   GET /api/math/multiply/{a}/{b}
 *       curl http://localhost:8080/text-service/api/math/multiply/6/7
 *       → 42
 *
 *   GET /api/math/divide?a=10&b=0
 *       curl "http://localhost:8080/text-service/api/math/divide?a=10&b=0"
 *       → 400 error=Division by zero
 */
@Path("/math")
@Produces(MediaType.TEXT_PLAIN)
public class MathResource {

    // GET /api/math/add?a=3&b=4  →  "7"
    @GET
    @Path("add")
    public String add(
            @QueryParam("a") @DefaultValue("0") int a,
            @QueryParam("b") @DefaultValue("0") int b) {

        return String.valueOf(a + b) + "\n";
    }

    // GET /api/math/multiply/6/7  →  "42"
    @GET
    @Path("multiply/{a}/{b}")
    public String multiply(
            @PathParam("a") int a,
            @PathParam("b") int b) {

        return String.valueOf(a * b) + "\n";
    }

    // GET /api/math/divide?a=10&b=2  →  "5"
    // GET /api/math/divide?a=10&b=0  →  400 with plain-text error body
    @GET
    @Path("divide")
    public Response divide(
            @QueryParam("a") @DefaultValue("0") int a,
            @QueryParam("b") @DefaultValue("1") int b) {

        if (b == 0) {
            // Return a structured plain-text error; the HTTP status carries
            // the semantic meaning — no XML or JSON needed here.
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("error=Division by zero\nstatus=400\n")
                .build();
        }

        return Response.ok(String.valueOf(a / b) + "\n").build();
    }
}
