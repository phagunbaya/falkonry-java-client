package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityMeta {

    private String id;
    private String sourceId;
    private String datastream;
    private String path;
    private String label;
    private String tenant;

    /**
     *
     * @return
     */
    public String getTenant() {
        return tenant;
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
     */
    public void setDatastream(String datastream) {
        this.datastream = datastream;
    }

    /**
     *
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     *
     * @param tenant
     */
    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * @return
     */
    public EntityMeta setId(String id) {
        this.id = id;
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
    public EntityMeta setSourceId(String sourceId) {
        this.sourceId = sourceId;
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
    public EntityMeta setAccount(String account) {
        this.tenant = account;
        return this;
    }
}
