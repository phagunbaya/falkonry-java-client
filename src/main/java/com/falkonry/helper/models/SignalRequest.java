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
public class SignalRequest {
  private String name;
  private ValueType valueType;
  private EventType eventType;

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
    public SignalRequest setName(String name) {
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
    public SignalRequest setValueType(ValueType valueType) {
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
    public SignalRequest setEventType(EventType eventType) {
    this.eventType = eventType;
    return this;
  }
}
