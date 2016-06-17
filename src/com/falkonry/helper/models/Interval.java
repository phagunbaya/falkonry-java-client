package com.falkonry.helper.models;

/**
 * Created by avais on 17/6/16.
 */
public class Interval {
  private String field;
  private String duration;

  public String getField() {
    return this.field;
  }

  public Interval setField(String field) {
    this.field = field;
    return this;
  }

  public String getDuration() {
    return this.duration;
  }

  public Interval setDuration(String duration) {
    this.duration = duration;
    return this;
  }
}