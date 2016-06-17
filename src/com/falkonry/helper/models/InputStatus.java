package com.falkonry.helper.models;

import org.codehaus.jackson.annotate.JsonProperty;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

public class InputStatus {
  private String status;
  private int requestPending;
  private int requestCompleted;
  private String id;
  private String tenant;
  private Long createTime;
  private String eventBuffer;
  private String action;

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getEventBuffer() {

    return eventBuffer;
  }

  public void setEventBuffer(String eventBuffer) {
    this.eventBuffer = eventBuffer;
  }

  public String getStatus(){
    return this.status;
  }

  public InputStatus setStatus(String status) {
    this.status = status;
    return this;
  }

  public int getRequestPending() {
    return requestPending;
  }

  public void setRequestPending(int requestPending) {
    this.requestPending = requestPending;
  }

  public int getRequestCompleted() {
    return requestCompleted;
  }

  public void setRequestCompleted(int requestCompleted) {
    this.requestCompleted = requestCompleted;
  }

  @JsonProperty("__$id")
  public String getId(){
    return this.id;
  }

  @JsonProperty("__$id")
  public InputStatus setId(String id) {
    this.id = id;
    return this;
  }

  @JsonProperty("__$tenant")
  public String getTenant() {
    return tenant;
  }

  @JsonProperty("__$tenant")
  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  @JsonProperty("__$createTime")
  public Long getCreateTime() {
    return createTime;
  }

  @JsonProperty("__$createTime")
  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }
}