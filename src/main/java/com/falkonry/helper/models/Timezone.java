package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Timezone {
  private String zone;
  private int offset;

  public String getZone() {
    return this.zone;
  }

  public Timezone setZone(String zone) {
    this.zone = zone;
    return this;
  }

  public int getOffset() {
    return this.offset;
  }

  public Timezone setOffset(int offset) {
    this.offset = offset;
    return this;
  }
}