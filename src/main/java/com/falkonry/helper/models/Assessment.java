package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2017-2018 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Assessment {

    private String id;
    private String sourceId;
    private String name;
    private String tenant;
    private String measurement;
    private String datastream;
    private String live;
    private String factsMesaurement;
    private String production;
    private String activeModel;
    private String rate;
    private List<String> aprioriConditionList;
   

	  /**
     *
     * @return
     */
    public String getRate() {
        return rate;
    }

    /**
     *
     * @param rate
     * @return
     */
    public Assessment setRate(String rate) {
        this.rate = rate;
        return this;
    }

    /**
     *
     * @return
     */
    public String getMeasurement() {
        return measurement;
    }

    /**
     *
     * @param measurement
     * @return
     */
    public Assessment setMeasurement(String measurement) {
        this.measurement = measurement;
        return this;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return this.id;
    }

    /**
     *
     * @param id
     * @return
     */
    public Assessment setId(String id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return
     */
    public String getTenant() {
        return tenant;
    }

    /**
     *
     * @param tenant
     * @return
     */
    public Assessment setTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    /**
     *
     * @return
     */
    public String getDatastream() {
        return datastream;
    }

    /**
     *
     * @param datastream
     * @return
     */
    public Assessment setDatastream(String datastream) {
        this.datastream = datastream;
        return this;
    }

    /**
     *
     * @return
     */
    public String getLive() {
        return live;
    }

    /**
     *
     * @param live
     * @return
     */
    public Assessment setLive(String live) {
        this.live = live;
        return this;
    }

    /**
     *
     * @return
     */
    public String getFactsMesaurement() {
        return factsMesaurement;
    }

    /**
     *
     * @param factsMesaurement
     * @return
     */
    public Assessment setFactsMesaurement(String factsMesaurement) {
        this.factsMesaurement = factsMesaurement;
        return this;
    }

    /**
     *
     * @return
     */
    public String getProduction() {
        return production;
    }

    /**
     *
     * @param production
     * @return
     */
    public Assessment setProduction(String production) {
        this.production = production;
        return this;
    }

    /**
     *
     * @return
     */
    public String getActiveModel() {
        return activeModel;
    }

    /**
     *
     * @param modelId
     * @return
     */
    public Assessment setActiveModel(String modelId) {
        this.activeModel = modelId;
        return this;
    }

    /**
     *
     * @return
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     *
     * @param sourceId
     * @return
     */
    public Assessment setSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name
     * @return
     */
    public Assessment setName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @return
     */
    @JsonProperty("tenant")
    public String getAccount() {
        return tenant;
    }

    /**
     *
     * @param account
     * @return
     */
    @JsonProperty("tenant")
    public Assessment setAccount(String account) {
        this.tenant = account;
        return this;
    }

    /**
     *
     * @return
     */
    public List<String> getAprioriConditionList() {
		return aprioriConditionList;
	}

    /**
     *
     * @param aprioriConditionList
     * @return
     */
    public Assessment setAprioriConditionList(List<String> aprioriConditionList) {
        this.aprioriConditionList = aprioriConditionList;
        return this;
    }

}
