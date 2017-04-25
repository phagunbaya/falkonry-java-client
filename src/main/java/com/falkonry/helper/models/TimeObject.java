package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class TimeObject {
  private String zone;
  private String format;
  private String identifier;

    /**
     *
     * @return
     */
    public String getZone() {
    return this.zone;
  }

    /**
     *
     * @param zone
     * @return
     */
    public TimeObject setZone(String zone) {
    this.zone = zone;
    return this;
  }
  
    /**
     *
     * @return
     */
    public String getFormat() {
    return this.format;
  }

    /**
     *
     * @param format
     * @return
     */
    public TimeObject setFormat(String format) {
    this.format = format;
    return this;
  }
  
    /**
     *
     * @return
     */
    public String getIdentifier() {
    return this.identifier;
  }

    /**
     *
     * @param identifier
     * @return
     */
    public TimeObject setIdentifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

}