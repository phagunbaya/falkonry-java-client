package com.falkonry.helper.models;

import java.util.ArrayList;
import java.util.List;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

public class AssessmentRequest {
  private String name;
  private List<String> inputList = new ArrayList<String>();
  private List<String> aprioriConditionList = new ArrayList<String>();

  public List<String> getInputList() {
    return inputList;
  }

  public AssessmentRequest setInputList(List<String> inputList) {
    this.inputList = inputList;
    return this;
  }

  public List<String> getAprioriConditionList() {
    return aprioriConditionList;
  }

  public AssessmentRequest setAprioriConditionList(List<String> aprioriConditionList) {
    this.aprioriConditionList = aprioriConditionList;
    return this;
  }

  public String getName() {

    return name;
  }

  public AssessmentRequest setName(String name) {
    this.name = name;
    return this;
  }
}
