package com.falkonry.helper.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssessmentRequest {

    private String name;
    private String datastream;
    private String assessmentRate;

    public String getDatastream() {
        return datastream;
    }

    public void setDatastream(String datastream) {
        this.datastream = datastream;
    }

    public String getAssessmentRate() {
        return assessmentRate;
    }

    public void setAssessmentRate(String assessmentRate) {
        this.assessmentRate = assessmentRate;
    }

    public String getName() {
        return name;
    }

    public AssessmentRequest setName(String name) {
        this.name = name;
        return this;
    }
}
