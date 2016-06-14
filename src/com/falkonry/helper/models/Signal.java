package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

class EventType {
  private String type = "Samples";

  public String getType() {
    return type;
  }

  public EventType setType(String type) {
    this.type = type;
    return this;
  }
}

class ValueType {
  private String type;

  public String getType() {
    return type;
  }

  public ValueType setType(String type) {
    this.type = type;
    return this;
  }
}

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
