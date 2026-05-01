package com.example.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

/**
 * Response bean for the /api/hello endpoint.
 *
 * The JAXB annotations (all from javax.xml.bind.annotation.*)
 * tell Jersey how to marshal this class to XML automatically
 * whenever a resource method declares @Produces(APPLICATION_XML).
 *
 * Example XML output:
 *
 *   <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
 *   <hello>
 *     <message>Hello from JAX-RS!</message>
 *     <service>demo-service</service>
 *     <timestamp>2024-06-15T10:30:00</timestamp>
 *   </hello>
 */
@XmlRootElement(name = "hello")          // names the root XML element
@XmlAccessorType(XmlAccessType.FIELD)    // bind fields directly (no getters needed)
public class HelloMessage {

    @XmlElement
    private String message;

    @XmlElement
    private String service;

    @XmlElement
    private String timestamp;

    /** No-arg constructor required by JAXB. */
    public HelloMessage() {}

    public HelloMessage(String message, String service) {
        this.message   = message;
        this.service   = service;
        this.timestamp = LocalDateTime.now().toString();
    }

    // Getters — used by application code, not by JAXB (FIELD access handles that)
    public String getMessage()   { return message; }
    public String getService()   { return service; }
    public String getTimestamp() { return timestamp; }
}
