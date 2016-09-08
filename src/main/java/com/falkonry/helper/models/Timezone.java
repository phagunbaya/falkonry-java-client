package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

public class Timezone {
  private String zone;
  private int offset;

  public String getZone() {
    return this.zone;
  }

  public Interval setZone(String zone) {
    this.zone = zone;
    return this;
  }

  public int getOffset() {
    return this.offset;
  }

  public Interval setOffset(String offset) {
    this.offset = offset;
    return this;
  }
}