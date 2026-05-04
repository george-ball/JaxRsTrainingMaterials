package com.example.api.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Demo resource — a single endpoint that returns a greeting as XML.
 *
 * Base URI:  http://localhost:8080/demo-service/api/hello
 *
 * OpenAPI annotations (io.swagger.v3.oas.annotations.*) are documentation-only
 * and have no effect on runtime behaviour.  They enrich the generated
 * openapi.json with descriptions, parameter details, and response schemas
 * that appear in the Swagger UI.
 *
 * JAX-RS imports use javax.ws.rs.* — correct for JAX-RS 2.1 on Tomcat 9.
 * Do NOT use jakarta.ws.rs.*, which is for Tomcat 10 / Jakarta EE 9+ only.
 */
@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
@Tag(name = "Hello", description = "Greeting endpoint")
public class HelloResource {

    /**
     * GET /api/hello
     *
     * Returns an XML greeting to the world
     *
     * curl -H "Accept: application/xml" \
     *      "http://localhost:8080/demo-service/api/hello?name=Alice"
     */
    @GET
    @Operation(
        summary     = "Get a greeting",
        description = "Returns an XML greeting."
    )
    @ApiResponse(
        responseCode = "200",
        description  = "Greeting returned successfully",
        content      = @Content(
            mediaType = MediaType.TEXT_PLAIN
        )
    )
    public String greet() {
        return "Hello, world";
    }
}
