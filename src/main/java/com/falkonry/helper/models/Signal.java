package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Signal {
  private String key;
  private String name;
  private ValueType valueType;
  private EventType eventType;

  public String getKey() {
    return key;
  }

  public Signal setKey(String key) {
    this.key = key;
    return this;
  }

  public String getName() {
    return name;
  }

  public Signal setName(String name) {
    this.name = name;
    return this;
  }

  public ValueType getValueType() {
    return valueType;
  }

  public Signal setValueType(ValueType valueType) {
    this.valueType = valueType;
    return this;
  }

  public EventType getEventType() {
    return eventType;
  }

  public Signal setEventType(EventType eventType) {
    this.eventType = eventType;
    return this;
  }
}
