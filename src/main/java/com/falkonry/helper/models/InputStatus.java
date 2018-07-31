package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2017-2018 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class InputStatus {
  private String status;
  private int requestPending;
  private int requestCompleted;
  private String id;
  private String tenant;
  private Long createTime;
  private String eventBuffer;
  private String action;

    /**
     *
     * @return
     */
    public String getAction() {
    return action;
  }

    /**
     *
     * @param action
     */
    public void setAction(String action) {
    this.action = action;
  }

    /**
     *
     * @return
     */
    public String getEventBuffer() {

    return eventBuffer;
  }

    /**
     *
     * @param eventBuffer
     */
    public void setEventBuffer(String eventBuffer) {
    this.eventBuffer = eventBuffer;
  }

    /**
     *
     * @return
     */
    public String getStatus(){
    return this.status;
  }

    /**
     *
     * @param status
     * @return
     */
    public InputStatus setStatus(String status) {
    this.status = status;
    return this;
  }

    /**
     *
     * @return
     */
    public int getRequestPending() {
    return requestPending;
  }

    /**
     *
     * @param requestPending
     */
    public void setRequestPending(int requestPending) {
    this.requestPending = requestPending;
  }

    /**
     *
     * @return
     */
    public int getRequestCompleted() {
    return requestCompleted;
  }

    /**
     *
     * @param requestCompleted
     */
    public void setRequestCompleted(int requestCompleted) {
    this.requestCompleted = requestCompleted;
  }

    /**
     *
     * @return
     */
    @JsonProperty("__$id")
  public String getId(){
    return this.id;
  }

    /**
     *
     * @param id
     * @return
     */
    @JsonProperty("__$id")
  public InputStatus setId(String id) {
    this.id = id;
    return this;
  }

    /**
     *
     * @return
     */
    @JsonProperty("__$tenant")
  public String getTenant() {
    return tenant;
  }

    /**
     *
     * @param tenant
     */
    @JsonProperty("__$tenant")
  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

    /**
     *
     * @return
     */
    @JsonProperty("__$createTime")
  public Long getCreateTime() {
    return createTime;
  }

    /**
     *
     * @param createTime
     */
    @JsonProperty("__$createTime")
  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }
}