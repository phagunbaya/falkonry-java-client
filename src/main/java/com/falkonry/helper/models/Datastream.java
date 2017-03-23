package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Datastream {
  private String id;
  private String sourceId;
  private String name;
  private String tenant;
  private String createdBy;
  private Long createTime;
  private String updatedBy;
  private Long updateTime;
  private Stats stats;
  private Field field;
  private Datasource datasource;
  private List<Input> inputList;

  public String getId() {
    return id;
  }

  public Datastream setId(String id) {
    this.id = id;
    return this;
  }

  public String getSourceId() {
    return sourceId;
  }

  public Datastream setSourceId(String sourceId) {
    this.sourceId = sourceId;
    return this;
  }

  public String getName() {
    return name;
  }

  public Datastream setName(String name) {
    this.name = name;
    return this;
  }

  @JsonProperty("tenant")
  public String getAccount() {
    return tenant;
  }

  @JsonProperty("tenant")
  public Datastream setAccount(String account) {
    this.tenant = account;
    return this;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public Datastream setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public Datastream setCreateTime(Long createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public Datastream setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
    return this;
  }

  public Long getUpdateTime() {
    return updateTime;
  }

  public Datastream setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public Stats getStats() {
    return stats;
  }

  public Datastream setStats(Stats stats)
  {
    this.stats = stats;
    return this;
  }

  public Field getField() {
    return field;
  }

  public Datastream setField(Field field) {
    this.field = field;
    return this;
  }
  
  public Datasource getDatasource() {
    return datasource;
  }

  public Datastream setDatasource(Datasource datasource) {
    this.datasource = datasource;
    return this;
  }
  
  public List<Input> getInputList() {
    return inputList;
  }

  public Datastream setInputList(List<Input> inputList) {
    this.inputList = inputList;
    return this;
  }

}
