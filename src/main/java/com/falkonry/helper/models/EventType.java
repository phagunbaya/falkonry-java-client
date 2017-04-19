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
public class EventType {
  private String type = "Samples";

    /**
     *
     * @return
     */
    public String getType() {
    return type;
  }

    /**
     *
     * @param type
     * @return
     */
    public EventType setType(String type) {
    this.type = type;
    return this;
  }
}