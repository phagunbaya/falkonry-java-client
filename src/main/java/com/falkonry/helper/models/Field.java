package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Field {
  private String entityIdentifier;
  private String entityName;
  private Signal signal;
  private TimeObject time;
  

  public String getEntityIdentifier() {
    return this.entityIdentifier;
  }

  public Field setEntityIdentifier(String entityIdentifier) {
    this.entityIdentifier = entityIdentifier;
    return this;
  }
  
  public String getEntityName() {
    return this.entityName;
  }

  public Field setEntityName(String entityName) {
    this.entityName = entityName;
    return this;
  }
  
  public Signal getSignal() {
    return signal;
  }

  public Field setSiganl(Signal signal) {
    this.signal = signal;
    return this;
  }
  
  public TimeObject getTime() {
    return time;
  }

  public Field setTime(TimeObject time) {
    this.time = time;
    return this;
  }

}