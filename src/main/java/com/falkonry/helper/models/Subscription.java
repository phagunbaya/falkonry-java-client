package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonProperty;

public class Subscription {
  private String key;
  private String type;
  private String topic;
  private String path;
  private String username;
  private String password;
  private String timeIdentifier;
  private String timeFormat;
  private String signalsTagField;
  private String signalsDelimiter;
  private String signalsLocation;
  private String valueColumn;
  private Boolean streaming;
  private Boolean isHistorian;
  private Boolean archived;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getType() {
    return type;
  }

  public Subscription setType(String type) {
    this.type = type;
    return this;
  }

  public String getTopic() {
    return topic;
  }

  public Subscription setTopic(String topic) {
    this.topic = topic;
    return this;
  }

  public String getPath() {
    return path;
  }

  public Subscription setPath(String path) {
    this.path = path;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public Subscription setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Subscription setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getTimeIdentifier() {
    return timeIdentifier;
  }

  public String getTimeFormat() {
    return timeFormat;
  }

  public String getSignalsTagField() {
    return signalsTagField;
  }

  public String getSignalsDelimiter() {
    return signalsDelimiter;
  }

  public String getSignalsLocation() {
    return signalsLocation;
  }

  public String getValueColumn() {
    return valueColumn;
  }

  public Boolean getStreaming() {
    return streaming;
  }

  public Subscription setStreaming(Boolean streaming) {
    this.streaming = streaming;
    return this;
  }

  @JsonProperty("isHistorian")
  public Boolean getHistorian() {
    return isHistorian;
  }

  public Boolean getArchived() {
    return archived;
  }

  @JsonProperty("isHistorian")
  public Subscription setHistorian(Boolean historian) {
    this.isHistorian = historian;
    return this;
  }

  public Subscription setArchived(Boolean archived) {
    this.archived = archived;
    return this;
  }
}
