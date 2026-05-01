package com.example.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * OpenAPI 3.0 metadata for the demo service.
 *
 * Swagger scans the classpath for @OpenAPIDefinition and uses the
 * annotation to populate the Info and Servers sections of the spec.
 * No methods are needed — the annotation alone is sufficient.
 *
 * The values here appear in the Swagger UI header and in the
 * generated openapi.json / openapi.yaml documents.
 */
@OpenAPIDefinition(
    info = @Info(
        title       = "Demo Service API",
        version     = "1.0",
        description = "Minimal JAX-RS demo — Java 11 / Tomcat 9 / XML responses.",
        contact     = @Contact(
            name  = "Course Support",
            email = "support@example.com"
        )
    ),
    servers = {
        @Server(
            url         = "http://localhost:8080/demo-service",
            description = "Local Tomcat 9"
        )
    }
)
public class OpenApiConfig {
    // No methods needed.
    // Swagger discovers this class by scanning the classpath for @OpenAPIDefinition.
}
