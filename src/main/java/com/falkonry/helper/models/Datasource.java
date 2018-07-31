package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2017-2018 Falkonry Inc
 * MIT Licensed
 */


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Datasource {
  private String type;
  private String protocol;
  private String host;
  private Long port;
  private String username;
  private String password;
  private String elementTemplateName;
  
    /**
     *
     * @return
     */
    public String getType() {
    return this.type;
  }

    /**
     *
     * @param type
     * @return
     */
    public Datasource setType(String type) {
    this.type = type;
    return this;
  }
  
    /**
     *
     * @return
     */
    public String getProtocol() {
    return this.protocol;
  }

    /**
     *
     * @param protocol
     * @return
     */
    public Datasource setProtocol(String protocol) {
    this.protocol = protocol;
    return this;
  }
  
    /**
     *
     * @return
     */
    public String gethost() {
    return this.host;
  }

    /**
     *
     * @param host
     * @return
     */
    public Datasource sethost(String host) {
    this.host = host;
    return this;
  }
  
    /**
     *
     * @return
     */
    public Long getPort() {
    return this.port;
  }

    /**
     *
     * @param port
     * @return
     */
    public Datasource setPort(Long port) {
    this.port = port;
    return this;
  }
  
    /**
     *
     * @return
     */
    public String getUsername() {
    return this.username;
  }

    /**
     *
     * @param username
     * @return
     */
    public Datasource setUsername(String username) {
    this.username = username;
    return this;
  }
  
    /**
     *
     * @return
     */
    public String getPassword() {
    return this.password;
  }

    /**
     *
     * @param password
     * @return
     */
    public Datasource setPassword(String password) {
    this.password = password;
    return this;
  }
  
    /**
     *
     * @return
     */
    public String getElementTemplateName() {
    return this.elementTemplateName;
  }

    /**
     *
     * @param elementTemplateName
     * @return
     */
    public Datasource setElementTemplateName(String elementTemplateName) {
    this.elementTemplateName = elementTemplateName;
    return this;
  }
  
  
}