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
@JsonIgnoreProperties(ignoreUnknown=true)

public class Stats {
    private int data;

    /**
     *
     * @return
     */
    public int getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(int data) {
        this.data = data;
    }
}
