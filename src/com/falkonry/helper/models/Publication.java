package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import java.util.Map;

public class Publication {
  private String key;
  private String type;
  private String topic;
  private String path;
  private String username;
  private String password;
  private String contentType;
  private Boolean streaming;
  private Map<String, String> headers;

  public String getKey() {
    return key;
  }

  public Publication setKey(String key) {
    this.key = key;
    return this;
  }

  public String getType() {
    return type;
  }

  public Publication setType(String type) {
    this.type = type;
    return this;
  }

  public String getTopic() {
    return topic;
  }

  public Publication setTopic(String topic) {
    this.topic = topic;
    return this;
  }

  public String getPath() {
    return path;
  }

  public Publication setPath(String path) {
    this.path = path;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public Publication setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Publication setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getContentType() {
    return contentType;
  }

  public Publication setContentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

  public Boolean getStreaming() {
    return streaming;
  }

  public Publication setStreaming(Boolean streaming) {
    this.streaming = streaming;
    return this;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public Publication setHeaders(Map<String, String> headers) {
    this.headers = headers;
    return this;
  }

}
