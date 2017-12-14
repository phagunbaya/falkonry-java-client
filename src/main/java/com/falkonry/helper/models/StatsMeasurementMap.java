package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class StatsMeasurementMap {
  private String Data;
  private String Signal;
  private String entity;
  private String entitySignalMap;
  private String DataRate;
  private String DataRateAverage;
  private String ConditionLatency;

    /**
     *
     * @return
     */
    @JsonProperty("Data")
  public String getData() {
    return Data;
  }

    /**
     *
     * @param data
     */
    @JsonProperty("Data")
  public void setData(String data) {
    this.Data = data;
  }

    /**
     *
     * @return
     */
    @JsonProperty("Signal")
  public String getSignal() {
    return Signal;
  }

    /**
     *
     * @param signal
     */
    @JsonProperty("Signal")
  public void setSignal(String signal) {
    this.Signal = signal;
  }

    /**
     *
     * @return
     */
    @JsonProperty("entity")
  public String getEntity() {
    return this.entity;
  }

    /**
     *
     * @param entity
     */
    @JsonProperty("entity")
  public void setEntity(String entity) {
    this.entity = entity;
  }

    /**
     *
     * @return
     */
    @JsonProperty("entitySignalMap")
  public String getEntitySignalMap() {
    return this.entitySignalMap;
  }

    /**
     *
     * @param entitySignalMap
     */
    @JsonProperty("entitySignalMap")
  public void setEntitySignalMap(String entitySignalMap) {
    this.entitySignalMap = entitySignalMap;
  }

    /**
     *
     * @return
     */
    @JsonProperty("DataRate")
  public String getDataRate() {
    return this.DataRate;
  }

    /**
     *
     * @param dataRate
     */
    @JsonProperty("DataRate")
  public void setDataRate(String dataRate) {
    this.DataRate = dataRate;
  }

    /**
     *
     * @return
     */
    @JsonProperty("DataRateAverage")
  public String getDataRateAverage() {
    return this.DataRateAverage;
  }

    /**
     *
     * @param dataRateAverage
     */
    @JsonProperty("DataRateAverage")
  public void setDataRateAverage(String dataRateAverage) {
    this.DataRateAverage = dataRateAverage;
  }

    /**
     *
     * @return
     */
    @JsonProperty("ConditionLatency")
  public String getConditionLatency() {
    return this.ConditionLatency;
  }

    /**
     *
     * @param conditionLatency
     */
    @JsonProperty("ConditionLatency")
  public void setConditionLatency(String conditionLatency) {
    this.ConditionLatency = conditionLatency;
  }
}
