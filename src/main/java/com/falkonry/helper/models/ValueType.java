package com.falkonry.helper.models;
/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ValueType {
  private String type = "Numerical";

  public String getType() {
    return type;
  }

  public ValueType setType(String type) {
    this.type = type;
    return this;
  }
}