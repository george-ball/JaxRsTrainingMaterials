package com.example.api.resource;

import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Parameter echo endpoint.
 *
 * URI:  GET /api/echo/{id}[;version=N][?filter=X]
 *       Headers: X-Request-ID
 *       Cookies: THEME
 *
 * Reads one parameter from every injection source and returns all
 * values as plain-text key=value lines.  This is the standard
 * diagnostic pattern for testing that parameters are wired correctly
 * before building more complex business logic.
 *
 * Example call:
 *   curl -H "X-Request-ID: CORR-123" \
 *        -H "Cookie: THEME=dark" \
 *        "http://localhost:8080/text-service/api/echo/ITEM-42;version=2?filter=active"
 *
 * Expected response (Content-Type: text/plain):
 *   pathId=ITEM-42
 *   matrix.version=2
 *   query.filter=active
 *   header.X-Request-ID=CORR-123
 *   cookie.THEME=dark
 */
@Path("/echo/{id}")
@Produces(MediaType.TEXT_PLAIN)
public class EchoResource {

    @GET
    public String echo(
            @PathParam("id")                           String id,
            @MatrixParam("version")  @DefaultValue("1") String version,
            @QueryParam("filter")    @DefaultValue("(none)") String filter,
            @HeaderParam("X-Request-ID")               String requestId,
            @CookieParam("THEME")    @DefaultValue("light")  String theme) {

        return String.join("\n",
            "pathId="                + id,
            "matrix.version="       + version,
            "query.filter="         + filter,
            "header.X-Request-ID="  + (requestId != null ? requestId : "(absent)"),
            "cookie.THEME="         + theme
        ) + "\n";
    }
}
