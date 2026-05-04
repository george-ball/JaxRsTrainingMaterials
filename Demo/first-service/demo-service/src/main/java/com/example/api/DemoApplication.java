package com.example.api;

import com.example.api.resource.HealthResource;
import com.example.api.resource.HelloResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * JAX-RS application entry point.
 *
 * @ApplicationPath("/api") registers all resources under /api/*.
 * The empty body tells Jersey to scan the classpath for classes
 * annotated with @Path and @Provider — no manual registration needed.
 *
 * Full base URL after deployment:
 *   http://localhost:8080/demo-service/api/
 */
@ApplicationPath("/api")
public class DemoApplication extends Application {

  public DemoApplication() {
    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setTitle("Demo Service API");
    beanConfig.setVersion("1.0.0");
    beanConfig.setSchemes(new String[]{"http"});
    beanConfig.setBasePath("/demo-service/api");
    beanConfig.setResourcePackage("com.example.api.resource");
    beanConfig.setScan(true);
  }

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<>();

    // Your JAX-RS resources
    classes.add(HelloResource.class);
    classes.add(HealthResource.class);

    // Swagger resources
    classes.add(ApiListingResource.class);
    classes.add(SwaggerSerializers.class);

    return classes;
  }

}
