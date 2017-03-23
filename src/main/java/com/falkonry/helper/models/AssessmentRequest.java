package com.falkonry.helper.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssessmentRequest {

    private String name;
    private String datastream;
    private String rate;

    public String getDatastream() {
        return datastream;
    }

    public void setDatastream(String datastream) {
        this.datastream = datastream;
    }

     @JsonProperty("rate")
    public String getAssessmentRate() {
        return rate;
    }
    
     @JsonProperty("rate")
    public void setAssessmentRate(String rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public AssessmentRequest setName(String name) {
        this.name = name;
        return this;
    }
}
