package com.example.api.resource;

import com.example.api.model.HelloMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
@Produces(MediaType.APPLICATION_XML)
@Tag(name = "Hello", description = "Greeting endpoint")
public class HelloResource {

    /**
     * GET /api/hello[?name=&lt;name&gt;]
     *
     * Returns an XML greeting addressed to the supplied name, or to
     * "World" if no name is provided.
     *
     * curl -H "Accept: application/xml" \
     *      "http://localhost:8080/demo-service/api/hello?name=Alice"
     */
    @GET
    @Operation(
        summary     = "Get a greeting",
        description = "Returns an XML greeting. Supply a name via the "
                    + "'name' query parameter to personalise the message."
    )
    @ApiResponse(
        responseCode = "200",
        description  = "Greeting returned successfully",
        content      = @Content(
            mediaType = MediaType.APPLICATION_XML,
            schema    = @Schema(implementation = HelloMessage.class)
        )
    )
    public Response greet(
            @Parameter(
                description = "Name to greet. Defaults to 'World' if omitted.",
                example     = "Alice"
            )
            @QueryParam("name") @DefaultValue("World") String name) {

        HelloMessage msg = new HelloMessage(
                "Hello, " + name + "! Greetings from JAX-RS on Tomcat 9.",
                "demo-service"
        );

        return Response.ok(msg).build();
    }
}
