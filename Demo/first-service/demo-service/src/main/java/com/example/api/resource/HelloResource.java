package com.example.api.resource;

import com.example.api.model.HelloMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Demo resource — a single endpoint that returns a greeting as XML.
 * <p>
 * Base URI:  http://localhost:8080/demo-service/api/hello
 * <p>
 * Methods:
 * <p>
 * GET /api/hello
 * Returns XML greeting with the default message.
 * curl -H "Accept: application/xml" http://localhost:8080/demo-service/api/hello
 * <p>
 * GET /api/hello?name=Alice
 * Returns XML greeting addressed to the supplied name.
 * curl -H "Accept: application/xml" \
 * "http://localhost:8080/demo-service/api/hello?name=Alice"
 * <p>
 * All imports are from javax.ws.rs.* — the correct namespace for
 * JAX-RS 2.1 on Java 11 / Tomcat 9.  Do NOT use jakarta.ws.rs.*,
 * which is for Tomcat 10 / Jakarta EE 9+ only.
 */
@Api(value = "Hello")
@Path("/hello")
@Produces(MediaType.APPLICATION_XML)    // all methods return XML by default
public class HelloResource {

  /**
   * GET /api/hello[?name=&lt;name&gt;]
   *
   * @param name Optional query parameter.  Defaults to "World".
   * @return 200 OK with an XML-marshalled HelloMessage body.
   */
  @GET
  @ApiOperation(value = "Say Hello")
  public Response greet(
      @QueryParam("name") @DefaultValue("World") String name) {

    HelloMessage msg = new HelloMessage(
        "Hello, " + name + "! Greetings from JAX-RS on Tomcat 9.",
        "demo-service"
    );

    // Response.ok() sets status 200.
    // Jersey's built-in JAXB provider marshals HelloMessage to XML
    // because HelloMessage carries @XmlRootElement and this method
    // is annotated @Produces(APPLICATION_XML).
    return Response.ok(msg).build();
  }


}
