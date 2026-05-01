package com.example.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS application entry point.
 *
 * @ApplicationPath("/api") roots all resources under /api/*.
 * The empty body triggers classpath scanning — Jersey discovers
 * classes annotated with @Path and @Provider automatically.
 *
 * Full base URL after deployment to Tomcat 9:
 *   http://localhost:8080/text-service/api/
 */
@ApplicationPath("/api")
public class TextApplication extends Application {
    // Empty body — Jersey scans for @Path and @Provider classes.
}
