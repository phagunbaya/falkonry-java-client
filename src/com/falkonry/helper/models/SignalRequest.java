package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

public class SignalRequest {
  private String name;
  private ValueType valueType;
  private EventType eventType;

  public String getName() {
    return name;
  }

  public SignalRequest setName(String name) {
    this.name = name;
    return this;
  }

  public ValueType getValueType() {
    return valueType;
  }

  public SignalRequest setValueType(ValueType valueType) {
    this.valueType = valueType;
    return this;
  }

  public EventType getEventType() {
    return eventType;
  }

  public SignalRequest setEventType(EventType eventType) {
    this.eventType = eventType;
    return this;
  }
}
