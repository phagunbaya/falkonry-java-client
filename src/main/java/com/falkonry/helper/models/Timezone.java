package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2017-2018 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Timezone {
  private String zone;
  private int offset;

    /**
     *
     * @return
     */
    public String getZone() {
    return this.zone;
  }

    /**
     *
     * @param zone
     * @return
     */
    public Timezone setZone(String zone) {
    this.zone = zone;
    return this;
  }

    /**
     *
     * @return
     */
    public int getOffset() {
    return this.offset;
  }

    /**
     *
     * @param offset
     * @return
     */
    public Timezone setOffset(int offset) {
    this.offset = offset;
    return this;
  }
}