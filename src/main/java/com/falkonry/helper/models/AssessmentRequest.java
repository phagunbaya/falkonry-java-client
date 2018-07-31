package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2017-2018 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssessmentRequest {

    private String name;
    private String datastream;
    private String rate;

    /**
     *
     * @return
     */
    public String getDatastream() {
        return datastream;
    }

    /**
     *
     * @param datastream
     */
    public void setDatastream(String datastream) {
        this.datastream = datastream;
    }

    /**
     *
     * @return
     */
    @JsonProperty("rate")
    public String getAssessmentRate() {
        return rate;
    }
    
    /**
     *
     * @param rate
     */
    @JsonProperty("rate")
    public void setAssessmentRate(String rate) {
        this.rate = rate;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * @return
     */
    public AssessmentRequest setName(String name) {
        this.name = name;
        return this;
    }
}
