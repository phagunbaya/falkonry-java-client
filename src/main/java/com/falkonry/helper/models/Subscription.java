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

  public Subscription setTimeIdentifier(String timeIdentifier) {
    this.timeIdentifier = timeIdentifier;
    return this;
  }

  public String getTimeFormat() {
    return timeFormat;
  }

  public Subscription setTimeFormat(String timeFormat) {
    this.timeFormat = timeFormat;
    return this;
  }

  public String getSignalsTagField() {
    return signalsTagField;
  }

  public Subscription setSignalsTagField(String signalsTagField) {
    this.signalsTagField = signalsTagField;
    return this;
  }

  public String getSignalsDelimiter() {
    return signalsDelimiter;
  }

  public Subscription setSignalsDelimiter(String signalsDelimiter) {
    this.signalsDelimiter = signalsDelimiter;
    return this;
  }

  public String getSignalsLocation() {
    return signalsLocation;
  }

  public Subscription setSignalsLocation(String signalsLocation) {
    this.signalsLocation = signalsLocation;
    return this;
  }

  public String getValueColumn() {
    return valueColumn;
  }

  public Subscription setValueColumn(String valueColumn) {
    this.valueColumn = valueColumn;
    return this;
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

  @JsonProperty("isHistorian")
  public Subscription setHistorian(Boolean historian) {
    this.isHistorian = historian;
    return this;
  }

}
