package com.example.api;

import io.swagger.v3.jaxrs2.SwaggerSerializers;
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import com.example.api.resource.HelloResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * JAX-RS application entry point.
 *
 * @ApplicationPath("/api") registers all resources under /api/*.
 *
 * This class explicitly lists all resource and provider classes via
 * getClasses() so Swagger knows exactly which classes to document.
 * If you use an empty Application body instead, Jersey scans the
 * classpath automatically but Swagger may not pick up all classes.
 *
 * URLs after deployment:
 *   http://localhost:8080/demo-service/api/hello       — the greeting endpoint
 *   http://localhost:8080/demo-service/api/openapi.json — OpenAPI 3.0 spec
 *   http://localhost:8080/demo-service/api/openapi.yaml — OpenAPI 3.0 spec (YAML)
 *   http://localhost:8080/demo-service/swagger/         — Swagger UI browser interface
 */
@ApplicationPath("/api")
public class DemoApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(

            // ── Resource classes ──────────────────────────────────────
            HelloResource.class,

            // ── Swagger / OpenAPI 3.0 ─────────────────────────────────
            // OpenApiResource:              serves GET /api/openapi.json
            //                               and  GET /api/openapi.yaml
            // AcceptHeaderOpenApiResource:  same, negotiated via Accept header
            // SwaggerSerializers:           enables JSON/YAML serialisation
            //                               of the OpenAPI model object
            OpenApiResource.class,
            AcceptHeaderOpenApiResource.class,
            SwaggerSerializers.class
        );
    }
}
