package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class Interval {
  private String field;
  private String duration;

  public String getField() {
    return this.field;
  }

  public Interval setField(String field) {
    this.field = field;
    return this;
  }

  public String getDuration() {
    return this.duration;
  }

  public Interval setDuration(String duration) {
    this.duration = duration;
    return this;
  }
}