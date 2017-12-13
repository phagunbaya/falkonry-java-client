package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Signal {

    
    private String valueIdentifier;
    private String signalIdentifier;
    

    /**
     *
     * @return
     */
    public String getValueIdentifier() {
        return valueIdentifier;
    }

    /**
     *
     * @param valueIdentifier
     */
    public void setValueIdentifier(String valueIdentifier) {
        this.valueIdentifier = valueIdentifier;
    }
    
    /**
    *
    * @return
    */
   public String getSignalIdentifier() {
       return signalIdentifier;
   }

   /**
    *
    * @param valueIdentifier
    */
   public void setSignalIdentifier(String signalIdentifier) {
       this.signalIdentifier = signalIdentifier;
   }

    

}
