package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class HttpResponseFormat {
  private int statusCode;
  private String response;

    /**
     *
     * @return
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     *
     * @param statusCode
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     *
     * @return
     */
    public String getResponse() {
        return response;
    }

    /**
     *
     * @param response
     */
    public void setResponse(String response) {
        this.response = response;
    }

  
}
