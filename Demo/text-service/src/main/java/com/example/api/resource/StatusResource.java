package com.example.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.time.LocalDateTime;

/**
 * System status endpoint.
 *
 * URI:  GET /api/status
 *
 * Returns a plain-text block of key=value lines — the standard
 * format used in this module for diagnostic and infrastructure
 * endpoints where the data is not a domain object.
 *
 * Demonstrates:
 *   - @Context UriInfo injection (returns the request URI)
 *   - Response.ok(String) with explicit TEXT_PLAIN type
 *   - String.format() for building structured plain text
 *
 * curl http://localhost:8080/text-service/api/status
 *
 * Expected response:
 *   service=text-service
 *   status=UP
 *   version=1.0
 *   timestamp=2024-06-15T10:30:00
 *   requestUri=http://localhost:8080/text-service/api/status
 */
@Path("/status")
@Produces(MediaType.TEXT_PLAIN)
public class StatusResource {

    @GET
    public Response status(@Context UriInfo uriInfo) {

        String body = String.join("\n",
            "service=text-service",
            "status=UP",
            "version=1.0",
            "timestamp=" + LocalDateTime.now(),
            "requestUri=" + uriInfo.getAbsolutePath()
        ) + "\n";

        // Response.ok() with an explicit TEXT_PLAIN type guarantees the
        // Content-Type header is set correctly even without @Produces.
        return Response
            .ok(body, MediaType.TEXT_PLAIN)
            .build();
    }
}
