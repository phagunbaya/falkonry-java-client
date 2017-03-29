package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author dev-falkonry-10
 */
@JsonIgnoreProperties(ignoreUnknown=true)
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
  private Boolean archived;

    /**
     *
     * @return
     */
    public String getKey() {
    return key;
  }

    /**
     *
     * @param key
     */
    public void setKey(String key) {
    this.key = key;
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
    public Subscription setType(String type) {
    this.type = type;
    return this;
  }

    /**
     *
     * @return
     */
    public String getTopic() {
    return topic;
  }

    /**
     *
     * @param topic
     * @return
     */
    public Subscription setTopic(String topic) {
    this.topic = topic;
    return this;
  }

    /**
     *
     * @return
     */
    public String getPath() {
    return path;
  }

    /**
     *
     * @param path
     * @return
     */
    public Subscription setPath(String path) {
    this.path = path;
    return this;
  }

    /**
     *
     * @return
     */
    public String getUsername() {
    return username;
  }

    /**
     *
     * @param username
     * @return
     */
    public Subscription setUsername(String username) {
    this.username = username;
    return this;
  }

    /**
     *
     * @return
     */
    public String getPassword() {
    return password;
  }

    /**
     *
     * @param password
     * @return
     */
    public Subscription setPassword(String password) {
    this.password = password;
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
     * @return
     */
    public String getTimeFormat() {
    return timeFormat;
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
     * @return
     */
    public String getSignalsDelimiter() {
    return signalsDelimiter;
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
     * @return
     */
    public String getValueColumn() {
    return valueColumn;
  }

    /**
     *
     * @return
     */
    public Boolean getStreaming() {
    return streaming;
  }

    /**
     *
     * @param streaming
     * @return
     */
    public Subscription setStreaming(Boolean streaming) {
    this.streaming = streaming;
    return this;
  }

    /**
     *
     * @return
     */
    public Boolean getArchived() {
    return archived;
  }

    /**
     *
     * @param archived
     * @return
     */
    public Subscription setArchived(Boolean archived) {
    this.archived = archived;
    return this;
  }
}
