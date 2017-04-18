package com.falkonry.helper.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

/**
 *
 * @author dev-falkonry-10
 */


@JsonIgnoreProperties(ignoreUnknown=true)
public class PipelineRequest {
  private String name;
  private String eventbuffer;
  private String entityIdentifier;
  private String entityName;
  private List<SignalRequest> inputList;
  private List<AssessmentRequest> assessmentList;

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
    public PipelineRequest setName(String name) {
    this.name = name;
    return this;
  }

    /**
     *
     * @return
     */
    public List<AssessmentRequest> getAssessmentList() {
    return assessmentList;
  }

    /**
     *
     * @param assessmentList
     * @return
     */
    public PipelineRequest setAssessmentList(List<AssessmentRequest> assessmentList) {
    this.assessmentList = assessmentList;
    return this;
  }

    /**
     *
     * @return
     */
    public List<SignalRequest> getInputList() {
    return inputList;
  }

    /**
     *
     * @param inputList
     * @return
     */
    public PipelineRequest setInputList(List<SignalRequest> inputList) {
    this.inputList = inputList;
    return this;
  }

    /**
     *
     * @return
     */
    @JsonProperty("entityName")
  public String getEntityName() {
    return entityName;
  }

    /**
     *
     * @param entityName
     * @return
     */
    @JsonProperty("entityName")
  public PipelineRequest setEntityName(String entityName) {
    this.entityName = entityName;
    return this;
  }

    /**
     *
     * @return
     */
    public String getEntityIdentifier() {
    return entityIdentifier;
  }

    /**
     *
     * @param entityIdentifier
     * @return
     */
    public PipelineRequest setEntityIdentifier(String entityIdentifier) {
    this.entityIdentifier = entityIdentifier;
    return this;
  }

    /**
     *
     * @return
     */
    @JsonProperty("input")
  public String getEventbuffer() {
    return eventbuffer;
  }

    /**
     *
     * @param eventbuffer
     * @return
     */
    @JsonProperty("input")
  public PipelineRequest setEventbuffer(String eventbuffer) {
    this.eventbuffer = eventbuffer;
    return this;
  }
}
