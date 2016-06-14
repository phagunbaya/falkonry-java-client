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
  private List<Signal> inputList = new ArrayList<Signal>();
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

  public List<Signal> getInputList() {
    return this.inputList;
  }

  public Assessment setInputList(List<Signal> inputList) {
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
}
