package com.falkonry.helper.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class Assessment {

    private String id;
    private String sourceId;
    private String name;
    private String tenant;
    private String measurement;
    private String Datastream;
    private String Live;
    private String factsMesaurement;
    private String production;
    private String activeModel;

//    public Assessment() {
//
//    }

    public String getId() {
        return this.id;
    }

    public Assessment setId(String id) {
        this.id = id;
        return this;
    }

    public String getTenant() {
        return tenant;
    }

    public String getDatastream() {
        return Datastream;
    }

    public String getLive() {
        return Live;
    }

    public String getFactsMesaurement() {
        return factsMesaurement;
    }

    public String getProduction() {
        return production;
    }

    public String getActiveModel() {
        return activeModel;
    }

    public String getSourceId() {
        return sourceId;
    }

    public Assessment setSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Assessment setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("tenant")
    public String getAccount() {
        return tenant;
    }

    @JsonProperty("tenant")
    public Assessment setAccount(String account) {
        this.tenant = account;
        return this;
    }

}
