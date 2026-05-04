package com.example;

import com.example.model.Message;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")

public class HelloResource {

  @GET
  @Produces(MediaType.APPLICATION_XML)
  public Message hello() {
    return new Message("Hello World");
  }

}