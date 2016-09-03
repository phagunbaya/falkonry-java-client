package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class Eventbuffer {
  private String id;
  private String sourceId;
  private String name;
  private String tenant;
  private String createdBy;
  private Long createTime;
  private String updatedBy;
  private Long updateTime;
  private List<Object> schemaList;
  private List<Subscription> subscriptionList;
  private Stats stats;
  private String timeIdentifier;
  private String timeFormat;
  private String entityName;
  private String entityIdentifier;
  private String signalsTagField;
  private String signalsDelimiter;
  private String signalsLocation;
  private String valueColumn;

  public String getId() {
    return id;
  }

  public Eventbuffer setId(String id) {
    this.id = id;
    return this;
  }

  public String getSourceId() {
    return sourceId;
  }

  public Eventbuffer setSourceId(String sourceId) {
    this.sourceId = sourceId;
    return this;
  }

  public String getName() {
    return name;
  }

  public Eventbuffer setName(String name) {
    this.name = name;
    return this;
  }

  @JsonProperty("tenant")
  public String getAccount() {
    return tenant;
  }

  @JsonProperty("tenant")
  public Eventbuffer setAccount(String account) {
    this.tenant = account;
    return this;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public Eventbuffer setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public Eventbuffer setCreateTime(Long createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public Eventbuffer setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
    return this;
  }

  public Long getUpdateTime() {
    return updateTime;
  }

  public Eventbuffer setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public List<Object> getSchemaList() {
    return schemaList;
  }

  public Eventbuffer setSchemaList(List<Object> schemaList) {
    this.schemaList = schemaList;
    return this;
  }

  public List<Subscription> getSubscriptionList() {
    return subscriptionList;
  }

  public Eventbuffer setSubscriptionList(List<Subscription> subscriptionList) {
    this.subscriptionList = subscriptionList;
    return this;
  }

  public Stats getStats() {
    return stats;
  }

  public Eventbuffer setStats(Stats stats)
  {
    this.stats = stats;
    return this;
  }

  public String getTimeIdentifier() {
    return timeIdentifier;
  }

  public Eventbuffer setTimeIdentifier(String timeIdentifier) {
    this.timeIdentifier = timeIdentifier;
    return this;
  }

  public String getTimeFormat() {
    return timeFormat;
  }

  public Eventbuffer setTimeFormat(String timeFormat) {
    this.timeFormat = timeFormat;
    return this;
  }

  public String getEntityIdentifier() {
    return entityIdentifier;
  }

  public Eventbuffer setEntityIdentifier(String entityIdentifier) {
    this.entityIdentifier = entityIdentifier;
    return this;
  }

  public String getEntityName() {
    return entityName;
  }

  public Eventbuffer setEntityName(String entityName) {
    this.entityName = entityName;
    return this;
  }


  public String getSignalsTagField() {
    return signalsTagField;
  }


  public Eventbuffer setSignalsTagField(String signalsTagField) {
    this.signalsTagField = signalsTagField;
    return this;
  }

  public String getSignalsDelimiter() {
    return signalsDelimiter;
  }

  public Eventbuffer setSignalsDelimiter(String signalsDelimiter) {
    this.signalsDelimiter = signalsDelimiter;
    return this;
  }

  public String getSignalsLocation() {
    return signalsLocation;
  }

  public Eventbuffer setSignalsLocation(String signalsLocation) {
    this.signalsLocation = signalsLocation;
    return this;
  }

  public String getValueColumn() {
    return valueColumn;
  }

  public Eventbuffer setValueColumn(String valueColumn) {
    this.valueColumn = valueColumn;
    return this;
  }
}
