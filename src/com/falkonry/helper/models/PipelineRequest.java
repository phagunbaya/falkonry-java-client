package com.falkonry.helper.models;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by avais on 17/6/16.
 */
public class PipelineRequest {
  private String name;
  private String eventbuffer;
  private String thingIdentifier;
  private String singleThingID;
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

  @JsonProperty("singleThingID")
  public String getThingName() {
    return singleThingID;
  }

  @JsonProperty("singleThingID")
  public PipelineRequest setThingName(String singleThingID) {
    this.singleThingID = singleThingID;
    return this;
  }

  public String getThingIdentifier() {
    return thingIdentifier;
  }

  public PipelineRequest setThingIdentifier(String thingIdentifier) {
    this.thingIdentifier = thingIdentifier;
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
