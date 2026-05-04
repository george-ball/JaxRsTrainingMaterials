package com.example.api.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api(value = "Health")
@Path("/health")
@Produces(MediaType.TEXT_PLAIN)    // all methods return XML by default
public class HealthResource {

    @GET
    @ApiOperation(value = "Check service health")
    public String health() {
      return "OK";
    }
  }