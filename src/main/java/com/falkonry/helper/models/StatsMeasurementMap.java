package com.falkonry.helper.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

/**
 *
 * @author dev-falkonry-10
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class StatsMeasurementMap {
  private String Data;
  private String Signal;
  private String Thing;
  private String ThingSignalMap;
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
    @JsonProperty("Thing")
  public String getThing() {
    return this.Thing;
  }

    /**
     *
     * @param thing
     */
    @JsonProperty("Thing")
  public void setThing(String thing) {
    this.Thing = thing;
  }

    /**
     *
     * @return
     */
    @JsonProperty("ThingSignalMap")
  public String getThingSignalMap() {
    return this.ThingSignalMap;
  }

    /**
     *
     * @param thingSignalMap
     */
    @JsonProperty("ThingSignalMap")
  public void setThingSignalMap(String thingSignalMap) {
    this.ThingSignalMap = thingSignalMap;
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
