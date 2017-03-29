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
  private Timezone timezone;

    /**
     *
     * @return
     */
    public String getId() {
    return id;
  }

    /**
     *
     * @param id
     * @return
     */
    public Eventbuffer setId(String id) {
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
    public Eventbuffer setSourceId(String sourceId) {
    this.sourceId = sourceId;
    return this;
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
    public Eventbuffer setName(String name) {
    this.name = name;
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
  public Eventbuffer setAccount(String account) {
    this.tenant = account;
    return this;
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
    public Eventbuffer setCreatedBy(String createdBy) {
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
    public Eventbuffer setCreateTime(Long createTime) {
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
    public Eventbuffer setUpdatedBy(String updatedBy) {
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
    public Eventbuffer setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
    return this;
  }

    /**
     *
     * @return
     */
    public List<Object> getSchemaList() {
    return schemaList;
  }

    /**
     *
     * @param schemaList
     * @return
     */
    public Eventbuffer setSchemaList(List<Object> schemaList) {
    this.schemaList = schemaList;
    return this;
  }

    /**
     *
     * @return
     */
    public List<Subscription> getSubscriptionList() {
    return subscriptionList;
  }

    /**
     *
     * @param subscriptionList
     * @return
     */
    public Eventbuffer setSubscriptionList(List<Subscription> subscriptionList) {
    this.subscriptionList = subscriptionList;
    return this;
  }

    /**
     *
     * @return
     */
    public Stats getStats() {
    return stats;
  }

    /**
     *
     * @param stats
     * @return
     */
    public Eventbuffer setStats(Stats stats)
  {
    this.stats = stats;
    return this;
  }

    /**
     *
     * @return
     */
    public String getTimeIdentifier() {
    return timeIdentifier;
  }

    /**
     *
     * @param timeIdentifier
     * @return
     */
    public Eventbuffer setTimeIdentifier(String timeIdentifier) {
    this.timeIdentifier = timeIdentifier;
    return this;
  }

    /**
     *
     * @return
     */
    public String getTimeFormat() {
    return timeFormat;
  }

    /**
     *
     * @param timeFormat
     * @return
     */
    public Eventbuffer setTimeFormat(String timeFormat) {
    this.timeFormat = timeFormat;
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
    public Eventbuffer setEntityIdentifier(String entityIdentifier) {
    this.entityIdentifier = entityIdentifier;
    return this;
  }

    /**
     *
     * @return
     */
    public String getEntityName() {
    return entityName;
  }

    /**
     *
     * @param entityName
     * @return
     */
    public Eventbuffer setEntityName(String entityName) {
    this.entityName = entityName;
    return this;
  }

    /**
     *
     * @return
     */
    public String getSignalsTagField() {
    return signalsTagField;
  }

    /**
     *
     * @param signalsTagField
     * @return
     */
    public Eventbuffer setSignalsTagField(String signalsTagField) {
    this.signalsTagField = signalsTagField;
    return this;
  }

    /**
     *
     * @return
     */
    public String getSignalsDelimiter() {
    return signalsDelimiter;
  }

    /**
     *
     * @param signalsDelimiter
     * @return
     */
    public Eventbuffer setSignalsDelimiter(String signalsDelimiter) {
    this.signalsDelimiter = signalsDelimiter;
    return this;
  }

    /**
     *
     * @return
     */
    public String getSignalsLocation() {
    return signalsLocation;
  }

    /**
     *
     * @param signalsLocation
     * @return
     */
    public Eventbuffer setSignalsLocation(String signalsLocation) {
    this.signalsLocation = signalsLocation;
    return this;
  }

    /**
     *
     * @return
     */
    public String getValueColumn() {
    return valueColumn;
  }

    /**
     *
     * @param valueColumn
     * @return
     */
    public Eventbuffer setValueColumn(String valueColumn) {
    this.valueColumn = valueColumn;
    return this;
  }

    /**
     *
     * @return
     */
    public Timezone getTimezone() {
    return timezone;
  }

    /**
     *
     * @param timezone
     * @return
     */
    public Eventbuffer setTimezone(Timezone timezone) {
    this.timezone = timezone;
    return this;
  }
}
