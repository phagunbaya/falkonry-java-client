package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Datasource {
  private String type;
  private String protocol;
  private String host;
  private Long port;
  private String username;
  private String password;
  private String elementTemplateName;
  

  public String getType() {
    return this.type;
  }

  public Datasource setType(String type) {
    this.type = type;
    return this;
  }
  
  public String getProtocol() {
    return this.protocol;
  }

  public Datasource setProtocol(String protocol) {
    this.protocol = protocol;
    return this;
  }
  
  public String gethost() {
    return this.host;
  }

  public Datasource sethost(String host) {
    this.host = host;
    return this;
  }
  
  public Long getPort() {
    return this.port;
  }

  public Datasource setPort(Long port) {
    this.port = port;
    return this;
  }
  
  public String getUsername() {
    return this.username;
  }

  public Datasource setUsername(String username) {
    this.username = username;
    return this;
  }
  
  public String getPassword() {
    return this.password;
  }

  public Datasource setPassword(String password) {
    this.password = password;
    return this;
  }
  
  public String getElementTemplateName() {
    return this.elementTemplateName;
  }

  public Datasource setElementTemplateName(String elementTemplateName) {
    this.elementTemplateName = elementTemplateName;
    return this;
  }
  
  
}