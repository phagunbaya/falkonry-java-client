package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 *
 * @author dev-falkonry-10
 */
@JsonIgnoreProperties(ignoreUnknown=true)

public class Pipeline {
  private String id;
  private String sourceId;
  private String name;
  private String tenant;
  private String eventbuffer;
  private String inputMeasurement;
  private String entityIdentifier;
  private String entityName;
  private String createdBy;
  private Long createTime;
  private String updatedBy;
  private Long updateTime;
  private List<Signal> inputList;
  private List<Assessment> assessmentList;
  private List<Publication> publicationList;
  private Long earliestDataPoint;
  private Long latestDataPoint;
  private List<Object> modelRevisionList;
  private List<Object> outflowHistory;
  private String status;
  private String outflowStatus;
  private StatsMeasurementMap statsMeasurementMap;
  private String type;

    /**
     *
     * @return
     */
    public String getOutflowStatus() {
    return outflowStatus;
  }

    /**
     *
     * @param outflowStatus
     */
    public void setOutflowStatus(String outflowStatus) {
    this.outflowStatus = outflowStatus;
  }

    /**
     *
     * @return
     */
    public String getStatus() {
    return status;
  }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
    this.status = status;
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
    public Pipeline setName(String name) {
    this.name = name;
    return this;
  }

    /**
     *
     * @return
     */
    public String getId(){
    return this.id;
  }

    /**
     *
     * @param id
     * @return
     */
    public Pipeline setId(String id) {
    this.id = id;
    return this;
  }

    /**
     *
     * @return
     */
    public String getSourceId() {
    return sourceId;
  }

    /**
     *
     * @param sourceId
     * @return
     */
    public Pipeline setSourceId(String sourceId) {
    this.sourceId = sourceId;
    return this;
  }

    /**
     *
     * @return
     */
    @JsonProperty("tenant")
  public String getAccount() {
    return tenant;
  }

    /**
     *
     * @param account
     * @return
     */
    @JsonProperty("tenant")
  public Pipeline setAccount(String account) {
    this.tenant = account;
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
  public Pipeline setEventbuffer(String eventbuffer) {
    this.eventbuffer = eventbuffer;
    return this;
  }

    /**
     *
     * @return
     */
    public String getInputMeasurement() {
    return inputMeasurement;
  }

    /**
     *
     * @param inputMeasurement
     * @return
     */
    public Pipeline setInputMeasurement(String inputMeasurement) {
    this.inputMeasurement = inputMeasurement;
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
     * @return
     */
    @JsonProperty("entityName")
  public String getEntityName() {
    return entityName;
  }

    /**
     *
     * @return
     */
    public String getCreatedBy() {
    return createdBy;
  }

    /**
     *
     * @param createdBy
     * @return
     */
    public Pipeline setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

    /**
     *
     * @return
     */
    public Long getCreateTime() {
    return createTime;
  }

    /**
     *
     * @param createTime
     * @return
     */
    public Pipeline setCreateTime(Long createTime) {
    this.createTime = createTime;
    return this;
  }

    /**
     *
     * @return
     */
    public String getUpdatedBy() {
    return updatedBy;
  }

    /**
     *
     * @param updatedBy
     * @return
     */
    public Pipeline setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
    return this;
  }

    /**
     *
     * @return
     */
    public Long getUpdateTime() {
    return updateTime;
  }

    /**
     *
     * @param updateTime
     * @return
     */
    public Pipeline setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
    return this;
  }

    /**
     *
     * @return
     */
    public List<Signal> getInputList() {
    return inputList;
  }

    /**
     *
     * @param inputList
     * @return
     */
    public Pipeline setInputList(List<Signal> inputList) {
    this.inputList = inputList;
    return this;
  }

    /**
     *
     * @return
     */
    public List<Assessment> getAssessmentList() {
    return assessmentList;
  }

    /**
     *
     * @param assessmentList
     * @return
     */
    public Pipeline setAssessmentList(List<Assessment> assessmentList) {
    this.assessmentList = assessmentList;
    return this;
  }

    /**
     *
     * @return
     */
    public List<Publication> getPublicationList() {
    return publicationList;
  }

    /**
     *
     * @param publicationList
     * @return
     */
    public Pipeline setPublicationList(List<Publication> publicationList) {
    this.publicationList = publicationList;
    return this;
  }

    /**
     *
     * @return
     */
    public Long getEarliestDataPoint() {
    return earliestDataPoint;
  }

    /**
     *
     * @param earliestDataPoint
     * @return
     */
    public Pipeline setEarliestDataPoint(Long earliestDataPoint) {
    this.earliestDataPoint = earliestDataPoint;
    return this;
  }

    /**
     *
     * @return
     */
    public Long getLatestDataPoint() {
    return latestDataPoint;
  }

    /**
     *
     * @param latestDataPoint
     * @return
     */
    public Pipeline setLatestDataPoint(Long latestDataPoint) {
    this.latestDataPoint = latestDataPoint;
    return this;
  }

    /**
     *
     * @return
     */
    public List<Object> getModelRevisionList() {
    return modelRevisionList;
  }

    /**
     *
     * @param modelRevisionList
     * @return
     */
    public Pipeline setModelRevisionList(List<Object> modelRevisionList) {
    this.modelRevisionList = modelRevisionList;
    return this;
  }

    /**
     *
     * @return
     */
    public List<Object> getOutflowHistory() {
    return outflowHistory;
  }

    /**
     *
     * @param outflowHistory
     * @return
     */
    public Pipeline setOutflowHistory(List<Object> outflowHistory) {
    this.outflowHistory = outflowHistory;
    return this;
  }

    /**
     *
     * @return
     */
    public StatsMeasurementMap getStatsMeasurementMap() {
    return statsMeasurementMap;
  }

    /**
     *
     * @param statsMeasurementMap
     */
    public void setStatsMeasurementMap(StatsMeasurementMap statsMeasurementMap) {
    this.statsMeasurementMap = statsMeasurementMap;
  }

    /**
     *
     * @return
     */
    public String getType() {
    return type;
  }

    /**
     *
     * @param type
     * @return
     */
    public Pipeline setType(String type) {
    this.type = type;
    return this;
  }
}
