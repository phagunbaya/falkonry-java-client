package com.falkonry.helper.models;

import java.util.ArrayList;
import java.util.List;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

public class Assessment {
  private String key;
  private String name;
  private String measurement;
  private String episodeMeasurement;
  private String verificationMeasurement;
  private List<String> inputList = new ArrayList<String>();
  private List<String> aprioriConditionList = new ArrayList<String>();

  public Assessment() {

  }

  public String getKey() {
    return this.key;
  }

  public Assessment setKey(String key) {
    this.key = key;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public Assessment setName(String name) {
    this.name = name;
    return this;
  }

  public List<String> getInputList() {
    return this.inputList;
  }

  public Assessment setInputList(List<String> inputList) {
    this.inputList = inputList;
    return this;
  }

  public List<String> getAprioriConditionList() {
    return this.aprioriConditionList;
  }

  public Assessment setAprioriConditionList(List<String> aprioriConditionList) {
    this.aprioriConditionList = aprioriConditionList;
    return this;
  }

  public String getMeasurement() {
    return measurement;
  }

  public void setMeasurement(String measurement) {
    this.measurement = measurement;
  }

  public String getVerificationMeasurement() {
    return verificationMeasurement;
  }

  public void setVerificationMeasurement(String verificationMeasurement) {
    this.verificationMeasurement = verificationMeasurement;
  }

  public String getEpisodeMeasurement() {
    return episodeMeasurement;
  }

  public void setEpisodeMeasurement(String episodeMeasurement) {
    this.episodeMeasurement = episodeMeasurement;
  }
}
