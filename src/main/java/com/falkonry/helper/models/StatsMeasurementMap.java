package com.falkonry.helper.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
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

  @JsonProperty("Data")
  public String getData() {
    return Data;
  }

  @JsonProperty("Data")
  public void setData(String data) {
    this.Data = data;
  }

  @JsonProperty("Signal")
  public String getSignal() {
    return Signal;
  }

  @JsonProperty("Signal")
  public void setSignal(String signal) {
    this.Signal = signal;
  }

  @JsonProperty("Thing")
  public String getThing() {
    return this.Thing;
  }

  @JsonProperty("Thing")
  public void setThing(String thing) {
    this.Thing = thing;
  }

  @JsonProperty("ThingSignalMap")
  public String getThingSignalMap() {
    return this.ThingSignalMap;
  }

  @JsonProperty("ThingSignalMap")
  public void setThingSignalMap(String thingSignalMap) {
    this.ThingSignalMap = thingSignalMap;
  }

  @JsonProperty("DataRate")
  public String getDataRate() {
    return this.DataRate;
  }

  @JsonProperty("DataRate")
  public void setDataRate(String dataRate) {
    this.DataRate = dataRate;
  }

  @JsonProperty("DataRateAverage")
  public String getDataRateAverage() {
    return this.DataRateAverage;
  }

  @JsonProperty("DataRateAverage")
  public void setDataRateAverage(String dataRateAverage) {
    this.DataRateAverage = dataRateAverage;
  }

  @JsonProperty("ConditionLatency")
  public String getConditionLatency() {
    return this.ConditionLatency;
  }

  @JsonProperty("ConditionLatency")
  public void setConditionLatency(String conditionLatency) {
    this.ConditionLatency = conditionLatency;
  }
}
