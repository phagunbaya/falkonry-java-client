package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TrackerReponse {
  private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}