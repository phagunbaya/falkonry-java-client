package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TimeObject {
  private String zone;
  private String format;
  private String identifier;

  public String getZone() {
    return this.zone;
  }

  public TimeObject setZone(String zone) {
    this.zone = zone;
    return this;
  }
  
  public String getFormat() {
    return this.format;
  }

  public TimeObject setFormat(String format) {
    this.format = format;
    return this;
  }
  
  public String getIdentifier() {
    return this.identifier;
  }

  public TimeObject setIdentifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

}