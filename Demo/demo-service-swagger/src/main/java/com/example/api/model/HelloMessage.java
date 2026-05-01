package com.example.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

/**
 * Response bean for the /api/hello endpoint.
 *
 * JAXB annotations (javax.xml.bind.annotation.*) control XML marshalling.
 * Swagger @Schema annotations add field-level documentation to the
 * generated OpenAPI spec — they are documentation-only and do not
 * affect runtime behaviour.
 *
 * Example XML output:
 *
 *   <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
 *   <hello>
 *     <message>Hello, World! Greetings from JAX-RS on Tomcat 9.</message>
 *     <service>demo-service</service>
 *     <timestamp>2024-06-15T10:30:00.123</timestamp>
 *   </hello>
 */
@XmlRootElement(name = "hello")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "A greeting response from the demo service")
public class HelloMessage {

    @XmlElement
    @Schema(
        description = "The greeting message text",
        example     = "Hello, Alice! Greetings from JAX-RS on Tomcat 9."
    )
    private String message;

    @XmlElement
    @Schema(description = "The name of the service that produced this response",
            example     = "demo-service")
    private String service;

    @XmlElement
    @Schema(description = "ISO-8601 timestamp of when the response was generated",
            example     = "2024-06-15T10:30:00.123")
    private String timestamp;

    /** No-arg constructor required by JAXB. */
    public HelloMessage() {}

    public HelloMessage(String message, String service) {
        this.message   = message;
        this.service   = service;
        this.timestamp = LocalDateTime.now().toString();
    }

    public String getMessage()   { return message; }
    public String getService()   { return service; }
    public String getTimestamp() { return timestamp; }
}
