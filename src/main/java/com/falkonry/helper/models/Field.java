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
public class Field {
  private String entityIdentifier;
  private String batchIdentifier;
  private String entityName;
  private Signal signal;
  private TimeObject time;
  
    /**
     *
     * @return
     */
    public String getEntityIdentifier() {
    return this.entityIdentifier;
  }

    /**
     *
     * @param entityIdentifier
     * @return
     */
    public Field setEntityIdentifier(String entityIdentifier) {
    this.entityIdentifier = entityIdentifier;
    return this;
  }
    
    /**
    *
    * @return
    */
   public String getBatchIdentifier() {
   return this.batchIdentifier;
 }

   /**
    *
    * @param batchIdentifier
    * @return
    */
   public Field setBatchIdentifier(String batchIdentifier) {
   this.batchIdentifier = batchIdentifier;
   return this;
 }
  
    /**
     *
     * @return
     */
    public String getEntityName() {
    return this.entityName;
  }

    /**
     *
     * @param entityName
     * @return
     */
    public Field setEntityName(String entityName) {
    this.entityName = entityName;
    return this;
  }
  
    /**
     *
     * @return
     */
    public Signal getSignal() {
    return signal;
  }

    /**
     *
     * @param signal
     * @return
     */
    public Field setSignal(Signal signal) {
    this.signal = signal;
    return this;
  }
  
    /**
     *
     * @return
     */
    public TimeObject getTime() {
    return time;
  }

    /**
     *
     * @param time
     * @return
     */
    public Field setTime(TimeObject time) {
    this.time = time;
    return this;
  }

}