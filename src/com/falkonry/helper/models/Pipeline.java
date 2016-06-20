package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class Pipeline {
  private String id;
  private String sourceId;
  private String name;
  private String tenant;
  private String eventbuffer;
  private String inputMeasurement;
  private String thingIdentifier;
  private String singleThingID;
  private String createdBy;
  private Long createTime;
  private String updatedBy;
  private Long updateTime;
  private List<Signal> inputList;
  private List<Assessment> assessmentList;
  private List<Publication> publicationList;
  private Interval interval;
  private Long earliestDataPoint;
  private Long latestDataPoint;
  private List<Object> modelRevisionList;
  private List<Object> outflowHistory;
  private String status;
  private String outflowStatus;
  private StatsMeasurementMap statsMeasurementMap;
  private String type;


  public String getOutflowStatus() {
    return outflowStatus;
  }

  public void setOutflowStatus(String outflowStatus) {
    this.outflowStatus = outflowStatus;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public Pipeline setName(String name) {
    this.name = name;
    return this;
  }

  public String getId(){
    return this.id;
  }

  public Pipeline setId(String id) {
    this.id = id;
    return this;
  }

  public String getSourceId() {
    return sourceId;
  }

  public Pipeline setSourceId(String sourceId) {
    this.sourceId = sourceId;
    return this;
  }

  @JsonProperty("tenant")
  public String getAccount() {
    return tenant;
  }

  @JsonProperty("tenant")
  public Pipeline setAccount(String account) {
    this.tenant = account;
    return this;
  }

  @JsonProperty("input")
  public String getEventbuffer() {
    return eventbuffer;
  }

  @JsonProperty("input")
  public Pipeline setEventbuffer(String eventbuffer) {
    this.eventbuffer = eventbuffer;
    return this;
  }

  public String getInputMeasurement() {
    return inputMeasurement;
  }

  public Pipeline setInputMeasurement(String inputMeasurement) {
    this.inputMeasurement = inputMeasurement;
    return this;
  }

  public String getThingIdentifier() {
    return thingIdentifier;
  }

  public Pipeline setThingIdentifier(String thingIdentifier) {
    this.thingIdentifier = thingIdentifier;
    return this;
  }

  @JsonProperty("singleThingID")
  public String getThingName() {
    return singleThingID;
  }

  @JsonProperty("singleThingID")
  public Pipeline setThingName(String singleThingID) {
    this.singleThingID = singleThingID;
    return this;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public Pipeline setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public Pipeline setCreateTime(Long createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public Pipeline setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
    return this;
  }

  public Long getUpdateTime() {
    return updateTime;
  }

  public Pipeline setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public List<Signal> getInputList() {
    return inputList;
  }

  public Pipeline setInputList(List<Signal> inputList) {
    this.inputList = inputList;
    return this;
  }

  public List<Assessment> getAssessmentList() {
    return assessmentList;
  }

  public Pipeline setAssessmentList(List<Assessment> assessmentList) {
    this.assessmentList = assessmentList;
    return this;
  }

  public List<Publication> getPublicationList() {
    return publicationList;
  }

  public Pipeline setPublicationList(List<Publication> publicationList) {
    this.publicationList = publicationList;
    return this;
  }

  public Interval getInterval() {
    return interval;
  }

  public Pipeline setInterval(Interval interval) {
    this.interval = interval;
    return this;
  }

  public Long getEarliestDataPoint() {
    return earliestDataPoint;
  }

  public Pipeline setEarliestDataPoint(Long earliestDataPoint) {
    this.earliestDataPoint = earliestDataPoint;
    return this;
  }

  public Long getLatestDataPoint() {
    return latestDataPoint;
  }

  public Pipeline setLatestDataPoint(Long latestDataPoint) {
    this.latestDataPoint = latestDataPoint;
    return this;
  }

  public List<Object> getModelRevisionList() {
    return modelRevisionList;
  }

  public Pipeline setModelRevisionList(List<Object> modelRevisionList) {
    this.modelRevisionList = modelRevisionList;
    return this;
  }

  public List<Object> getOutflowHistory() {
    return outflowHistory;
  }

  public Pipeline setOutflowHistory(List<Object> outflowHistory) {
    this.outflowHistory = outflowHistory;
    return this;
  }

  public StatsMeasurementMap getStatsMeasurementMap() {
    return statsMeasurementMap;
  }

  public void setStatsMeasurementMap(StatsMeasurementMap statsMeasurementMap) {
    this.statsMeasurementMap = statsMeasurementMap;
  }

  public String getType() {
    return type;
  }

  public Pipeline setType(String type) {
    this.type = type;
    return this;
  }
}
