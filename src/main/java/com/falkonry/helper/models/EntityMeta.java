package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 *
 * @author dev-falkonry-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityMeta {

    private String id;
    private String sourceId;
    private String datastream;
    private String path;
    private String label;
    private String tenant;
    private String createdBy;
    private Long createTime;
    private String updatedBy;
    private Long updateTime;
    private Long type;

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
     * @return
     */
    public Long getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(Long type) {
        this.type = type;
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

    /**
     *
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     *
     * @param createdBy
     * @return
     */
    public EntityMeta setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     *
     * @return
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     *
     * @param createTime
     * @return
     */
    public EntityMeta setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     *
     * @return
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     *
     * @param updatedBy
     * @return
     */
    public EntityMeta setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    /**
     *
     * @return
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     *
     * @param updateTime
     * @return
     */
    public EntityMeta setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
