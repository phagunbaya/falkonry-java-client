package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Map;

/**
 *
 * @author dev-falkonry-10
 */
@JsonIgnoreProperties(ignoreUnknown=true)
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
     * @return
     */
    public Publication setKey(String key) {
    this.key = key;
    return this;
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
    public Publication setType(String type) {
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
    public Publication setTopic(String topic) {
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
    public Publication setPath(String path) {
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
    public Publication setUsername(String username) {
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
    public Publication setPassword(String password) {
    this.password = password;
    return this;
  }

    /**
     *
     * @return
     */
    public String getContentType() {
    return contentType;
  }

    /**
     *
     * @param contentType
     * @return
     */
    public Publication setContentType(String contentType) {
    this.contentType = contentType;
    return this;
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
    public Publication setStreaming(Boolean streaming) {
    this.streaming = streaming;
    return this;
  }

    /**
     *
     * @return
     */
    public Map<String, String> getHeaders() {
    return headers;
  }

    /**
     *
     * @param headers
     * @return
     */
    public Publication setHeaders(Map<String, String> headers) {
    this.headers = headers;
    return this;
  }

}
