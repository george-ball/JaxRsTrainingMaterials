package com.example;

import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.File;

public class Main {

  public static void main(String[] args) throws Exception {

    Tomcat tomcat = new Tomcat();
    tomcat.setPort(9999);
    tomcat.getConnector();

    Context context = tomcat.addContext("/basic-service", new File(".").getAbsolutePath());

    ResourceConfig config = new ResourceConfig()
        .packages("com.example.api.resource");

    ServletContainer jerseyServlet = new ServletContainer(config);

    Wrapper wrapper = Tomcat.addServlet(
        context,
        "jersey-servlet",
        jerseyServlet
    );

    wrapper.setLoadOnStartup(1);
    context.addServletMappingDecoded("/api/*", "jersey-servlet");

    tomcat.start();
    System.out.println("Server started at http://localhost:9999/basic-service/api/myresource");
    tomcat.getServer().await();
  }
}