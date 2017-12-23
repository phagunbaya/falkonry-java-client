package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Tracker {
  private String id;
  private String tenant;
  private String status;
  private String action;
  private String datastream;
  private String user;
  private String dataSource;
  private String message;
  private long createTime;

  /**
   *
   * @return
   */
  @JsonProperty("__$id")
  public String getId() {
    return id;
  }

  /**
   *
   * @param id
   */
  @JsonProperty("__$id")
  public void setId(String id) {
    this.id = id;
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
  public long getCreateTime() {
    return createTime;
  }

  /**
   *
   * @param createTime
   */
  @JsonProperty("__$createTime")
  public void setCreateTime(long createTime) {
    this.createTime = createTime;
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
  public String getDatastream() {
    return datastream;
  }

  /**
   *
   * @param datastream
   */
  public void setDatastream(String datastream) {
    this.datastream = datastream;
  }

  /**
   *
   * @return
   */
  public String getUser() {
    return user;
  }

  /**
   *
   * @param user
   */
  public void setUser(String user) {
    this.user = user;
  }

  /**
   *
   * @return
   */
  public String getDataSource() {
    return dataSource;
  }

  /**
   *
   * @param dataSource
   */
  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
  }

  /**
   *
   * @return
   */
  public String getMessage() {
    return message;
  }

  /**
   *
   * @param message
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
