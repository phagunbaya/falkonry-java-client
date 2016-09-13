package com.falkonry.helper.models;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

public class PipelineRequest {
  private String name;
  private String eventbuffer;
  private String entityIdentifier;
  private String entityName;
  private List<SignalRequest> inputList;
  private List<AssessmentRequest> assessmentList;
  private Interval interval;

  public String getName() {
    return name;
  }

  public PipelineRequest setName(String name) {
    this.name = name;
    return this;
  }

  public Interval getInterval() {
    return interval;
  }

  public PipelineRequest setInterval(Interval interval) {
    this.interval = interval;
    return this;
  }

  public List<AssessmentRequest> getAssessmentList() {
    return assessmentList;
  }

  public PipelineRequest setAssessmentList(List<AssessmentRequest> assessmentList) {
    this.assessmentList = assessmentList;
    return this;
  }

  public List<SignalRequest> getInputList() {
    return inputList;
  }

  public PipelineRequest setInputList(List<SignalRequest> inputList) {
    this.inputList = inputList;
    return this;
  }

  @JsonProperty("entityName")
  public String getEntityName() {
    return entityName;
  }

  @JsonProperty("entityName")
  public PipelineRequest setEntityName(String entityName) {
    this.entityName = entityName;
    return this;
  }

  public String getEntityIdentifier() {
    return entityIdentifier;
  }

  public PipelineRequest setEntityIdentifier(String entityIdentifier) {
    this.entityIdentifier = entityIdentifier;
    return this;
  }

  @JsonProperty("input")
  public String getEventbuffer() {
    return eventbuffer;
  }

  @JsonProperty("input")
  public PipelineRequest setEventbuffer(String eventbuffer) {
    this.eventbuffer = eventbuffer;
    return this;
  }
}
