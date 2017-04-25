package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Input {
  private String key;
  private String name;
  private ValueType valueType;
  private EventType eventType;

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
    public Input setKey(String key) {
    this.key = key;
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
    public Input setName(String name) {
    this.name = name;
    return this;
  }

    /**
     *
     * @return
     */
    public ValueType getValueType() {
    return valueType;
  }

    /**
     *
     * @param valueType
     * @return
     */
    public Input setValueType(ValueType valueType) {
    this.valueType = valueType;
    return this;
  }

    /**
     *
     * @return
     */
    public EventType getEventType() {
    return eventType;
  }

    /**
     *
     * @param eventType
     * @return
     */
    public Input setEventType(EventType eventType) {
    this.eventType = eventType;
    return this;
  }
}
