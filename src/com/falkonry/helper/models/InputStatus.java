package com.falkonry.helper.models;

import org.codehaus.jackson.annotate.JsonProperty;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

public class InputStatus {
  private String id;
  private String message;

  @JsonProperty("__$id")
  public String getId(){
    return this.id;
  }

  @JsonProperty("__$id")
  public InputStatus setId(String id) {
    this.id = id;
    return this;
  }

  public String getMessage(){
    return this.message;
  }

  public InputStatus setMessage(String message) {
    this.message = message;
    return this;
  }
}