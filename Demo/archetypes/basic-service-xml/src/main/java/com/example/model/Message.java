package com.example.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
  public String text;

  public Message() {

  }

  public Message(String text) {

    this.text = text;

  }
}
